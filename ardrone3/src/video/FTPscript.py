#!/usr/bin/env python

from __future__ import absolute_import
from __future__ import print_function
from __future__ import unicode_literals

import pexpect
import sys

# Note that, for Python 3 compatibility reasons, we are using spawnu and
# importing unicode_literals (above). spawnu accepts Unicode input and
# unicode_literals makes all string literals in this script Unicode by default.
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
child.sendline('mget *.*')
#child.sendline('get Bebop_Drone_2015-03-10T161049+0000_B0CA63.mp4')
child.expect('ftp> ')
child.sendline('prompt')
child.expect('ftp> ')
child.sendline('mdel *.*')
child.expect('ftp> ')
child.sendline('bye')
# The rest is not strictly necessary. This just demonstrates a few functions.
# This makes sure the child is dead; although it would be killed when Python exits.
if child.isalive():
    child.sendline('bye') # Try to ask ftp child to exit.
    child.close()
# Print the final state of the child. Normally isalive() should be FALSE.
if child.isalive():
    print('Child did not exit gracefully.')
else:
    print('Child exited gracefully.')
