import pandas as pd
from pymongo import MongoClient

def _connect_mongo(host, port, username, password, db):
    """ A util for making a connection to mongo """

    if username and password:
        mongo_uri = 'mongodb://%s:%s@%s:%s/%s' % (username, password, host, port, db)
        conn = MongoClient(mongo_uri)
    else:
        conn = MongoClient(host, port)

    return conn[db]


def read_mongo(db='financial_data', collection='data', query={}, host='localhost', port=27017, username=None,
               password=None, no_id=True):
    """ Read from Mongo and Store into DataFrame """

    # Connect to MongoDB
    db = _connect_mongo(host=host, port=port, username=username, password=password, db=db)

    # Make a query to the specific DB and Collection
    cursor = db[collection].find(query)

    # Expand the cursor and construct the DataFrame
    df = pd.DataFrame(list(cursor))

    # Delete the _id
    if no_id:
        del df['_id']

    return df


def write_mongo(df, source, db='financial_data', collection='data', host='localhost', port=27017, username=None, password=None,
                no_id=True):
    """ Read from DataFrame and store into Mongo"""
    """ Expects a MultiIndex dataframe
        with stock names as first level and
        dates as second one 
    """

    # Connect to MongoDB
    db = _connect_mongo(host=host, port=port, username=username, password=password, db=db)

    # Get specific Collection in db
    data = db[collection]

    # Turn dataframe into dictionary for mongodb insertion
    df_as_dict = df.to_dict(orient='index')

    def add_date_field(stock_date_tuple):
        df_as_dict[stock_date_tuple]['Stockname'] = stock_date_tuple[0]
        df_as_dict[stock_date_tuple]['Date'] = stock_date_tuple[1].to_pydatetime()
        df_as_dict[stock_date_tuple]['Source'] = source
        return df_as_dict[stock_date_tuple]

    insert_dico_list = map(lambda key: add_date_field(key), df_as_dict.keys())
    insert_dico_list = [*insert_dico_list]

    # Case 1: update with new information or corrected information
    # Case 2: write completely new data
    #insertion_result = data.insert_many(insert_dico)

    # Try to see if data is already inserted and, if not, sends all data
    def add_data_point(stockinfo):
        db_query_result = data.find_one({
            'Date': stockinfo['Date'],
            'Stockname': stockinfo['Stockname'],
            'Source': source
        })
        if db_query_result is None:
            data.insert_one(stockinfo)
            return None
        else:
            return stockinfo

    # Careful: map object is applied only when actual objects are needed
    insertion_result = map(add_data_point, insert_dico_list)

    return [*insertion_result]
