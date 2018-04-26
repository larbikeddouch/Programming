import pandas_datareader.data as web

def get_stock_data(stocknames, source, startdate, enddate):
    return web.DataReader(stocknames, source, startdate, enddate)