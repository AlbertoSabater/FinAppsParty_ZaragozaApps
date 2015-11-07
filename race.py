#!/usr/bin/python
import sys
import time
from sense_hat import SenseHat

sense = SenseHat()
id = int(sys.argv[1])
height = int(sys.argv[2])
print(id);

def redlights(x, y):
	for i in range(0,y):
			sense.set_pixel(x,7-i,[255,0,0])
	return

def yellowlights(x, y):
	for i in range(0,y):
			sense.set_pixel(x,7-i,[255,255,0])
	return

def greenlights(x, y):
	for i in range(0,y):
		sense.set_pixel(x,7-i,[0,255,0])
	return

if (id == 0):
	greenlights(id, height)
	print("aaa")
elif (id == 1):
	redlights(id, height)

print("bbb")