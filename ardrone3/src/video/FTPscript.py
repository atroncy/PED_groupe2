#!/usr/bin/env python

from __future__ import absolute_import
from __future__ import print_function
from __future__ import unicode_literals

import pexpect
import sys

#Creation of a child process
child = pexpect.spawn('ftp 192.168.42.1 21')

child.expect('(?i)name .*: ')
child.sendline('pedGroupe2')
child.expect('ftp> ')
child.sendline('cd internal_000/Bebop_Drone/media')
child.expect('ftp> ')
child.sendline('ls')
child.expect('ftp> ')
child.sendline('prompt')
child.expect('ftp> ')
#Get all files from the Bebop
child.sendline('mget -p -i *.*')
child.expect('ftp> ')
#Delete all files from the Bebop to release memory
child.sendline('mdel -p -i *.*')

if child.isalive():
    child.sendline('bye') 
    child.close()

if child.isalive():
    print('Child did not exit gracefully.')
else:
    print('Child exited gracefully.')