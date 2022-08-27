from gpiozero import Servo
from time import sleep
import RPi.GPIO as GPIO
import time
import os
from gpiozero.pins.pigpio import PiGPIOFactory
from nturl2path import url2pathname
import requests
import datetime

#ultrasonic configuration
TRIG=21
ECHO=20
GPIO.setmode(GPIO.BCM)

#servo configuration
factory = PiGPIOFactory()
servo = Servo(12, pin_factory=factory, min_pulse_width=0.0005, max_pulse_width=0.0025)

#flushing function (called when the ultrasonic detected certain distance object)
def servoFlush():
    print("Go to min")
    servo.min()
    sleep(2)
    print("Go to mid")
    servo.mid()
    sleep(2)
    print("Go to min")
    servo.min()
    servo.value = None;
    
def capture():
    cmd1 = "libcamera-still -o capture_test.jpg --tuning-file /usr/share/libcamera/ipa/raspberrypi/imx219_noir.json"
    os.system(cmd1)
    ##send image to server
    #url = "http://localhost:4000/upload"
    #current_time = datetime.datetime.now()
    #files = { 
    #        "profile" : open("capture_test.jpg", "rb"),
    #        "time" : current_time
    #        }
    #r = requests.post(url, files=files)
    

while True:
    servo.min()
    print("distance measurement in progress")
    GPIO.setup(TRIG,GPIO.OUT)
    GPIO.setup(ECHO,GPIO.IN)
    GPIO.output(TRIG,False)
    print("waiting for sensor to settle")
    time.sleep(0.2)
    
    GPIO.output(TRIG,True)
    time.sleep(0.00001)
    GPIO.output(TRIG,False)
    
    while GPIO.input(ECHO)==0:
        pulse_start=time.time()
    while GPIO.input(ECHO)==1:
        pulse_end=time.time()
        
    pulse_duration=pulse_end-pulse_start
    distance=pulse_duration*17150
    distance=round(distance,2)
    print("distance:",distance,"cm")
    
    if (distance < 8):
        print("Flush triggered")
        capture()
        servoFlush()
    time.sleep(2)


    
