### Protocol Buffers

> Google's data interchange format


Protocol buffers are flexible, efficient, automated solution to serialize and retrieve structured data.




#### Sample

```proto3
syntax = "proto3";

package addressbook;

option java_package = "edu.sjsu.cs.addressbook";
option java_multiple_files = true;

message Person {
    string name = 1;
    int32 id = 2;
    string email = 3;

    enum PhoneType {
        MOBILE = 0;
        HOME = 1;
        WORK = 2;
    }

    message PhoneNumber {
        string number = 1;
        PhoneType type = 2;
    }

    repeated PhoneNumber phones = 4;
}

message AddressBook {
    repeated Person people = 1;
}
```
