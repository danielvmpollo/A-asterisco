/*
Hecho por:
Asomoza Ponce Juan Carlos
Villegas Moreno Zeuxis Daniel
*/
import java.util.ArrayList;

public class Nodo implements Comparable<Nodo> {

	private String sNombre;
	private float fCoste;
	private Nodo tNodoPadre;
	private ArrayList<Nodo> tHijos;

	public Nodo() {
		super();
	}

	public Nodo(String sNombre, float fCoste, Nodo tNodoPadre, ArrayList<Nodo> tHijos) {
		super();
		this.sNombre = sNombre;
		this.fCoste = fCoste;
		this.tHijos = new ArrayList<Nodo>();
	}
	
	public String getNombre() {
		return sNombre;
	}

	public void setNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public float getCoste() {
		return fCoste;
	}

	public void setCoste(float fCoste) {
		this.fCoste = fCoste;
	}

	public Nodo getNodoPadre() {
		return this.tNodoPadre;
	}

	public void setNodoPadre(Nodo tNodoPadre) {
		this.tNodoPadre = tNodoPadre;
	}
	
	public ArrayList<Nodo> getHijos() {
		return this.tHijos;
	}

	public void setHijos(ArrayList<Nodo> tHijos) {
		this.tHijos = tHijos;
	}
	
	public void anadirNodoHijo(Nodo tNodoHijo) {
		this.tHijos.add(tNodoHijo);
	}
	
	public void eliminarNodoHijo(int iPosicion) {
		this.tHijos.remove(iPosicion);
	}

	public Nodo clone() {
		Nodo tNodo = new Nodo(this.sNombre, this.fCoste, this.tNodoPadre, this.tHijos);
		return tNodo;
	}
	
	@Override
	public int compareTo(Nodo tNodo) {
		return (int) this.fCoste - (int) tNodo.getCoste();
	}
}
