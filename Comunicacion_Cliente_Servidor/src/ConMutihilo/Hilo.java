package ConMutihilo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Hilo extends Thread {

	String fraseCliente;
	String fraseMayusculas;
	Socket conexion;

	public Hilo(Socket conexion) {
		this.conexion = conexion;
	}

	public void run() {
		// Creamos otro socket para establecer la conexión con cliente
		while (true) {
			try {
				// Creamos el input stream del nuevo socket
				BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

				// Creamos también el output stream
				DataOutputStream salidaCliente = new DataOutputStream(conexion.getOutputStream());

				// Leemos la línea del socket
				fraseCliente = entradaCliente.readLine();
				fraseMayusculas = fraseCliente.toUpperCase() + '\n';

				// Escribimos las líneas en el socket
				salidaCliente.writeBytes(fraseMayusculas);

				System.out.println(
						"Conectado desde el puerto " + conexion.getLocalPort() + ". Esperando conexiones de clientes");
				System.out.println("Cliente conectado desde " + conexion.getInetAddress().getHostAddress() + ":"
						+ conexion.getPort() + ".");
				System.out.println("SERVIDOR frase del Cliente: " + fraseCliente);
				System.out.println("SERVIDOR respuesta al Cliente:" + fraseMayusculas);
			} catch (Exception e) {
				//e.printStackTrace();
				
				System.out.println("Cliente desconectado");
				break;
			}
			
		}
		try {
			conexion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conexion.isClosed()) {
			System.out.println("Servidor esperando nuevo cliente...");
		}
		
	}
}
