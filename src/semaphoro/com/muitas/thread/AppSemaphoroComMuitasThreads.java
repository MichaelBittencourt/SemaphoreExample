package semaphoro.com.muitas.thread;

public class AppSemaphoroComMuitasThreads {

	public static void main(String[] args) {
		Buffer buffer = Buffer.getInstancia();
		
		Produtor p1 = new Produtor(buffer);
		Produtor p2 = new Produtor(buffer);
		Produtor p3 = new Produtor(buffer);
		
		
		Consumidor c1 = new Consumidor(buffer);
		Consumidor c2 = new Consumidor(buffer);
		Consumidor c3 = new Consumidor(buffer);
		Consumidor c4 = new Consumidor(buffer);
		Consumidor c5 = new Consumidor(buffer);
		
		System.out.println("executando.... AppSemaphoro cheio das threads");
		
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
	}
}
