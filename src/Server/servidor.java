package Server;

import java.util.Scanner;

public class servidor {

	public static void main(String[] args) {

		int puerto = 52000;
		Scanner scanIn;

		System.out.println("*******************");
		System.out.println(" Servidor iniciado ");
		System.out.println("*******************");
		System.out.println("Indique puerto a utilizar [ENTER default]");

		// Seleccionde puerto del servidor
		scanIn = new Scanner(System.in);
		String entrada = scanIn.nextLine();
		if (!entrada.equals("")) {
			try {
				puerto = Integer.parseInt(entrada);
			} catch (Exception e) {
				e.printStackTrace();
				puerto = 52000;
			}
		}

		// Arancamos el servidor
		ProtocoloServer p = new ProtocoloServer(puerto);
		System.out.println("----------------------------");
		System.out.println("  * SERVER STARTING.....  *  ");
		System.out.println("----------------------------");

		System.out.println("Introduzca el protocolo  '0' UDP '1' TCP [Dedafult UDP]:");
		entrada = scanIn.nextLine();
		scanIn.close();
		if (entrada != null && entrada == "0") {
			System.out.println("----------------------------");
			System.out.println(" PROTOCOLO TPC CONCURRENTE ");
			System.out.println("----------------------------");

			// arrancamos el modo escucha del servidor
			while (p.IniciarTCP() == 0) {
				// esto es unbucle infinito
				// cuando acaba el protocolo
				// el valor retornado cambia a -1
			}

			System.out.println("*******************");
			System.out.println(" Servidor cerrado ");
			System.out.println("*******************");

		} else {

			System.out.println("----------------------------");
			System.out.println(" PROTOCOLO UDP CONCURRENTE ");
			System.out.println("----------------------------");

			// arrancamos el modo escucha del servidor
			while (p.IniciarUDP() == 0) {
				// esto es unbucle infinito
				// cuando acaba el protocolo
				// el valor retornado cambia a -1
			}

			System.out.println("*******************");
			System.out.println(" Servidor cerrado ");
			System.out.println("*******************");
			
		}
	}
}
