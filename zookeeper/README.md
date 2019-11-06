Zookeeper
=========
A distributed coordination service.

(Following notes are taken from the book:
ZooKeeper
Distributed Process Coordination _by Benjamin Reed, Flavio Junqueira_)

### Basics

* znode (data nodes)
  * may or may not contain data
  * if it contains any data, the data is stored as a byte array.
  * exact format of the byte array is specific to each application and Zookeeper does not directly provide support to parse it.

* **Zookeeper API**
  * `create /path data`
    * creates a znode named with /path and containing data
  * `delete /path`
    * deletes the znode /path
  * `setData /path data`
    * sets the data of znode /path to data
  * `exists /path`
    * checks whether /path exists
  * `getData /path`
    * returns the data in /path
  * `getChildren /path`
    * returns the list of children under /path

* **Modes**
  * persistent
  * ephemeral
  * persistent_sequential
  * ephemeral_sequential

* **Notifications & Watches**
  * Notifications - to receive notifications on changes to znode, client sets a watch on a given znode.
  * Watch
    * one-shot operation -- triggers one notification
    * to receive multiple notifications over time, the client must set a new watch upon receiving each notification.

* **Versions**
  * each znode has a version number associated with it
  * incremented every time its data changes

### Zookeeper Architecture
* Zookeeper client sdk is responsible for the interaction with Zookeeper servers.

* **Zookeeper Quorums**
  * data tree is replicated across all servers in the ensemble
  * `2f + 1`, majority = `f + 1`, can tolerate `f` failures
  * shoot for an odd number of servers (recommended -- even number makes the system more fragile)
* **Sessions**
  * In order to perform any operation, client needs to establish a session with the Zookeeper service
  * If a session ends for any reason, the ephemeral nodes created during the session disappear
  * Sessions offer order guarantees: requests in a session are executed in FIFO order
