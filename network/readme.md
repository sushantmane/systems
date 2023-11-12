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