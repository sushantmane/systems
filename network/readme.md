# Commands

## View ARP Table Contents

To observe the mapping between IP addresses and MAC addresses for devices on your local network, use the following command:

```bash
$ arp -na
```

## View Certificate for an Address

To inspect the certificate for a specific address (e.g., example.com on port 443), use the following command:

```bash
$ openssl s_client -connect example.com:443 -servername example.com
```

## Capture Packets on Port

To capture network packets on a specific port (e.g., port 80) and save them to a file (e.g., http.pcap), use the following command:

```bash
$ sudo tcpdump port 80 -w http.pcap
```

## View DNS Trace for a Domain

To trace the DNS (Domain Name System) resolution process for a specific domain (e.g., linkedin.com), use the following command:

```bash
$ dig +trace linkedin.com
```

This command provides a detailed view of the DNS resolution path, showing each step in the process, including the authoritative name servers involved in resolving the domain.


## Perform Traceroute to a Domain

To trace the network path to a specific domain (e.g., linkedin.com), revealing the routers and transit nodes along the way, use the following command:

```bash
$ traceroute linkedin.com
```

This command displays the route that network packets take to reach the destination, helping identify the network infrastructure involved in connecting to the specified domain.