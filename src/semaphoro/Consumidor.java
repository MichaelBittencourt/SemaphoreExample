package semaphoro;

public class Consumidor extends Thread {

	private Buffer buffer;
	
	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			}catch (Exception e) {
			}
			
			buffer.remove();
		}
	}
}
