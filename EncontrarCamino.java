//package pkg_main;

import java.util.ArrayList;

public class EncontrarCamino {
	
	private String sMapa;
	
	public EncontrarCamino(String sMapa) {
		this.sMapa = sMapa;
	}
	
	public ArrayList<Nodo> cargarMapa() {
		ArrayList<Nodo> tRet = new ArrayList<Nodo>();
		String[] sData = Funciones.readFile(this.sMapa);
		
		// Recorremos el array
		for (int i = 0; i < sData.length; i++) {
			String[] sCiudad = sData[i].split(";");
			// Cogemos el nombre del padre
			Nodo tNodoPadre = new Nodo(sCiudad[0], -1, null, null);
			// Recorremos el resto de las ciudades
			for (int x = 1; x < sCiudad.length; x++) {
				String sNombre = sCiudad[x].substring(0, sCiudad[x].indexOf("("));
				String sCoste = sCiudad[x].substring(sCiudad[x].indexOf("(") + 1, sCiudad[x].indexOf(")"));

				if (Integer.parseInt(sCoste) > 0) {
					Nodo tNodoHijo = new Nodo(sNombre, Integer.parseInt(sCoste), null, null);
					tNodoPadre.anadirNodoHijo(tNodoHijo);
				}
			}

			tRet.add(tNodoPadre);
		}
		
		return tRet;
	}
}
