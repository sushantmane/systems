Zookeeper
=========
A distributed coordination service.


### Basics
* znode - data nodes
* znodes -  may or may not contain data
  * if it contains any data, the data is stored as a byte array.
  * exact format of the byte array is specific to each application and Zookeeper does not directly provide support to parse it.
* Zookeeper API
  * `create /path data`
    * creates a znode named with /path and containing data
  * `delete /path`
    * deletes the znode /path
  * `setData /path data`
    * sets the data of znode /path to data
  *
