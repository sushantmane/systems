package edu.sjsu.cs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static class IHideInterrupts implements Runnable {

        private CountDownLatch latch = new CountDownLatch(1);
        private Object object = new Object();

        @Override
        public void run() {
            try {
                Thread.currentThread().setName("Spartan");
                try {
                    iCatchInterrupts();
                } catch (Exception ex) {}
                willYouCallMe();
                System.out.println("F: freedom!");
            } catch (Exception e) {
                System.out.println("F: things didn't go as I planned them but that's ok");
                e.printStackTrace();
            }
        }

        private void iCatchInterrupts() throws InterruptedException {
            try {
                System.out.println("F1: Gonna sleep now...");
                Thread.sleep(Long.MAX_VALUE);
            } catch (Exception e) {
                System.out.println("F1: I was interrupted");
                Thread.currentThread().interrupt();   // <==== UNCOMMENT ME AFTER FIRST RUN
                throw e;
            }
        }

        private void willYouCallMe() {
            System.out.println("F2: You did call me :>");
            try {
                System.out.println("F2: I'm just taking a nap!");
//                latch.await();
                synchronized (object) {
                    object.wait(Long.MAX_VALUE);
                }
            } catch (InterruptedException e) {
                System.out.println("F2: Why did you wake me up?");
            }
            System.out.println("F2: We had a good run!");
        }
    }

    public static void main(String[] args) {
        IHideInterrupts iHideInterrupts = new IHideInterrupts();
        executorService.submit(iHideInterrupts);

        try {
            Thread.sleep(1000);
            System.out.println("M: STONITH");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("M: Am I a killer? " + executorService.awaitTermination(100L, TimeUnit.DAYS));
        } catch (InterruptedException e) {
            System.out.println("M: I failed to stop them :(");
        }

        try {
            System.out.println("M: I wonder who all survived?");
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("M: blah");
        }
    }
}
