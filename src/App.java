import BlockingQueueProducerConsumer.Consumer;
import BlockingQueueProducerConsumer.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public static void main( String[] args ) throws InterruptedException {
//        Date data = new Date();
//        System.out.println(data);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dataStr = sdf.format(data);
//        System.out.println(dataStr);
//
//
//        try {
//            Date date = sdf.parse("2019-10-23 09:57:38");
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        X x = new X();
//        for (int i = 0; i < 3; i++) {
//            Thread thread = new Thread(() -> x.addOne());
//            thread.start();
//        }
//
//        System.out.println("in main ---" + x.get());


        /*
            阻塞队列实现生产者-消费者模式
         */
        int numProducers = 4;
        int numConsumers = 3;

        BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>(5);

        for (int i = 0; i < numProducers; i++) {
            new Thread(new Producer(myQueue)).start();
        }

        for (int i = 0; i < numConsumers; i++) {
            new Thread(new Consumer(myQueue)).start();
        }

        Thread.sleep(1000);

        System.exit(0);
    }
}
