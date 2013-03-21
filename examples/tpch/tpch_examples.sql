-- basket analysis
select l1.l_partkey, l2.l_partkey, count(*) as frequency
from lineitem l1, lineitem l2
where l1.l_orderkey = l2.l_orderkey
and l1.l_partkey != l2.l_partkey
group by l1.l_partkey, l2.l_partkey
order by frequency desc;

-- basket analysis with UDF (simlarity of p_type)

-- step 1: find products in same order
select l1.l_partkey as p1_key, l2.l_partkey as p2_key, 
p1.p_type as p1_type, p2.p_type as p2_type, count(*) as frequency
from lineitem l1, lineitem l2, part p1, part p2
where l1.l_orderkey = l2.l_orderkey
and l1.l_partkey = p1.p_partkey 
and l2.l_partkey = p2.p_partkey 
and l1.l_partkey != l2.l_partkey
group by l1.l_partkey, l2.l_partkey, p1.p_type, p2.p_type
order by frequency desc;

-- step 2: UDF to filter products with similar types