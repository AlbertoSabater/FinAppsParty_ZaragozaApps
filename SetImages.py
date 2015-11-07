#!/usr/bin/python
import sys
import time
from sense_hat import SenseHat

sense = SenseHat()

def redlights():
	for i in range(8):
		for j in range(8):
			sense.set_pixel(i,j,[255,0,0])
	return

def yellowlights():
	for i in range(8):
		for j in range(8):
			sense.set_pixel(i,j,[255,255,0])
	return

def greenlights():
	for i in range(8):
			for j in range(8):
				sense.set_pixel(i,j,[0,255,0])
	return

while True:
	greenlights()
	time.sleep(3)
	yellowlights()
	time.sleep(3)
	redlights()
	time.sleep(3)

