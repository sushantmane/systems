# Notes on Systems Performance 2nd Ed by Brendan Gregg

## Chapter 1: Introduction

### Perspectives
- Workload analysis: assess the performance of the workload
- Resource analysis: evaluate the utilization of system resources

### Methodologies
- Linux Perf Analysis in 60 seconds

| # | Tool                | Check                                       |
|---|---------------------|---------------------------------------------|
| 1 | `uptime`            | Load averages to see if load is increasing or decreasing |
| 2 | `dmesg -T`          | Check for Kernel errors |
| 3 | `vmstat -SM 1`      | System-wide statistics: run queue length, swap in/out, CPU usage, memory usage |
| 4 | `mpstat -P ALL 1`   | CPU statistics: per-CPU usage |
| 5 | `pidstat 1`         | Per-process CPU usage: identify unexpected CPU consumers and user/system CPU time |
| 6 | `iostat -sxz 1`     | Disk I/O statistics: IOPS and throughput, average wait time, percent busy |
| 7 | `free -m`           | Memory usage: free, used, buffers, cache |
| 8 | `sar -n DEV 1`      | Network statistics: packets and throughput |
| 9 | `sar -n TCP,ETCP 1` | TCP statistics: retransmits, connection rates |
| 10| `top`               | Check overall system health |



## Chapter 2: Methodologies
