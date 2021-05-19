package disincronizada;

public class AppDisincronizado {

	public static void main(String[] args) {
		Buffer buffer = Buffer.getInstancia();
		
		Produtor p1 = new Produtor(buffer);
		Consumidor c1 = new Consumidor(buffer);
		
		p1.start();
		c1.start();
	}
}
