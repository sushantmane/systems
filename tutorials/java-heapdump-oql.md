OQL for Java Heap Dump Analysis
================================



1. Get all strings in heap dump
```
select s from java.lang.String s
```

2. Get all strings containing "heartbeat" as a substring
```
select s from java.lang.String s where s.toString().contains("heartbeat")
```