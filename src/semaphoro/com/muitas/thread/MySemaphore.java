package semaphoro.com.muitas.thread;

public class MySemaphore {
    private static Integer SEMAPHORE_ID_GLOBAL = 0;
    private Integer semaphoreSize;
    private Integer semaphoreId;
    private SemaphoreCount semaphoreCount;

    public Integer getSemaphoreSize() {
        return semaphoreSize;
    }

    @Override
    public String toString() {
        return "MySemaphore " + semaphoreId + " size = " + semaphoreSize;
    }

    public MySemaphore(Integer qtd) {
        semaphoreSize = qtd;
        semaphoreCount = new SemaphoreCount(semaphoreSize);
        semaphoreId = SEMAPHORE_ID_GLOBAL++;
        //System.out.println("Constructor: " + this);
    }

    public void acquire() throws InterruptedException {
        //synchronized(semaphoreSize) {
            //semaphoreCount.wait();
            while (semaphoreCount.getCount() == 0) {
                //wait();
                /*Thread.sleep(2000);
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }*/
            }
            semaphoreCount.decrement();
            semaphoreSize--;
            //System.out.println("Acquire: " + this);
        //}
    }

    public synchronized void release() {
        //synchronized(semaphoreSize) {
            semaphoreCount.increment();
            semaphoreSize++;
            //notify();
            //System.out.println("Release: " + this);
        //}
    }
}
