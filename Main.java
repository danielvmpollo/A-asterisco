/*
Hecho por:
Asomoza Ponce Juan Carlos
Villegas Moreno Zeuxis Daniel
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static ArrayList<Nodo> tListaCiudades = new ArrayList<Nodo>();

	public static void main(String[] args) {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		String sLine = null;
		
		try {
			System.out.println("Indica el archivo a leer: ");
			sLine = br.readLine();
			System.out.println();

			if (!new File(sLine).exists()) {
				System.out.println("El archivo no existe!");
				System.exit(0);
			}
			
			EncontrarCamino tPathFinder = new EncontrarCamino(sLine);
			tListaCiudades = tPathFinder.cargarMapa();
			 
			for (int i = 0; i < tListaCiudades.size(); i++) {
				System.out.println("--> " + i + " => " + tListaCiudades.get(i).getNombre());
			}

			System.out.println();
			System.out.println("Indica el numero del origen: ");
			String sOrigen = br.readLine();
			
			System.out.println("Indica el numero del destino: ");
			String sDestino = br.readLine();
			System.out.println();
			
			if (!sOrigen.equals("") && !sDestino.equals("")) {
				System.out.println(" --------------------------COMIENZA LA BUSQUEDA -----------------------\n");

				AAsterisco tStar = new AAsterisco(tListaCiudades, Integer.parseInt(sOrigen), Integer.parseInt(sDestino));
				tStar.calcularRuta();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}