import data_loader

source = 'morningstar'

def get_data(stocknames, startdate, enddate):
    return data_loader.get_stock_data(stocknames,source,startdate,enddate)

# stock1 = "F"
# stock2 = "AAPL"
# stock3 = "GOOGL"
#
# source = 'yahoo'
# startdate = datetime.datetime(2018,1,1)
# enddate = datetime.datetime(2018,1,26)
#
# print(stock1+":")
# print(data_loader.get_stock_data(stock1,source,startdate,enddate).ix['2018-01-26'])
#
# print("#####################################")
# print(stock2+":")
# print(data_loader.get_stock_data(stock2,source,startdate,enddate).ix['2018-01-26'])
#
# print('#####################################')
# print(stock3+":")
# print(data_loader.get_stock_data(stock3,source,startdate,enddate).ix['2018-01-26'])