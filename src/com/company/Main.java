
package com.company;



import java.util.concurrent.CountDownLatch;



public class Main {

    private static final int TOURIST = 15;

    private static CountDownLatch LATCH;

    public static void main(String[] args) throws InterruptedException {

        LATCH = new CountDownLatch(TOURIST +1);

        for (int i = 1; i <= TOURIST; i++) {

            Guide guide = new Guide(i);

            guide.start();

            Thread.sleep(2000);

        }


        while (LATCH.getCount() > 1) {

            Thread.sleep(1000);

        }

        Thread.sleep(1000);

        System.out.println("Готово");

        LATCH.countDown();

    }
    public static class Guide extends Thread {

        private int hasClients;



        public Guide(int clients) {

            this.hasClients = clients;

        }

        @Override

        public void run() {

            try {

                System.out.printf("Туристр %d в автобусе \n",

                        hasClients);

                LATCH.countDown();
                LATCH.await();

                Thread.sleep(2000);

                System.out.printf("Турист %d покинул автобус\n", hasClients);

            } catch (InterruptedException ex) {

            }

        }

    }

}