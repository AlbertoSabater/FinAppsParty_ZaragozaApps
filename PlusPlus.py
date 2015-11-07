#!/usr/bin/python
import sys
import time
from sense_hat import SenseHat

sense = SenseHat()
sense.clear() 

def redlights():
	for i in range(8):
		for j in range(8):
			sense.set_pixel(i,j,[255,0,0])
	return

def bluelights():
	for i in range(8):
		for j in range(8):
			sense.set_pixel(i,j,[0,0,255])
	return

def twowinnerslighs():
	for i in range(8):
		if i <= 3:
			for j in range(8):
				sense.set_pixel(i,j,[255,0,0])
		else:
			for j in range(8):
				sense.set_pixel(i,j,[0,0,255])
	return

def clearlights():
	for i in range(8):
		for j in range(8):
			sense.set_pixel(i,j,[150,150,150])
		
	return

id = str(sys.argv[1])
if id == "0":
	for i in range(2):
		redlights()
		time.sleep(2)
		clearlights()
		time.sleep(1)
	redlights()
	time.sleep(2)
elif id == "1":
	for i in range(2):
		bluelights()
		time.sleep(2)
		clearlights()
		time.sleep(1)
	bluelights()
	time.sleep(2)
else:
	for i in range(2):
		twowinnerslighs()
		time.sleep(2)
		clearlights()
		time.sleep(1)
	twowinnerslighs()
	time.sleep(2)
sense.clear()
