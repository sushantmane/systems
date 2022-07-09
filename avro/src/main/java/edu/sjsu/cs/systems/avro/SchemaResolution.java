package edu.sjsu.cs.systems.avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SchemaResolution {


    public static void schemaResolutionDemo() throws IOException {
        Schema writersSchema = SchemaBuilder.record("ChunkKey")
                .namespace("edu.sjsu.cs.systems.avro")
                .fields()
                .requiredString("fingerprint")
                .requiredString("hashFunction")
                .requiredLong("size")
                .endRecord();

        Schema readersSchema = SchemaBuilder.record("ChunkKey")
                .namespace("edu.sjsu.cs.systems.avro")
                .fields()
                .requiredString("fingerprint")
                .requiredString("hashFunction")
                .requiredLong("size")
                .nullableString("encrypted", "null")
                .endRecord();

        System.out.println(writersSchema);
        System.out.println(readersSchema);

        GenericRecord oldRecord = new GenericData.Record(writersSchema);
        oldRecord.put("fingerprint", "baymax");
        oldRecord.put("hashFunction", "md5");
        oldRecord.put("size", 42);

        DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<>(writersSchema);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Encoder binEncoder = EncoderFactory.get().binaryEncoder(baos, null);
        datumWriter.write(oldRecord, binEncoder);
        binEncoder.flush();

        byte[] serBytes = baos.toByteArray();
        System.out.println(serBytes.length);

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(writersSchema);
        Decoder binDecoder = DecoderFactory.get().binaryDecoder(serBytes, null);
        GenericRecord newRecord = datumReader.read(null, binDecoder);
        System.out.println(newRecord);


        DatumReader<GenericRecord> datumReader1 = new GenericDatumReader<>(writersSchema, readersSchema);
        Decoder binDecoder1 = DecoderFactory.get().binaryDecoder(serBytes, null);
        GenericRecord newRecord1 = datumReader1.read(null, binDecoder1);
        System.out.println(newRecord1);

    }


    public static void main(String[] args) throws IOException {
        schemaResolutionDemo();
    }
}
