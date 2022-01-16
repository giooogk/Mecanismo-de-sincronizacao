package threads;

import currentList.ConcurrentList;
//import currentList.RandomLis;

public class I extends Thread{

	private ConcurrentList listaCompartilhada;
	private final int item;

	public I(String nome, ConcurrentList lista, int item) {
		super(nome);
		this.item = item;
		this.listaCompartilhada = lista;
	}
	
	public void run() {
		listaCompartilhada.inserir(item);
	}
}