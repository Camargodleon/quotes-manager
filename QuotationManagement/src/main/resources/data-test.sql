
insert into stock_app (stock_id, id) values ('petr4', UUID());
insert into quotes (id, quote_date, quote_value, stock_id) values ((NEXT VALUE FOR hibernate_sequence), '2019-01-01', 10, (select id from stock_app where stock_id = 'petr4'));
insert into quotes (id, quote_date, quote_value, stock_id) values ((NEXT VALUE FOR hibernate_sequence), '2019-01-02', 11, (select id from stock_app where stock_id = 'petr4'));
insert into quotes (id, quote_date, quote_value, stock_id) values ((NEXT VALUE FOR hibernate_sequence), '2019-01-03', 14, (select id from stock_app where stock_id = 'petr4'));
insert into stock_app (stock_id, id) values ('aapl34', UUID());
insert into quotes (id, quote_date, quote_value, stock_id) values ((NEXT VALUE FOR hibernate_sequence), '2019-01-01', 10, (select id from stock_app where stock_id = 'aapl34'));
insert into quotes (id, quote_date, quote_value, stock_id) values ((NEXT VALUE FOR hibernate_sequence), '2019-01-02', 11, (select id from stock_app where stock_id = 'aapl34'));