package Cliente;

import java.net.InetAddress;
import java.util.Random;

import Clases.TCPconection;
import Clases.UDPconection;

public class ProtocoloCliente {

	protected TCPconection tcp = null;
	protected UDPconection udp = null;
	// udp conecction
	protected int etapa = 0;
	protected int puerto = 1385;
	protected InetAddress ip = null;
	protected int intentos = 0;

	public ProtocoloCliente(String sip, int puerto) {

		try {
			this.ip = InetAddress.getByName(sip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.puerto = puerto;
	}

	public int IniciarTCP() {
		// modelamos el protocolos en 3 estados
		// estado 0 arranque y espera
		// estado 1 contestacion
		// estado 2 cerrar sockets y cerrar

		switch (etapa) {

		case 0:
			System.out.println("Iniciando Cliente TCP");
			try {
				tcp = new TCPconection(ip, puerto);							
				System.out.println("Realizamos peticion de Cliente:");
				String t = "--Â¿Que hora es?---";
				System.out.println(t);
				tcp.Write(t);
				etapa = 1;
			} catch (Exception e) {
				// printamos lasposibles excepciones
				e.printStackTrace();
				System.out.println(" Error en arranque del Socket -- FAIL ");
				intentos++;
				if (intentos == 2) {
					System.out.println("Cancelacion de conexion");
					return (-1);
				}				
			}
			break;

		case 1:
			System.out.println("--Contestacion--");
			System.out.println("Servidor:" + tcp.Read());
			etapa = 2;

			break;

		case 2:
			System.out.println("Cerrar socket cliente");
			tcp.close();
			etapa = 1;
			return (-1);
			// break;

		default:
			System.out.println("Error en el proceso, Protocolo reiniciado.");
			etapa = 0;			
			break;
		}

		return (0);
	}

	public int IniciarUDP() {
		// modelamos el protocolos en 3 estados
		// estado 0 arranque y espera
		// estado 1 contestacion
		// estado 2 cerrar sockets y cerrar

		switch (etapa) {

		case 0:
			System.out.println("Iniciando Cliente UDP");
			udp = new UDPconection(ip, puerto + 1);
			//udp.ArrancarCliente();
			System.out.println("Realizamos peticion de Cliente:");
			String t = "--UAV Conecctivity .. start ...---";
			System.out.println(t);
			udp.writeLineClient(t);
			etapa = 1;
			break;

		case 1:
			System.out.println("****************************");
			System.out.println("Server:" + udp.readLine());
			etapa = 2;

			break;
			
		case 2:
			double velocidad=10;
			Random rn = new Random();
			t = " VelocidadX:"+velocidad*rn.nextDouble()+" VelocidadY: "+velocidad*rn.nextDouble()+" Velocidadz: "+velocidad*rn.nextDouble()+" Positcion:41.274147, 1.997959 Bateria:100 Datos:"+12365897*velocidad*rn.nextDouble()+"EFTWB ";
			System.out.println(t);
			udp.writeLineClient(t);
			etapa = 3;
			break;
			
		case 3:
			System.out.println("*******  WAIT  ******");
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			//añadir aqui el escuchar a los server
			etapa = 2;
			break;

		case 5:
			System.out.println("Cerrar socket cliente");
			udp.close();
			etapa = 1;
			return (-1);
			// break;

		default:
			System.out.println("Error en el proceso, intento :" + intentos
					+ " ...Protocolo reiniciado.");
			etapa = 0;
			intentos++;
			if (intentos == 2) {
				System.out.println("Cancelacion de conexion");
				return (-1);
			}
			break;
		}

		return (0);
	}

}
