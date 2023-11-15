# Commands

## Linux

### The /proc Filesystem

Explore valuable system information through the /proc filesystem, a virtual filesystem in Linux that provides access to kernel-related information.

- [Kernel.org - The /proc filesystem](https://docs.kernel.org/filesystems/proc.html)
- [Linux System Administrators Guide - The /proc filesystem](https://tldp.org/LDP/sag/html/proc-fs.html)

#### Key Commands:

```bash
$ cat /proc/cpuinfo       # CPU information
$ cat /proc/meminfo       # Memory information
$ cat /proc/filesystems   # Filesystems supported by the kernel
$ cat /proc/mounts        # Mounted filesystems
$ cat /proc/uptime        # Uptime information
$ cat /proc/version       # Kernel version information
$ cat /proc/cmdline       # Kernel boot command line
$ cat /proc/devices       # Device information
$ cat /proc/diskstats     # Disk statistics
$ cat /proc/loadavg       # Load average
$ cat /proc/modules       # Loaded modules
$ cat /proc/stat          # Various statistics
$ cat /proc/swaps         # Swap space
$ cat /proc/ioports       # I/O ports
$ cat /proc/interrupts    # Interrupts in use
$ cat /proc/net/dev       # Network devices
$ cat /proc/net/arp       # ARP cache
$ cat /proc/net/route     # Routing information
$ cat /proc/net/tcp       # TCP connections
$ cat /proc/net/udp       # UDP connections
$ cat /proc/net/icmp      # ICMP connections
```



### Check the health of a disk

smartctl: smartctl is a command-line utility used to check and monitor the health of storage devices using SMART (Self-Monitoring, Analysis, and Reporting Technology). 

```bash
Copy code
sudo smartctl -a /dev/sdX
```