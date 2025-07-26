INSERT INTO prices (
    brand_id,
    start_date,
    end_date,
    price_list_id,
    product_id,
    priority,
    price,
    currency,
    last_update,
    last_update_by
)
SELECT
    BrandId,
    PARSEDATETIME(StartDate, 'yyyy-MM-dd-HH.mm.ss'),
    PARSEDATETIME(EndDate, 'yyyy-MM-dd-HH.mm.ss'),
    PriceList,
    ProductId,
    Priority,
    Price,
    Currency,
    PARSEDATETIME(LastUpdate, 'yyyy-MM-dd-HH.mm.ss'),
    LastUpdateBy
FROM CSVREAD(
    'classpath:db/data/test_data_prices.csv',
    null,
    'fieldSeparator=,');
