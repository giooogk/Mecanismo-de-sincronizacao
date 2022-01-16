import java.util.LinkedList;
import java.util.Random;

public class SimpleLis {
	private LinkedList<Integer> lista;
	private int capacidade;

	public SimpleLis(int capacidade) {
		this.capacidade = capacidade;
		lista = new LinkedList<Integer>();
	}

	public synchronized void inserir(int item) {
		while (lista.size() == capacidade) {
			System.out.println(Thread.currentThread().getName() + " encontrou a fila está cheia");
			System.out.print(Thread.currentThread().getName() + " suspença.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		lista.add(item);
		System.out.println(Thread.currentThread().getName() + " inseriu " + item);
		notify();
	}

	public synchronized void remover() {
		while (lista.size() == 0) {
			System.out.println(Thread.currentThread().getName() + " encontrou a fila vazia");
			System.out.println(Thread.currentThread().getName() + " suspença.");
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
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(i + " = " + lista.get(i));
		}
	}

	public synchronized void buscar() {
		while (lista.size() == 0) {
			System.out.println(Thread.currentThread().getName() + " encontrou a fila vazia");
			System.out.println(Thread.currentThread().getName() + " suspença.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printList();
		notifyAll();// MODO DE LIBERAÇÃO
	}

}