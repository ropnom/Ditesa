package Server;

import Clases.TCPconection;
import Clases.UDPconection;

public class ProtocoloServer {

	protected TCPconection tcp = null;
	protected UDPconection udp = null;

	protected int etapa = 0;
	protected int puerto = 0;

	public ProtocoloServer(int puerto) {

		this.puerto = puerto;
	}

	public int IniciarTCP() {
		// modelamos el protocolos en 3 estados
		// estado 0 arranque y espera
		// estado 1 contestacion
		// estado 2 cerrar sockets y cerrar

		switch (etapa) {

		case 0:
			System.out.println("Iniciando Servidor TCP");
			tcp = new TCPconection(puerto);
			System.out.println(" Esperando clientes ... ");
			tcp.ArrancarServer();
			etapa = 1;
			break;

		case 1:
			String mensaje = "Conexion Establecida.... OK";
			System.out.println(mensaje);
			tcp.Write(mensaje);
			etapa = 2;
			break;

		case 2:
			mensaje = tcp.Read();
			System.out.println("UAV: "+mensaje);
			// check de cuando salir;
			etapa = 2;
			break;

		case 3:
			System.out.println("Cerrar socket server");
			tcp.close();
			etapa = 1;
			return (-1);
			// break;

		default:
			System.out.println("Error en el proceso, Protocolo reiniciado.");
			etapa = 1;
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
			System.out.println("Iniciando Servidor UDP");
			udp = new UDPconection(puerto + 1);
			System.out.println(" Esperando clientes ... ");
			udp.ArrancarServer();
			etapa = 1;
			break;

		case 1:
			String mensaje = "Ejemplo de envio server UDP";
			System.out.println(mensaje);
			udp.writeLineServer(mensaje);
			etapa = 2;
			break;

		case 2:
			mensaje = udp.readLine();
			System.out.println("UAV: "+mensaje);
			// check de cuando salir;
			etapa = 2;
			break;

		case 3:
			System.out.println("Cerrar socket server");
			udp.close();
			etapa = 1;
			return (-1);
			// break;

		default:
			System.out.println("Error en el proceso, Protocolo reiniciado.");
			etapa = 1;
			break;
		}

		return (0);
	}

}
