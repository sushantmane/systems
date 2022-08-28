package edu.sjsu.cs.systems.kafka.producer;

public class TestHypothesis {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("this sucks!");
            }
        });


        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            try {
                System.out.println("Hello!");
            } finally {
                System.out.println("World");
            }
        } finally {
            System.out.println("Not that bad world!");
        }
    }
}
