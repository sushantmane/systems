package edu.sjsu.cs.systems.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerDemo {


    private static final Logger LOG = LoggerFactory.getLogger(ProducerDemo.class);

    private final Properties kafkaProperties = new Properties();
    private final KafkaProducer<String, String> kafkaProducer;

    public ProducerDemo(String kafkaConnectStr) {
        kafkaProperties.put("bootstrap.servers", kafkaConnectStr);
        kafkaProperties.put("key.serializer", "edu.sjsu.cs.systems.kafka.common.DemoSerializer");
        kafkaProperties.put("value.serializer", "edu.sjsu.cs.systems.kafka.common.DemoSerializer");
        kafkaProperties.put("client.id", "demo-client");
        kafkaProperties.put("allow.auto.create.topics", "false");
        kafkaProperties.put("max.block.ms", 6000);
        kafkaProducer = new KafkaProducer<>(kafkaProperties);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LOG.info("Launching a producer...");
        ProducerDemo demo = new ProducerDemo("sumane-mn1.linkedin.biz:9092");

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("UTP_X0", "Hello", "World");
        long ts = System.currentTimeMillis();
        LOG.info("Calling send api...");
        Future<RecordMetadata> sendResult = demo.kafkaProducer.send(producerRecord, new ProducerResultCallback(producerRecord));

        while (!(sendResult.isDone() || sendResult.isCancelled())) {
            Thread.sleep(50);
        }
        LOG.info("Result - canceled:{} done:{} RMD:{}", sendResult.isCancelled(), sendResult.isDone(), sendResult.get());
        LOG.info("Send call returned in:{}", System.currentTimeMillis() - ts);
    }
}
