package semaphoro.com.muitas.thread;

public class MySemaphore {
    private static Integer SEMAPHORE_ID_GLOBAL = 0;
    private Integer semaphoreSize;
    private Integer semaphoreId;

    public Integer getSemaphoreSize() {
        return semaphoreSize;
    }

    @Override
    public String toString() {
        return "MySemaphore " + semaphoreId + " size = " + semaphoreSize;
    }

    public MySemaphore(Integer qtd) {
        semaphoreSize = qtd;
        semaphoreId = SEMAPHORE_ID_GLOBAL++;
        //System.out.println("Constructor: " + this);
    }

    public void acquire() throws InterruptedException {
        synchronized(this) {
            while (semaphoreSize == 0) {
                wait();
                /*Thread.sleep(2000);
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }*/
            }
            semaphoreSize--;
            //System.out.println("Acquire: " + this);
        }
    }

    public void release() {
        synchronized(this) {
            semaphoreSize++;
            notify();
            //System.out.println("Release: " + this);
        }
    }
}
