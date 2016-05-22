/*
Hecho por:
Asomoza Ponce Juan Carlos
Villegas Moreno Zeuxis Daniel
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AAsterisco {

	private String entrada = null;
	private Scanner teclado = new Scanner(System.in);
	private Nodo tNodoOrigen;
	private Nodo tNodoDestino;
	private Nodo tNodoActual;
	private ArrayList<Nodo> tListaCiudad = new ArrayList<Nodo>();
	private ArrayList<Nodo> tListAux = new ArrayList<Nodo>();
	private ArrayList<Nodo> tListaEliminados = new ArrayList<Nodo>();
	private ListaNodos tListaNodos = new ListaNodos();

	public AAsterisco(ArrayList<Nodo> tListaCiudad, int iOrigen, int iDestino) {
		super();
		this.tListaCiudad = tListaCiudad;
		this.tNodoOrigen = this.tListaCiudad.get(iOrigen);
		this.tNodoDestino = this.tListaCiudad.get(iDestino);

		copiarArrays(this.tListaCiudad, this.tListAux);
	}

	public void calcularRuta() {
		float fCosteFijo = 0;
		
		this.tNodoActual = this.tNodoOrigen;
		this.tNodoActual.setCoste(0);
		this.tListaNodos.anadirNodo(this.tNodoActual);
		
		while (tListaNodos.getSize() != 0) {
			//try{
				// Ordenamos la lista
				Collections.sort(this.tListaNodos.getListaNodos());
				// Cogemos el primer elemento de la lista
				this.tNodoActual = this.tListaNodos.getPrimerNodo();
				
				System.out.println("Lista Actual (Nodos no revisados): ");

				for (int i = 0; i < this.tListaNodos.getListaNodos().size(); i++) {
					System.out.println(" + " + this.tListaNodos.getListaNodos().get(i).getNombre() + " - " + 
												this.tListaNodos.getListaNodos().get(i).getCoste());
				}
				
				System.out.println();

				//Thread.sleep(2000);
				entrada = teclado.nextLine();

				// Si el nodo actual es el destino
				if (this.tNodoActual == this.tNodoDestino) {
					printResultado();
					break;
				}

				// Eliminamos el nodo actual de la lista
				tListaNodos.eliminarNodo(this.tNodoActual);
				// Anadimo el nodo a la lista de eliminados
				tListaEliminados.add(this.tNodoActual);

				System.out.println("Nodo Actual (Nodo elegido): " + this.tNodoActual.getNombre() + " - " + this.tNodoActual.getCoste());

				// Recorremos sus hijos
				for (int i = 0; i < this.tNodoActual.getHijos().size(); i++) {
					// Cogemos el hijo actual
					Nodo tNodoHijo = this.tNodoActual.getHijos().get(i);
					
					// Comprobamos si el hijo ya existe en la lista de eliminados
					if (existeNodoEliminados(tNodoHijo) == true) {
						continue;
					}

					// Lo identificamos como padre
					tNodoHijo = getNodoAsPadre(tNodoHijo);

					if (tNodoHijo == null)
						continue;

					// Cogemos el coste fijo del padre y le sumamos al coste del hijo
					fCosteFijo = this.tNodoActual.getCoste() + this.tNodoActual.getHijos().get(i).getCoste();	
					
					System.out.println(" - Nodo Hijo: " + tNodoHijo.getNombre() +  " => Distancia: " + this.tNodoActual.getHijos().get(i).getCoste());
					
					Nodo tNodoAux = this.tNodoActual.getHijos().get(i);
					tNodoAux.setCoste(fCosteFijo);
					// Verificamos si existen duplicados, o rutas mas cortas
					int iRet = existeOptimo(tNodoAux);
					
					if (iRet == -2) { // Si no existe ningun nodo con el mismo nombre
						// Aplicamos el coste final
						tNodoHijo.setCoste(fCosteFijo);
						// Asignamos al padre
						tNodoHijo.setNodoPadre(this.tNodoActual);
						// Anadimos a la lista
						this.tListaNodos.anadirNodo(tNodoHijo);
					} else if (iRet >= 0) { // Si existe algun nodo con distancia mayor
						this.tListaNodos.setNodo(iRet, tNodoAux);
					}
				}
				
				System.out.println();
				//Thread.sleep(2000);
				entrada = teclado.nextLine();

			//}catch(InterruptedException e){}
		}
	}

	private Nodo getNodoAsPadre(Nodo tNodoHijo) {
		for (int i = 0; i < this.tListAux.size(); i++) {
			if ((this.tListAux.get(i).getCoste() == -1) && (this.tListAux.get(i).getNombre().equals(tNodoHijo.getNombre()))) {
				return this.tListaCiudad.get(i);
			}
		}

		return null;
	}

	private Boolean existeNodoEliminados(Nodo tNodo) {
		for (int i = 0; i < this.tListaEliminados.size(); i++) {
			if (this.tListaEliminados.get(i).getNombre().equals(tNodo.getNombre())) {
				return true;
			}
		}

		return false;
	}
	
	private int existeOptimo(Nodo tNodo) {
		for (int i = 0; i < this.tListaNodos.getSize(); i++) {
			if (this.tListaNodos.getNodo(i).getNombre().equals(tNodo.getNombre())) {
				if (this.tListaNodos.getNodo(i).getCoste() <= tNodo.getCoste()) {
					return -1;
				}
				
				return i;
			} 
		}
		
		return -2;
	}
	
	private String getRutaCompleta(Nodo tNodo) {
		String sRet = "";
		ArrayList<Nodo> tListaAux = new ArrayList<Nodo>();

		while (tNodo.getNodoPadre() != null) {
			tListaAux.add(tNodo);
			tNodo = tNodo.getNodoPadre();
		}
		
		Collections.reverse(tListaAux);
		sRet = this.tNodoOrigen.getNombre();
		
		for (int i = 0; i < tListaAux.size(); i++) {
			sRet += " => " + tListaAux.get(i).getNombre();
		}

		return sRet;	
	}
	
	private void copiarArrays(ArrayList<Nodo> tSrc, ArrayList<Nodo> sDest) {
		for (Nodo tNodo : tSrc)
			sDest.add(tNodo.clone());
	}
	
	private void printResultado() {
		System.out.println();
		System.out.println("Origen: " + this.tNodoOrigen.getNombre());
		System.out.println("Destino: " + this.tNodoActual.getNombre());
		System.out.println("Distancia Total: " + this.tNodoActual.getCoste() + "");
		System.out.println("Ruta Completa: " + getRutaCompleta(this.tNodoActual));
	}
}
