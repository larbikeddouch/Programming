import data_loader
import datetime

stock1 = "F"
stock2 = "AAPL"
stock3 = "GOOGL"

source = 'google'
startdate = datetime.datetime(2018,1,1)
enddate = datetime.datetime(2018,1,26)

print(stock1+":")
print(data_loader.get_stock_data(stock1,source,startdate,enddate).ix['2018-01-26'])

print("#####################################")
print(stock2+":")
print(data_loader.get_stock_data(stock2,source,startdate,enddate).ix['2018-01-26'])

print('#####################################')
print(stock3+":")
print(data_loader.get_stock_data(stock3,source,startdate,enddate).ix['2018-01-26'])