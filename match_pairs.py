#!/usr/bin/python
import sys
import time
import random
from sense_hat import SenseHat

sense = SenseHat()
sense.clear()

blue = ([0,0,255],0)
red = ([255,0,0],0)
yellow = ([255,255,0],0)
green = ([0,255,0],0)
darkgreen = ([0,128,0],0)
pink = ([255,0,255],0)
ciam = ([0,255,255],0)
orange = ([255,69,0],0)

lista = [blue,red,yellow,green,darkgreen,pink,ciam,orange]
positions = [[2,2],[2,3],[2,4],[2,5],[3,2],[3,3],[3,4],[3,5],
[4,2],[4,3],[4,4],[4,5],[5,2],[5,3],[5,4],[5,5]]

for position in positions:
	i = random.randint(0,7) #Modify colour
	temp = list(lista[i]) #List of the tuple
	#Check if the colour has been added more than twice
	while (temp[1] > 1):
		#If added more than twice, get new colour
		i = random.randint(0,7)
		temp = list(lista[i])
	temp[1] += 1 #Update times of modification
	sense.set_pixel(position[0],position[1],temp[0])
	lista[i] = tuple((temp[0],temp[1]))