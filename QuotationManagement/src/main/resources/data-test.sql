insert into stock_app (stock_id, id) values ('petr4', UUID());
insert into stock_quotes (id, date_key, quote_value) values ((select id from stock_app where stock_id = 'petr4'), '2019-01-01', 10);
insert into stock_quotes (id, date_key, quote_value) values ((select id from stock_app where stock_id = 'petr4'), '2019-01-02', 11);
insert into stock_quotes (id, date_key, quote_value) values ((select id from stock_app where stock_id = 'petr4'), '2019-01-03', 14);
insert into stock_app (stock_id, id) values ('appl34', UUID());
insert into stock_quotes (id, date_key, quote_value) values ((select id from stock_app where stock_id = 'appl34'), '2019-01-01', 10);
insert into stock_quotes (id, date_key, quote_value) values ((select id from stock_app where stock_id = 'appl34'), '2019-01-02', 11);