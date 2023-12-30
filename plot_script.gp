set terminal pdfcairo enhanced font 'Verdana,12'
set output 'performance_plot.png'

set title "Performance Comparison"
set xlabel "Size"
set ylabel "Time"
set datafile separator ","
set grid

plot "quicksort_data.csv" using 1:2 with lines title 'Quicksort', \
     "priority_queue_data.csv" using 1:2 with lines title 'Priority Queue'
