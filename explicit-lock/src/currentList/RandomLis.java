package currentList;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RandomLis {
	private LinkedList<Integer> lista;
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
	
	public RandomLis() {
		lista = new LinkedList<Integer>();
	}

	public RandomLis(LinkedList<Integer> cloneFrom) {
		lista = new LinkedList<Integer>(cloneFrom);
	}
	
	public void inserir(int item) {
		try {
			rwl.writeLock().tryLock();

			lista.add(item);
			System.out.println(Thread.currentThread().getName() +  " inseriu " + item);

			rwl.writeLock().unlock();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}

	}
	
	public void remover() {
		try {
			rwl.writeLock().tryLock();

			int item = lista.remove(new Random().nextInt(lista.size()));
			System.out.println(Thread.currentThread().getName() + " removeu " + item);

			rwl.writeLock().unlock();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}

	
	public void buscar() {
		try {
			rwl.readLock().tryLock();

			int item = lista.get(new Random().nextInt(lista.size()));
			System.out.println(Thread.currentThread().getName() + " buscou " + item);

			rwl.readLock().unlock();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}

	public void printList() {
		System.out.println("Lista:");
		for(int i = 0; i <lista.size(); i++) {
			System.out.print( "(" + i + ": " + lista.get(i) + "), ");
		}
		System.out.println("\n");
	}
}