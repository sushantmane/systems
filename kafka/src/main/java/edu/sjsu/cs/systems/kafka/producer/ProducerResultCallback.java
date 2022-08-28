package edu.sjsu.cs.systems.kafka.producer;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class ProducerResultCallback implements Callback {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerResultCallback.class);


    private final ProducerRecord<String, String> producerRecord;
    private final KafkaProducer producer;

    public ProducerResultCallback(KafkaProducer producer, ProducerRecord producerRecord) {
        this.producerRecord = producerRecord;
        this.producer = producer;
    }

    @Override
    public void onCompletion(RecordMetadata rmd, Exception e) {
        long ts = System.currentTimeMillis();
        String es = e != null? "EX" : "";
        LOG.info("####CALLBACK-{}:{} - rec:{} ts:{}", es, producerRecord.key().split("-")[1], producerRecord, ts, e);
        if (e != null) {
            producer.close(Duration.ZERO);
            return;
        }

        System.out.println("Ack - key:" + producerRecord.key() + " ts:" + rmd.timestamp() + " partId:" + rmd.partition() + " offset:" + rmd.offset());
        LOG.info("####ACK##### - key:" + producerRecord.key() + " ts:" + rmd.timestamp() + " partId:" + rmd.partition() + " offset:" + rmd.offset());
    }
}
