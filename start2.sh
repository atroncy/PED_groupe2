#!/bin/bash
# 	ksu $user login
#	$1=interface



sudo /sbin/ardrone-wifi -i $1 -o up $1

sudo /sbin/ardrone-wifi -i $1 -o scan

sudo /sbin/ardrone-wifi -i $1 -o start -s BebopDrone-I000520 -a 192.168.42.2

sudo /sbin/ardrone-wifi -i $1 -o status

ping -c3 192.168.42.1
