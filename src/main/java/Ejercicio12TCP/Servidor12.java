package Ejercicio12TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor12 {
	public static void main(String[] args) throws IOException {

		
		ServerSocket serverSocket = null; // inicializamos la variables
											// Socket dl Server
		Socket clientSocket = null; // aceptar el socket
		PrintWriter outserver = null; // declaramos una variable para escribir
		InputStreamReader inserver = null; // para coger lo que recibe por el
											// socket
		BufferedReader inb = null; // Buffer para leer la entrada del socket -
									// el mensaje que contiene-
		
		try 
		{
			serverSocket = new ServerSocket(53000);
			clientSocket = serverSocket.accept();
			outserver = new PrintWriter(clientSocket.getOutputStream(), true);
			inserver = new InputStreamReader(clientSocket.getInputStream());
			
			
			inb = new BufferedReader(inserver);
			
		} 
		catch (IOException e) 
		{
			System.out.println("Exception caught when trying to listen on port " + 530000 + " or listening for a connection");
			System.out.println(e);
		}
		finally
		{
			{
				System.out.println("Iniciado SERVIDOR");
				String inputLine; // para leer la línea del buffer
				for(;;)
				{
					inputLine = inb.readLine();
					if (inputLine != null) //el servidor escucha 
					{
						String mensaje = "11/03/2014 Hora: 19:26";
						outserver.println(mensaje);
						break;
					}
				}
				
			}
		}
	}
}
