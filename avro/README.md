Avro
=====
> A language neutral data serialization system


- Data is described using schema
- Schema is required for both writing and reading data
  - This allows compact encoding since field data is not tagged with an identifier
- Schemas are usually written in Json
- 
- Supported types
```avro
# primitive
null - the absence of a value - "null"
boolean - a binary balue - "boolean"
int - 32-bit signed number - "int"
long - 64-bit signed number - "long"
float - single precision (32 bit) float - "float"
double - double precision (64 bit) float - "dobule"
bytes - sequence of 8-bit unsigned bytes - "bytes"
string - sequence of unicode characters - "string"

# complex
* array - ordered collection of objects
{"type": "array", "items": "long"} // objects must have the same schema

* map - unordered collection of key-value pairs (keys must be string and values may be any type)
{"type":"map", "values":"string"} // values must have the same schema

* record - a collection of named fields of any type
{
    "type": "record",
    "name": "WeatherRecord",
    "doc": "A weather reading",
    "fields": [
        {"name": "year", "type": "int"},
        {"name": "temp", "type": "int"},
        {"name": "stationId", "type": "string"}
    ]
}

* enum - a set of named values
{
    "type": "enum",
    "name": "workDays",
    "doc": "Working days of week",
    "symbols": ["MONDAY", "TUESDAY", "FRIDAY"]
}

* fixed - a fixed number of 8-bit unsigned bytes
{
    "type": "fixed",
    "name": "md5",
    "size": 16
}

* union - a union of schemas. Represented by a JSON array, where each element in the array is schema.
- Data represented by the union must match one of the schemas in the union
[
    "null",
    "string",
    {"type": "map", "values": "string"}
]

```

* Generic mapping - when schema is not know ahead of time
* Specific mapping - when schema is available before reading or writing data
* Reflection


### Avro tool
```bash

# print schema of an avro file
java -jar avro-tools-1.11.0.jar getschema ../gh/systems/avro/avro_container1.avro 


# print avro file data in json format
 java -jar avro-tools-1.11.0.jar tojson ../gh/systems/avro/avro_container1.avro
```



### Schema Resolution 

Schema resolution of records (Source: Hadoop - The Definitive Guide)


| New schema | Writer | Reader | Action |
| ---------- | ------ | ------ | ------- |
| Added field | Old	| New | The reader uses the default value of the new field, since it is not written by the writer.|
| Added field | New	| Old | The reader does not know about the new field written by the writer, so it is ignored (projection).|
| Removed field | Old	| New	| The reader ignores the removed field (projection).|
| Removed field | New	| Old	| The removed field is not written by the writer. If the old schema had a default defined for the field, the reader uses this; otherwise, it gets an error. In this case, it is best to update the reader’s schema, either at the same time as or before the writer’s.|


#### Aliases
* Handy tip to evolve schema with name change: use  the name aliases. 
  It allows you the use of different names in the readers and writes schema.
* Aliases are used to translate the writer's schema to reader's schema, but they are not available to the reader.
  eg: in `eastern` and `western` were names of the fields in the old schema. New (readers) schema use `east` and `west`.
```json
{
  "type": "record", 
  "name": "MainCityInDirection", 
  "doc": "Name of the city in the given direction",
  "fields": [
    {"name":  "east", "type": "string", "aliases":  "eastern"},
    {"name":  "west", "type": "string", "aliases":  "western"}
  ]
}
```