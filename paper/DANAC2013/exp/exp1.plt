set style data histogram
set style histogram cluster gap 1
set style fill solid 1.00 border 0

set xtics ("Query 3" 0, "Query 5" 1, "Basket Analysis" 2)

set ylabel "Time (in seconds)"    
set yrange [0:*]
set autoscale x

plot	"XDB.dat" u 2 title 'XDB' linecolor rgb "green", \
        "Hive.dat" u 2 title 'Hive' linecolor rgb "yellow"

set term postscript 
set output "exp1-eps-converted-to.eps"
replot
