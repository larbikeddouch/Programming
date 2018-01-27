import pandas_datareader.data as web
import datetime

start = datetime.datetime(2018,1,1)
end = datetime.datetime(2018,1,26)

f = web.DataReader("F",'yahoo',start,end)

print(f.ix['2018-01-26'])
