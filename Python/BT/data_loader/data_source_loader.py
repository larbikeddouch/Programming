from single_stock_data import get_stock_data
import mongo_util

class DataSource(object):
    ''' This class handles a data source, more specifically
        it tries to load data from mongo, and if it does not find it,
        it tries to download it from apis'''
    source = None
    # Does not download unless specified
    auto_complete_data = False

    def __init__(self,
                 data_source_name,
                 auto_complete_data=False):
        self.source = data_source_name
        self.auto_complete_data = auto_complete_data

    def read_data(self, stocks, start_date, end_date):
        # Mongo Query
        df = mongo_util.read_mongo([self.source],
                                stocks,
                                start_date,
                                end_date)

        # Performs the addition of new data only if
        # auto_complete_data is True
        if self.auto_complete_data:
            # Case where there is currently no data
            if df.empty:
                self.add_new_data(stocks, start_date, end_date)
                return mongo_util.read_mongo([self.source],
                                           stocks,
                                           start_date,
                                           end_date)

            # Comes here only if there already is data
            # Find out first and last date in df
            df_start_date = df.index.levels[1].min()
            df_end_date = df.index.levels[1].max()

            # Ensure as few holes in data as possible
            if df_end_date < start_date:
                self.read_data(stocks, df_end_date, end_date)

            if df_start_date > end_date:
                self.read_data(stocks, start_date, df_start_date)

            # Add new data if start_date or end_date is too far
            # from current data dates
            if (df_start_date - start_date).days > 7:
                self.add_new_data(stocks, start_date, df_start_date)

            if (end_date - df_end_date).days > 7:
                self.add_new_data(stocks, df_end_date, end_date)

            df = mongo_util.read_mongo([self.source],
                                       stocks,
                                       start_date,
                                       end_date)

        return df

    def add_new_data(self, stocks, start_date, end_date):
        additional_data = get_stock_data(stocks,
                                         self.source,
                                         start_date,
                                         end_date)

        if not additional_data is None and not additional_data.empty:
            insertion_result = mongo_util.write_mongo(additional_data, self.source)