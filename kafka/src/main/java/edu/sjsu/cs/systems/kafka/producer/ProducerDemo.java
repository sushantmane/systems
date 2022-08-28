package edu.sjsu.cs.systems.kafka.producer;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProducerDemo {


    private static final Logger LOG = LoggerFactory.getLogger(ProducerDemo.class);

    public static final String KAFKA_URL = "sumane-mn1.linkedin.biz:9092";
    public static final String TOPIC_NAME = "TopicEx" + System.currentTimeMillis();
    public static final ExecutorService executorService = Executors.newFixedThreadPool(2);
    public final KafkaProducer<String, byte[]> kafkaProducer;


    public ProducerDemo(boolean autoCreateTopic) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", KAFKA_URL);
        kafkaProperties.put("key.serializer", "edu.sjsu.cs.systems.kafka.common.DemoSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        kafkaProperties.put("client.id", "demo-client");
        kafkaProperties.put("allow.auto.create.topics", autoCreateTopic);
        kafkaProperties.put("enable.idempotence", false);
        kafkaProperties.put("max.in.flight.requests.per.connection", 1);
        kafkaProperties.put("acks", "all");
        kafkaProperties.put("retries", 1);
        kafkaProperties.put("max.block.ms", 20000);
        kafkaProperties.put("request.timeout.ms", 350);
        kafkaProperties.put("delivery.timeout.ms", 10000);
        kafkaProperties.put("linger.ms", 0);
        kafkaProperties.put("batch.size", 512);
        kafkaProperties.put("buffer.memory", 1200);
        kafkaProperties.put("metadata.max.idle.ms", 5000);
        kafkaProperties.put("metadata.max.age.ms", 5000);

        kafkaProducer = new KafkaProducer<>(kafkaProperties);
    }

    public static class CreateTopic implements Runnable {

        public final AdminClient client;
        public Boolean fireCreate = false;
        private final Lock lock;
        private final Condition condition;

        public CreateTopic(Lock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
            Properties kafkaAdminProperties = new Properties();
            kafkaAdminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            client = KafkaAdminClient.create(kafkaAdminProperties);
        }

        @Override
        public void run() {
            Thread.currentThread().setName("create-topic-thread");
            LOG.info("##### create-topic-instance - ls topics:{}", client.listTopics());

            while (true) {
                lock.lock();
                while (!fireCreate) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                fireCreate = false;
                lock.unlock();


                NewTopic topic = new NewTopic(TOPIC_NAME, 1, (short) 1);
                CreateTopicsResult result = client.createTopics(List.of(topic));
                KafkaFuture<Void> future = result.values().get(TOPIC_NAME);

                LOG.info("creating a topic.....");
                try {
                    while (!future.isDone()) {
                        Thread.sleep(50);
                    }
                    LOG.info("topic create future:{} done:{} futureCancelled:{}", future, future.isDone(), future.isCancelled());
                } catch (InterruptedException ex) {
                    LOG.info("got an exception when creating topic: ", ex);
                    ex.printStackTrace();
                }
                ListTopicsResult listTopicsResult = client.listTopics();
                Future<Collection<TopicListing>> lsFuture = listTopicsResult.listings();
                try {
                    lsFuture.get();
                    LOG.info("post create lst:{}", lsFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                LOG.info("<---EXIT create topic thread...");
            }
        }
    }

    public static class DeleteTopic implements Runnable {

        public final AdminClient client;
        public Boolean fireDelete = false;
        private final Lock lock;
        private final Condition condition;

        public DeleteTopic(Lock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
            Properties kafkaAdminProperties = new Properties();
            kafkaAdminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
            client = KafkaAdminClient.create(kafkaAdminProperties);
            LOG.info("delete-topic-instance - ls topics:{}", client.listTopics());
        }


        @Override
        public void run() {
            Thread.currentThread().setName("delete-topic-thread");
            LOG.info("XXX delete-topic-instance - ls topics:{}", client.listTopics());

            lock.lock();
            while (!fireDelete) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
            LOG.info("XXX deleting a topic - ts:{}", System.currentTimeMillis());
            DeleteTopicsResult result = client.deleteTopics(List.of(TOPIC_NAME));
            KafkaFuture<Void> future = result.topicNameValues().get(TOPIC_NAME);
            try {
                while (!future.isDone()) {
                    Thread.sleep(50);
                }
                LOG.info("XXX topic deleted - ts:{}", System.currentTimeMillis());
            } catch (InterruptedException ex) {
                LOG.info("XXX failed to delete topic- XXX");
                ex.printStackTrace();
            }
            ListTopicsResult listTopicsResult = client.listTopics();
            Future<Collection<TopicListing>> lsFuture = listTopicsResult.listings();
            try {
                lsFuture.get();
                LOG.info("XXX post delete lst:{}", lsFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            LOG.info("<---EXIT delete topic thread-->");
        }
    }



    public static void main(String[] args) {
        LOG.info("Launching a producer... ###### topic:{} ######", TOPIC_NAME);

        Lock ctLock = new ReentrantLock();
        Condition ctCond = ctLock.newCondition();
        CreateTopic ctRun = new CreateTopic(ctLock, ctCond);
        Lock dtLock = new ReentrantLock();
        Condition dtCond = dtLock.newCondition();
        DeleteTopic dtRun = new DeleteTopic(dtLock, dtCond);

        executorService.submit(ctRun);
        executorService.submit(dtRun);

        ctLock.lock();
        LOG.info("#WAKEUP CT thread...");
        ctRun.fireCreate = true;
        ctCond.signal();
        ctLock.unlock();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }


        byte[] bytes = new byte[128];
        new Random().nextBytes(bytes);
        System.out.println(bytes.length);

        ProducerDemo demo = new ProducerDemo(false);
        for (int i = 1; i <= 150000; i++) {
            long ts = System.currentTimeMillis();
            ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<>(TOPIC_NAME, 0, ts, "key-" + i, bytes);
            LOG.info("###SEND:{} ts:{}", i, ts);
            try {
                Future<RecordMetadata> sendResult = demo.kafkaProducer.send(producerRecord,
                        new ProducerResultCallback(demo.kafkaProducer, producerRecord));
                LOG.info("###SENT:{}", i);
            } catch (Exception e) {
                LOG.error("Exception while sending message for: {}", i, e);
            }

            if (i == 700) {

                dtLock.lock();
                LOG.info("#WAKEUP DT thread...");
                dtRun.fireDelete = true;
                dtCond.signal();
                dtLock.unlock();

            }

        }

        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
        }

        dtLock.lock();
        LOG.info("#WAKEUP DT thread...");
        dtRun.fireDelete = true;
        dtCond.signal();
        dtLock.unlock();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        while (!(sendResult.isDone() || sendResult.isCancelled())) {
//            Thread.sleep(50);
//        }
//        LOG.info("Result - canceled:{} done:{} RMD:{}", sendResult.isCancelled(), sendResult.isDone(), sendResult.get());
    }
}
