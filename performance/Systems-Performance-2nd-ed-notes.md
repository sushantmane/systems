# Notes on Systems Performance 2nd Ed by Brendan Gregg

> Curated with the help of ChatGPT & Co-Pilot

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

### Key Performance Metrics
- Focus on latency, utilization, and saturation.

### Terminology
- **Latency:** The time it takes to service a request.
- **Utilization:** The percentage of time a resource is busy servicing requests.
- **Saturation:** The degree to which a resource has extra work it can't service, often queued.
- **Response Time:** The time it takes to service a request, including queueing delay.
- **Workload:** The amount of work being done by a system.
- **IOPS:** Input/Output Operations Per Second - read/write operations per second.
- **Throughput:** The amount of data read/written per second or the number of operations per second.

### Models
- **SUT:** System Under Test
- **Queueing System Model:** A model of a system with a queue, where requests arrive, wait in a queue, and are serviced by a resource.
```bash
                  Queue
                 +-+-+-+        +---------+        
 Arrival  -----> | | | | -----> |         |
                 +-+-+-+        | Service |
                                | Center  |
 Departure <------------------- |         |
                                +---------+     
```