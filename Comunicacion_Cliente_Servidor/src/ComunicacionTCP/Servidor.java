package ComunicacionTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Servidor {
	
	public static void main(String[] args) throws Exception {
		String fraseCliente;
		String fraseMayusculas;
		
		//Creamos un socket Servidor
		ServerSocket socketServidor = new ServerSocket(2021);
		
			//Creamos otro socket para establecer la conexión con cliente
			Socket conexion = socketServidor.accept();
			while(true) {
			//Creamos el input stream del nuevo socket
			BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			//Creamos también el output stream
			DataOutputStream salidaCliente = new DataOutputStream(conexion.getOutputStream());
			
			//Leemos la línea del socket
			fraseCliente = entradaCliente.readLine();
			fraseMayusculas=fraseCliente.toUpperCase()+'\n';
			
			//Escribimos las líneas en el socket
			salidaCliente.writeBytes(fraseMayusculas);
			
			System.out.println("Conectado desde el puerto "+socketServidor.getLocalPort()+". Esperando conexiones de clientes");
			System.out.println("Cliente conectado desde "+conexion.getInetAddress().getHostAddress() + ":"+conexion.getPort()+".");
			System.out.println("SERVIDOR frase del Cliente: " +fraseCliente);
			System.out.println("SERVIDOR respuesta al Cliente:" + fraseMayusculas);
		}
	}
}