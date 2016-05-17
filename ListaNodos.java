//package pkg_main;

import java.util.ArrayList;

public class ListaNodos {
	
	private ArrayList<Nodo> tListaNodos = new ArrayList<Nodo>();
	
	public void anadirNodo(Nodo tNodo) {
		this.tListaNodos.add(tNodo);
	}
	
	public void eliminarNodo(Nodo tNodo) {
		this.tListaNodos.remove(tNodo);
	}
	
	public Nodo getNodo(int iIndex) {
		return this.tListaNodos.get(iIndex);
	}
	
	public void setNodo(int iIndex, Nodo tNodo) {
		this.tListaNodos.set(iIndex, tNodo);
	}
	
	public ArrayList<Nodo> getListaNodos() {
		return this.tListaNodos;
	}
	
	public int getSize() {
		return this.tListaNodos.size();
	}
	
	public Nodo getPrimerNodo() {
		return this.tListaNodos.get(0);
	}
	
	public Nodo getUltimoNodo() {
		return this.tListaNodos.get(this.tListaNodos.size() -1);
	}
}
