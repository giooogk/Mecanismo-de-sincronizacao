
public class Main {
	
	private static final int CAPACIDADE = 100;
	private static final int NUM_THREADS = 5;

	public static void main(String[] args) {
		
		SimpleLis lista = new SimpleLis(CAPACIDADE);
		
		I[] inserir = new I[NUM_THREADS];
		B[] buscar = new B[NUM_THREADS];
		R[] remover = new R[NUM_THREADS];
		
		for(int i = 0; i < NUM_THREADS; i++) {
			inserir[i] = new I("Inserir" + (i+1), lista);
			buscar[i] = new B("Buscar" + (i+1), lista);
			remover[i] = new R("Remover" + (i+1), lista);
			
		}
		
		for(int i = 0; i < NUM_THREADS; i++) {
			inserir[i].start();
			buscar[i].start();
			remover[i].start();
			
		}
		
		try {
			for(int i = 0; i < NUM_THREADS; i++) {
				inserir[i].join();
				buscar[i].join();
				remover[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
