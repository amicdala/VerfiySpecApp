spin -a  ..\\example.pml
gcc -DMEMLIM=1024 -O2 -DXUSAFE -w -o pan pan.c
pan -m10000  -a -N req1 >> outreq1.txt