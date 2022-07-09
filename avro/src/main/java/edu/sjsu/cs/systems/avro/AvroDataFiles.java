package edu.sjsu.cs.systems.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class AvroDataFiles {

    public static void dataFilesDemo() throws IOException {
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


        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(AvroDataFiles.class.getResourceAsStream("/avro/DeduplicatedRecord/v1/dedupe_record.avsc"));
//        System.out.println(schema.toString(true));


        File file = new File("avro_container1.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(deduplicatedRecord);
        dataFileWriter.append(deduplicatedRecord);
        dataFileWriter.append(deduplicatedRecord);
        dataFileWriter.close();


        // generic record
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader);
        GenericRecord record = null;
        while (dataFileReader.hasNext()) {
            record = dataFileReader.next(record);
            System.out.println(record);
        }

        // specific record
        DatumReader<DeduplicatedRecord> specificDatumReader = new SpecificDatumReader<>();
        DataFileReader<DeduplicatedRecord> dataFileReaderSr = new DataFileReader<>(file, specificDatumReader);
        for (DeduplicatedRecord specificRecord : dataFileReaderSr) {
            System.out.println(specificRecord);
        }
    }

    public static void main(String[] args) throws IOException {
        dataFilesDemo();
    }

}
