package edu.sjsu.cs.systems.avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class AvroDemo {

    public static Schema getChunkKeySchema() {
        return SchemaBuilder.record("ChunkKey")
                .namespace("edu.sjsu.cs.systems.avro")
                .fields()
                .requiredString("fingerprint")
                .requiredString("hashFunction")
                .requiredLong("size")
                .endRecord();
    }

    public static Schema getChunkValueSchema() {
        return SchemaBuilder.record("ChunkValue")
                .namespace("edu.sjsu.cs.systems.avro")
                .fields()
                .requiredBytes("data")
                .endRecord();
    }

    public static Schema getDeduplicatedRecordSchema() {
        return SchemaBuilder.record("DeduplicatedRecord")
                .namespace("edu.sjsu.cs.systems.avro")
                .fields()
                .name("chunkKey")
                    .type(getChunkKeySchema())
                    .noDefault()
                .name("chunkValue")
                    .type(getChunkValueSchema())
                    .noDefault()
                .requiredLong("timestamp")
                .endRecord();
    }

    private static byte[] serializeDeduplicatedRecordWithJsonEncoder(DeduplicatedRecord deduplicatedRecord) {
        DatumWriter<DeduplicatedRecord> datumWriter = new SpecificDatumWriter<>(DeduplicatedRecord.class);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(DeduplicatedRecord.getClassSchema(), outputStream);
            datumWriter.write(deduplicatedRecord, jsonEncoder);
            jsonEncoder.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static DeduplicatedRecord deserDeduplicatedRecordJson(byte[] data) {
        DatumReader<DeduplicatedRecord> datumReader = new SpecificDatumReader<>(DeduplicatedRecord.class);
        try {
            Decoder decoder = DecoderFactory.get().jsonDecoder(DeduplicatedRecord.getClassSchema(), new String(data));
            return datumReader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] serializeDeduplicatedRecord(DeduplicatedRecord deduplicatedRecord) {
        DatumWriter<DeduplicatedRecord> datumWriter = new SpecificDatumWriter<>(DeduplicatedRecord.class);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Encoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
            datumWriter.write(deduplicatedRecord, binaryEncoder);
            binaryEncoder.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static DeduplicatedRecord deserDeduplicatedRecord(byte[] data) {
        DatumReader<DeduplicatedRecord> datumReader = new SpecificDatumReader<>(DeduplicatedRecord.class);
        try {
            Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
            return datumReader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        ChunkKey chunkKey = ChunkKey.newBuilder()
                .setFingerprint("f261e47e2e5f4692b85e0cbcd8c7da15")
                .setHashFunction("MD5")
                .setSize(32)
                .build();
        ChunkValue chunkValue = ChunkValue.newBuilder()
                .setData(ByteBuffer.wrap("609c237f2bd71600cfd9cc0237fbabbe".getBytes()))
                .build();
        DeduplicatedRecord deduplicatedRecord = DeduplicatedRecord.newBuilder()
                .setChunkKey(chunkKey)
                .setChunkValue(chunkValue)
                .setTimestamp(System.currentTimeMillis())
                .build();

        byte[] serJson = serializeDeduplicatedRecordWithJsonEncoder(deduplicatedRecord);
        byte[] serBin = serializeDeduplicatedRecord(deduplicatedRecord);
        System.out.println(serJson.length + " " + deserDeduplicatedRecordJson(serJson));
        System.out.println(serBin.length + " " + deserDeduplicatedRecord(serBin));
        FileOutputStream fos = new FileOutputStream("tmp.dat");
        fos.write(serJson);
        fos.flush();
        fos.write("linebreak".getBytes());
        fos.write(serBin);
        fos.close();
    }
}
