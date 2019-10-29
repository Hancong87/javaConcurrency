import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int balance;
    private final Lock lock
            = new ReentrantLock();
    // 转账
    //可能有活锁
    void transfer(Account tar, int amt){
        while (true) {
            if(this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                            break;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer



    // 解决活锁
    void transfer1(Account tar, int amt) {
        while (true) {
            if (this.lock.tryLock()) {//①
                try {
                    try {
                        Thread.sleep(new Random().nextInt(10));//随机睡眠，打个时间差，解决活锁
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (tar.lock.tryLock()) {//②
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                            break;
                        } finally {
                            tar.lock.unlock();
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

    //sleep放回合结束前会更好，没必要每次都sleep
    //放在哪？
}