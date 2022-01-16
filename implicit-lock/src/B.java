
public class B extends Thread{
	
	private SimpleLis listaCompartilhada; 
	
	public B(String nome, SimpleLis lista) {
		super(nome);
		this.listaCompartilhada = lista;
	}
	
	public void run() {
		
		listaCompartilhada.buscar();
	}
}
