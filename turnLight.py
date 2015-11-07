#!/usr/bin/python
import sys
import time
import random
from sense_hat import SenseHat

sense = SenseHat()
x = int(sys.argv[1])
y = int(sys.argv[2])
r = int(sys.argv[3])
g = int(sys.argv[4])
b = int(sys.argv[5])

print x
print y
print r
print g
print b
sense.set_pixel(x,y,[r,g,b])