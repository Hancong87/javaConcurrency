import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class X {
    private final Lock rtl = new ReentrantLock();
    int value;
    public int get() {
        rtl.lock();
        try {
            System.out.println("in get --- ");
            return value;
        } finally {
            rtl.unlock();
        }
    }
    public void addOne() {
        rtl.lock();
        try {
            System.out.println("in addOne --- ");
            value += 1;
        } finally {
            rtl.unlock();
        }
    }
}
