
public class R extends Thread{
private SimpleLis listaCompartilhada; 
	
	public R(String nome, SimpleLis lista) {
		super(nome);
		this.listaCompartilhada = lista;
	}
	
	public void run() {
		listaCompartilhada.remover();
	}
}
