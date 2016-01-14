#!/bin/bash (source!)
#
# LX800 boot profile
#

hostname lx800.local

# set the keyboard
kbdrate -r 30 -d 250 >dev/null
loadkeys us >/dev/null

# set locale
export LANG=en_US
export LC_ALL=en_US
export TZ="Europe/Bucharest"

# mount the r/w filesystem (only after this, writing to local filesystem works)
mount -t tmpfs ram /var
rsync -ar /var.template/ /var
touch /var/{run/utmp,log/wtmp}

srv clock up

# start logging on the r/w filesystem (TODO: see if any of these can be started sooner!)
srv klogd up
srv bootlogd up
srv syslogd up
#srv smartd up
#srv sensord up
#srv upsd up

srv net.if.lo up

srv sshd up
srv ntpd1 up

# backup storage
#hdparm -W0 /dev/sdb >/dev/null
#srv mount.backup1 up
#hdparm -W0 /dev/sdc >/dev/null
#srv mount.backup2 up
#srv mount.root.local.bk up

