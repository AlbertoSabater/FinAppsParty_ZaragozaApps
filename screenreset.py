#!/usr/bin/python
import sys
import time
from sense_hat import SenseHat

sense = SenseHat()

for i in range(0,8):
	for j in range(0,8):

			sense.set_pixel(i,j,[155,155,155])

print("end of screen reset")
