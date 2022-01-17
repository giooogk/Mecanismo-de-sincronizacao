package currentList;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentList {
	public LinkedList<Integer> innerList;
	public final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	public ConcurrentList() {
		innerList = new LinkedList<Integer>();
	}

	public ConcurrentList(LinkedList<Integer> cloneFrom) {
		innerList = new LinkedList<Integer>(cloneFrom);
	}

	// insere um item no final da lista.
	public void inserir(int item) {
		lock.writeLock().lock();
		try {
			innerList.add(item);
			System.out.println(Thread.currentThread().getName() +  " inseriu " + item);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(lock.isWriteLockedByCurrentThread()) {
				lock.writeLock().unlock();
			}
		}

	}

	// remove a primeira ocorrência um item da lista, caso ele esteja presente nela.
	public void remover(int item) {
		lock.writeLock().lock();
		try {

			boolean hasRemoved = innerList.removeFirstOccurrence(item);
			System.out.println(Thread.currentThread().getName() + " removeu? " + hasRemoved);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(lock.isWriteLockedByCurrentThread()) {
				lock.writeLock().unlock();
			}
		}
	}


	public void buscar(int item) {
		lock.readLock().lock();
		try {

			int indexOfItem = innerList.indexOf(item);
			System.out.println(Thread.currentThread().getName() + " buscou " + item + " e retornou " + indexOfItem);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}

	public int sum() {
		int sum = 0;
		for (int val: innerList) {
			sum += val;
		}
		return sum;
	}
}