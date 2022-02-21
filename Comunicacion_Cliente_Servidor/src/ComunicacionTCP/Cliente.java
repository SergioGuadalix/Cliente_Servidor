package ComunicacionTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String frase;
		String frasemodificada;
		
		//Creamos el socket de la clase en la que estamos, el cliente
		Socket socketcliente = new Socket("127.0.0.1",2021);
		
		for(int i=0; i<=10;i++) {
		
			//Creamos el input Stream
			BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));
			
			//Creamos el output stream para sacar información del socket
			DataOutputStream salidaServidor = new DataOutputStream(socketcliente.getOutputStream());
			
			//Creamos un input stream del socket
			BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socketcliente.getInputStream()));
			
			System.out.println("Cliente conectado desde "+socketcliente.getLocalPort());
			System.out.println("Frase para enviar a nuestro amigo el Servidor");
			
			frase = "Un gran poder conlleva una gran responsabilidad";
			
			
			//Enviar cada línea al servidor
			salidaServidor.writeBytes(frase + '\n');
			System.out.println("");
			
			//Leer una línea del servidor
			frasemodificada = entradaServidor.readLine();
			System.out.println("Del Servidor con cariño: "+frasemodificada);
		
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		socketcliente.close();
	}
}
