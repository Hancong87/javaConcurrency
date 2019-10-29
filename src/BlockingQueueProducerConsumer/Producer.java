package BlockingQueueProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    protected BlockingQueue<Object> queue;

    public Producer(BlockingQueue<Object> theQueue) {
        this.queue = theQueue;
    }

    @Override
    public void run() {
        while (true) {
            Object justProduced = getResource();
            try {
                queue.put(justProduced);
                System.out.println("生产者资源队列大小= " + queue.size());
            } catch (InterruptedException e) {
                System.out.println("生产者 中断");
            }
        }
    }

    Object getResource() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("生产者 读 中断");
        }
        return  new Object();
    }
}
