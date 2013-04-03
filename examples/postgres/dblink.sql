select * from dblink('port=5432 dbname=tpch_s1 user=postgres password=postgres', 'select sum(l_extendedprice), l_linestatus from lineitem group by l_linestatus') as t1 (revenue decimal, l_linestatux char(1));

select sum(l_extendedprice), l_linestatus from lineitem group by l_linestatus;