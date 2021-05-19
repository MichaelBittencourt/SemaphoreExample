package semaphoro.com.muitas.thread;

public class SemaphoreCount {

    private Integer count;

    public SemaphoreCount(Integer count) {
        this.count = count;
    }

    public synchronized Integer getCount() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }
}
