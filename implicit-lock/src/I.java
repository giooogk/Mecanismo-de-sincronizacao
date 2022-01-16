
public class I extends Thread{
	
	private SimpleLis listaCompartilhada; 
	
	public I(String nome, SimpleLis lista) {
		super(nome);
		this.listaCompartilhada = lista;
	}
	
	public void run() {
		int item = (int)(Math.random() * 100) + 1;
		listaCompartilhada.inserir(item);
	}
}