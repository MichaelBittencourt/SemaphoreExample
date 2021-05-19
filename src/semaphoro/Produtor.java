package semaphoro;

import java.util.Random;

public class Produtor extends Thread {

	private Buffer buffer;
	private Random gerandorNumeroAleatorio;
	
	public Produtor(Buffer buffer) {
		this.buffer = buffer;
		gerandorNumeroAleatorio = new Random();
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			}catch (Exception e) {
			}
			
			int numero = gerandorNumeroAleatorio.nextInt(100);
			buffer.add(numero);
		}
	}
	
	
}
