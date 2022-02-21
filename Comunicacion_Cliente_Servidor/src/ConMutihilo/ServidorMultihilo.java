package ConMutihilo;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultihilo {
	public static void main(String[] args) throws Exception {
	
	ServerSocket socketServidor = new ServerSocket(2021);
	Socket conexion;
	
	while(true) {
		conexion = socketServidor.accept();
		
		Thread hilo = new Thread(new Hilo(conexion));
		hilo.start();
	}
	
	}
}
