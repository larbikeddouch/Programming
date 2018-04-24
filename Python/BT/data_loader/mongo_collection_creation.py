import pymongo

# Start using collection data in financial_data database
client = pymongo.MongoClient('mongodb://127.0.0.1:27017')
db = client.financial_data
mydata = db.data

# Create multi index for mydata
# Stock name and datetime
# Initially intended to have the "unique" flag
# set to True for such index
# Remove?
mydata.create_index([
        ('Stockname', "text"),
        ('Date', pymongo.DESCENDING)
    ])