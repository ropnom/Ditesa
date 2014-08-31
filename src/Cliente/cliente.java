package Cliente;

import java.util.Scanner;

public class cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("********************");
		System.out.println(" ** Programa UAV ** ");
		System.out.println("********************");
		ProtocoloCliente a = new ProtocoloCliente("127.0.0.1",
				52000);

		System.out.println("Introduzca el protocolo que desea:");
		System.out.println("--- TCP pulse '0' ");
		System.out.println("--- UDP pulse '1' ");
		Scanner scan = new Scanner(System.in);

		int protocolo = 0;
		try {
			protocolo = scan.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (protocolo == 0) {
			System.out.println("-------------------");
			System.out.println(" PROTOCOLO TPC  ");
			System.out.println("-------------------");
			while (a.IniciarTCP() == 0) {
				// While infito realiza peticion e imprime resolucion
				// finalizado salta y finaliza
			}
		} else {
			System.out.println("-------------------");
			System.out.println(" PROTOCOLO UDP  ");
			System.out.println("-------------------");

			while (a.IniciarUDP() == 0) {
				// While infito realiza peticion e imprime resolucion
				// finalizado salta y finaliza
			}
		}
		System.out.println("*******************");
		System.out.println(" Cliente cerrado ");
		System.out.println("*******************");

	}

}
