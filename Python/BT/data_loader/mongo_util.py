import pandas as pd
from pymongo import MongoClient
from pymongo import ASCENDING


def _connect_mongo(host, port, username, password, db):
    """ A util for making a connection to mongo """

    if username and password:
        mongo_uri = 'mongodb://%s:%s@%s:%s/%s' % (username, password, host, port, db)
        conn = MongoClient(mongo_uri)
    else:
        conn = MongoClient(host, port)

    return conn[db]


def read_mongo(sources, stocks, start_date, end_date, db='financial_data', collection='data',
        host='localhost', port=27017, username=None, password=None, no_id=True):
    """ Read from Mongo and Store into DataFrame """

    if end_date < start_date:
        raise ValueError('Entered Start Date is after End Date')

    query = {'$and': [
        {'Date': {'$gt': start_date, '$lt': end_date}},
        {'Source': {'$in': sources}},
        {'Stockname': {'$in': stocks}}]}

    # Connect to MongoDB
    db = _connect_mongo(host=host, port=port, username=username, password=password, db=db)

    # Make a query to the specific DB and Collection
    cursor = db[collection].find(query).sort('Date', ASCENDING)

    # Expand the cursor and construct the DataFrame
    # If no data matches the query, cursor is not None
    # list(cursor) is an empty list and df is an empty dataframe
    df = pd.DataFrame(list(cursor))

    # Delete the _id
    if no_id and not df.empty:
        del df['_id']

    # Set MultiIndex with stock and date
    if not df.empty:
        df.set_index(['Stockname', 'Date'], inplace=True)

    return df


def write_mongo(df, source, db='financial_data', collection='data', host='localhost', port=27017,
                username=None, password=None, no_id=True):
    """ Read from DataFrame and store into Mongo. Expects a MultiIndex dataframe
    with stock names as first level and dates as second one """

    # Connect to MongoDB
    db = _connect_mongo(host=host, port=port, username=username, password=password, db=db)

    # Get specific Collection in db
    data = db[collection]

    # Get data from df
    insert_dico_list = prepare_dict_from_dataframe(df, source)

    # Careful: map object is applied only when actual objects are needed
    insertion_result = map(lambda x: add_data_point(data, source, x), insert_dico_list)

    return [*insertion_result]


def prepare_dict_from_dataframe(df, source):
    # Turn dataframe into dictionary for mongodb insertion
    df_as_dict = df.to_dict(orient='index')

    def add_date_field(stock_date_tuple):
        df_as_dict[stock_date_tuple]['Stockname'] = stock_date_tuple[0]
        df_as_dict[stock_date_tuple]['Date'] = stock_date_tuple[1].to_pydatetime()
        df_as_dict[stock_date_tuple]['Source'] = source
        return df_as_dict[stock_date_tuple]

    insert_dico_list = map(lambda key: add_date_field(key), df_as_dict.keys())
    return [*insert_dico_list]


def add_data_point(data, source, stockinfo):
    # Try to see if data is already inserted and
    # if not, sends all data
    filter = {
        'Date': stockinfo['Date'],
        'Stockname': stockinfo['Stockname'],
        'Source': source
    }
    db_query_result = data.find_one(filter)

    # make the list of all missing fields
    db_query_field_list = list(db_query_result.keys()) if (not db_query_result is None) else []
    newfields = [field for field in stockinfo.keys() if field not in db_query_field_list]

    # insert new fields if there are any
    # if new point, then all fields are in there
    # upsert means that the document is inserted if nothing matches the initial query
    local_insertion_result = None
    if len(newfields) > 0:
        additional_data = dict([(field, stockinfo[field]) for field in newfields])
        local_insertion_result = data.update_one(filter, {'$set' : additional_data}, upsert=True)

    return local_insertion_result