spin -a  ..\\examplePG.pml
gcc -DMEMLIM=1024 -O2 -DXUSAFE -w -o pan pan.c
pan -m10000  -a -N req1_1 >> outreq11.txt
