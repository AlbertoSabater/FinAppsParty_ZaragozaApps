#!/usr/bin/python
import sys
import time
from sense_hat import SenseHat

sense = SenseHat()
sense.clear()

for i in reversed(range(20)):
	sense.show_message(str(i),text_colour=[255,0,0])
