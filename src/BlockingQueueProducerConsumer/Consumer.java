package BlockingQueueProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    protected BlockingQueue<Object> queue;

    public Consumer(BlockingQueue<Object> theQueue) {
        this.queue = theQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = queue.take();
                System.out.println("消费者 资源 队列大小 " + queue.size());
                take(obj);
            }
        } catch (InterruptedException ex) {
            System.out.println("消费者 中断");
        }
    }

    void take(Object obj) {
        try {
            Thread.sleep(100); // simulate time passing
        } catch (InterruptedException ex) {
            System.out.println("消费者 读 中断");
        }
        System.out.println("消费对象 " + obj);
    }


}
