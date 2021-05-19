package semaphoro;

public class AppSemaphoro {

	public static void main(String[] args) {
		Buffer buffer = Buffer.getInstancia();
		
		Produtor p1 = new Produtor(buffer);
		Consumidor c1 = new Consumidor(buffer);
		
		System.out.println("executando.... AppSemaphoro");
		
		p1.start();
		c1.start();
	}
}
