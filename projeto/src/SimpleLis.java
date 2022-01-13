import java.util.LinkedList;
import java.util.Random;

public class SimpleLis {
	private int capacidade;
	
	private LinkedList<Integer> lista;
	
	
	public SimpleLis(int capacidade) {
		this.capacidade = capacidade;
		lista = new LinkedList<Integer>();
	}
	
	public synchronized void inserir(int item) {
		while(lista.size() == capacidade) {
			System.out.print("Fila está cheia");
			System.out.print(Thread.currentThread().getName() + " suspença. \n");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lista.add(item);
		System.out.println(Thread.currentThread().getName() +  " inseriu " + item);
		notify();
	}
	
	public synchronized void remover() {
		while (lista.size() == 0) {
			System.out.println("Fila vazia");
			System.out.println(Thread.currentThread().getName() +" suspença. \n");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		        
		int item = lista.remove(numRandom());
		System.out.println(Thread.currentThread().getName() + " removeu " + item);
		notify();  
	}
	
	public int numRandom() {
		int num;
        num = new Random().nextInt(lista.size());

        return num;
	}
	
	public void printList() {
		System.out.println(Thread.currentThread().getName() + " iniciou uma busca.");
		for(int i = 0; i <lista.size(); i++) {
			System.out.println( i + " = " + lista.get(i));
		}
	}
	
	public synchronized void buscar() {
		while (lista.size() == 0) {
			System.out.println("fila vazia");
			System.out.println(Thread.currentThread().getName() + " suspença. \n");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printList();
		notify();//MODO DE LIBERAÇÃO
	}
	
}