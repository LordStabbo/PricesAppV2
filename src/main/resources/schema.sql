CREATE TABLE IF NOT EXISTS PRICES (
    BRAND_ID SMALLINT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST SMALLINT NOT NULL,
    PRODUCT_ID INTEGER NOT NULL,
    PRIORITY SMALLINT NOT NULL,
    PRICE VARCHAR(7) NOT NULL,
    CURRENCY VARCHAR(7) NOT NULL
);

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST,  PRODUCT_ID, PRIORITY, PRICE, CURRENCY)
VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, '35.50', 'EUR'),
       (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1,  '25.45', 'EUR'),
       (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1,  '30.5', 'EUR'),
       (1, '2020-06-15 16:00:00', '2020-12-30 23:59:59', 4, 35455, 1,  '38.95', 'EUR');
