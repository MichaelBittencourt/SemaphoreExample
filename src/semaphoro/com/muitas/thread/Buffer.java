package semaphoro.com.muitas.thread;

import java.util.concurrent.Semaphore;

public class Buffer {

	private static final Buffer instancia = new Buffer();
	private static final int TAMANHO_ARRAY = 5;
	private static final int POSICAO_LIVRE_INICIAL = 0;
	
	private int[] buffer;
	private int posicaoLivre;
	
	private MySemaphore bufferCheio;
	private MySemaphore bufferVazio;
	private MySemaphore mutex;
	
	private Buffer() {
		buffer = new int[TAMANHO_ARRAY];
		posicaoLivre = POSICAO_LIVRE_INICIAL;
		
		bufferVazio = new MySemaphore(TAMANHO_ARRAY);
		bufferCheio = new MySemaphore(POSICAO_LIVRE_INICIAL);
		mutex = new MySemaphore(1);
	}
	
	public static Buffer getInstancia() {
		return instancia;
	}
	
	public void add(int numero) {
		
		try {
			bufferVazio.acquire();  
			mutex.acquire();
			
			if(posicaoLivre >= TAMANHO_ARRAY) {
				System.out.println("#### Erro durante produção");
			}else {
				buffer[posicaoLivre] = numero;               
				                                        
				System.out.println("Produtor " + //
						Thread.currentThread().getId() + //
						" inseriu o numero: " + numero + //
						" na posição:" + posicaoLivre);
				
				posicaoLivre++;
			}
		} catch (InterruptedException e) {
			System.out.println("#### Erro durante produção");
		}
		
		mutex.release();
		bufferCheio.release();
		
	}
	
	public void remove() {
		try {
			bufferCheio.acquire();
			mutex.acquire();
			
			if(posicaoLivre <= POSICAO_LIVRE_INICIAL) {
				System.out.println("#### Erro durante consumo");
			}else {
				posicaoLivre--;
				
				System.out.println("Consumidor " + //
						Thread.currentThread().getId() + //
						" removeu o numero: " + buffer[posicaoLivre] + //
						" da posição:" + posicaoLivre);
				
				
			}
			
			mutex.release();
			bufferVazio.release();
		} catch (InterruptedException e) {
			System.out.println("#### Erro durante consumo");
		}
		
		
	}
	
}
