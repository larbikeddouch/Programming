import pandas as pd
from pymongo import MongoClient

client = MongoClient('mongodb://127.0.0.1:27017')
db = client.test
result = db.test.find_one({})
print(result)


def _connect_mongo(host, port, username, password, db):
    """ A util for making a connection to mongo """

    if username and password:
        mongo_uri = 'mongodb://%s:%s@%s:%s/%s' % (username, password, host, port, db)
        conn = MongoClient(mongo_uri)
    else:
        conn = MongoClient(host, port)

    return conn[db]


def read_mongo(db='db', collection='financial_data', query={}, host='localhost', port=27017, username=None,
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


def write_mongo(df, db='db', collection='financial_data', host='localhost', port=27017, username=None, password=None,
                no_id=True):
    """ Read from DataFrame and store into Mongo"""

    # Connect to MongoDB
    db = _connect_mongo(host=host, port=port, username=username, password=password, db=db)

    # Get specific Collection in db
    data = db[collection]

    # 2 Cases :
    # Case 1: update with new information or corrected information
    # Case 2: write completely new data

    # Case 2
    df_as_dict = df.to_dict(orient='index')

    def add_date_field(record_date):
        df_as_dict[record_date]['Date'] = record_date.to_pydatetime()
        return df_as_dict[record_date]

    insert_dico = map(lambda date: add_date_field(date), df_as_dict.keys())
