#!/bin/bash
# 	ksu $user login
#	$1=interface



sudo /sbin/ardrone-wifi -i $1 -o up $1

sudo /sbin/ardrone-wifi -i $1 -o scan

sudo /sbin/ardrone-wifi -i $1 -o start -s PLOP -m adhoc -c 6 -a 192.168.1.3

sudo /sbin/ardrone-wifi -i $1 -o status

ping -c3 192.168.1.1

ping -c3 192.168.1.2
