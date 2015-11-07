#!/usr/bin/python
import sys
import time
import random
from sense_hat import SenseHat

sense = SenseHat()
positions = [[2,2],[3,2],[4,2],[5,2],[2,3],[3,3],[4,3],[5,3],
[2,4],[3,4],[4,4],[5,4],[2,5],[3,5],[4,5],[5,5]]

def clearlights():
	for i in range(2,6):
		for j in range(2,6):
			sense.set_pixel(i,j,[150,150,150])
	return

i = 1
j = 0
while (i < len(sys.argv)): 
	r = int(sys.argv[i])
	g = int(sys.argv[i+1])
	b = int(sys.argv[i+2])
	sense.set_pixel(positions[j][0],positions[j][1],[r,g,b])
	i += 3
	j += 1

time.sleep(5)
clearlights()
