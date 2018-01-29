import datetime
import pandas_datareader.data as web

def get_stock_data(stockname, source, startdate, enddate):
    return web.DataReader(stockname, source, startdate, enddate)