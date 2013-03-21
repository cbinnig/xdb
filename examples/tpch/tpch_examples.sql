-- basket analysis
select l1.l_partkey, l2.l_partkey, count(*) as frequency
from lineitem l1, lineitem l2
where l1.l_orderkey = l2.l_orderkey
and l1.l_partkey != l2.l_partkey
group by l1.l_partkey, l2.l_partkey
order by frequency desc;

-- basket analysis with UDF (simlarity of p_type)

-- step 1: find products in same order
select l1.l_partkey, l2.l_partkey, p1.p_type, p2.p_type
from lineitem l1, lineitem l2, part p1, part p2
where l1.l_orderkey = l2.l_orderkey
and l1.l_partkey = p1.p_partkey 
and l2.l_partkey = p2.p_partkey 
and l1.l_partkey != l2.l_partkey
order by l1.l_partkey, l2.l_partkey
limit 1000;

-- step 2: calculate similarity of product types and aggregate 
-- if(similarity(p1.p_type, p2.p_type)>0.5) aggregate (count) => map and reduce in one op

-- step 3: re-partition by l1.l_partkey and aggregate (count)
