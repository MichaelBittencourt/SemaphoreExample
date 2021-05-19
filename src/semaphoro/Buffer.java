package semaphoro;

import semaphoro.com.muitas.thread.MySemaphore;

import java.util.concurrent.Semaphore;

public class Buffer {

	private static final Buffer instancia = new Buffer();
	private static final int TAMANHO_ARRAY = 5;
	private static final int POSICAO_LIVRE_INICIAL = 0;
	
	private int[] buffer;
	private int posicaoLivre;
	
	private MySemaphore bufferCheio;
	private MySemaphore bufferVazio;
	
	private Buffer() {
		buffer = new int[TAMANHO_ARRAY];
		posicaoLivre = POSICAO_LIVRE_INICIAL;
		
		bufferVazio = new MySemaphore(TAMANHO_ARRAY);
		bufferCheio = new MySemaphore(POSICAO_LIVRE_INICIAL);
	}
	
	public static Buffer getInstancia() {
		return instancia;
	}
	
	public void add(int numero) {
		
		try {
			System.out.println("Antes Acquire: bufferVazio.size = " + bufferVazio.getSemaphoreSize());
			bufferVazio.acquire();
			System.out.println("Depois Acquire: bufferVazio.size" + bufferVazio.getSemaphoreSize());

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
		System.out.println("Antes Release: bufferCheio.size = " + bufferCheio.getSemaphoreSize());
		bufferCheio.release();
		System.out.println("Depois Release: bufferCheio.size = " + bufferCheio.getSemaphoreSize());
		
	}
	
	public void remove() {
		try {
			System.out.println("Antes Acquire: bufferCheio.size = " + bufferCheio.getSemaphoreSize());
			bufferCheio.acquire();
			System.out.println("Depois Acquire: bufferCheio.size = " + bufferCheio.getSemaphoreSize());
			
			if(posicaoLivre <= POSICAO_LIVRE_INICIAL) {
				System.out.println("#### Erro durante consumo");
			}else {
				posicaoLivre--;
				
				System.out.println("Consumidor " + //
						Thread.currentThread().getId() + //
						" removeu o numero: " + buffer[posicaoLivre] + //
						" da posição:" + posicaoLivre);
				
				
			}
			System.out.println("Antes Releaase: bufferVazio.size = " + bufferVazio.getSemaphoreSize());
			bufferVazio.release();
			System.out.println("Depois Release: bufferVazio.size = " + bufferVazio.getSemaphoreSize());
		} catch (InterruptedException e) {
			System.out.println("#### Erro durante consumo");
		}
		
		
	}
	
}
