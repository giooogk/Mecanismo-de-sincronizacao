package threads;

import currentList.ConcurrentList;

public class B extends Thread{
	
	private ConcurrentList listaCompartilhada;
	private final int item;

	public B(String nome, ConcurrentList lista, int item) {
		super(nome);
		this.item = item;
		this.listaCompartilhada = lista;
	}
	
	public void run() {
		listaCompartilhada.buscar(item);
	}
}
