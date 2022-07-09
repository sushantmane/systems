package edu.sjsu.cs.systems.avro;


import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class AvroSchemaParser {

    public void schemaParseDemo() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(getClass().getResourceAsStream("/avro/DeduplicatedRecord/v1/dedupe_record.avsc"));
//        System.out.println(schema.toString(true));

        GenericRecord chunkKeyRecord = new GenericData.Record(schema.getField("chunkKey").schema());
        chunkKeyRecord.put("fingerprint", "xyz");
        chunkKeyRecord.put("hashFunction", "crc-32");
        chunkKeyRecord.put("size", 32);

        GenericRecord chunkValueRecord= new GenericData.Record(schema.getField("chunkValue").schema());
        chunkValueRecord.put("data", ByteBuffer.wrap("WhatALifeWhatALifeWhatALife".getBytes(StandardCharsets.UTF_8)));


        GenericRecord dedupeRecord = new GenericData.Record(schema);
        dedupeRecord.put("chunkKey", chunkKeyRecord);
        dedupeRecord.put("chunkValue", chunkValueRecord);
        dedupeRecord.put("timestamp", 1657350829560L);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(bos, null);
        datumWriter.write(dedupeRecord, encoder);
        encoder.flush();
        byte[] serBytes = bos.toByteArray();


        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(serBytes, null);
        GenericRecord result = datumReader.read(null, decoder);
        Assert.assertEquals(result.get("timestamp"), 1657350829560L);
        Assert.assertEquals(((GenericRecord)result.get("chunkKey")).get("hashFunction").toString(), "crc-32");
        ByteBuffer byteBuffer = (ByteBuffer) ((GenericRecord)result.get("chunkValue")).get("data");
        Assert.assertEquals(StandardCharsets.UTF_8.decode(byteBuffer).toString(), "WhatALifeWhatALifeWhatALife");
        bos.close();
    }

    public static void main(String[] args) throws IOException {
        AvroSchemaParser avroSchemaParser = new AvroSchemaParser();
        avroSchemaParser.schemaParseDemo();
    }
}
