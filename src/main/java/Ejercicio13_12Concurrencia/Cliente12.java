package Ejercicio13_12Concurrencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente12 {
	public static void main(String[] args) throws IOException {

		//Declaramos las variables que necesitaremos:
		int portNumber = 53000;
		Socket clientsocket = null;
		PrintWriter outclient = null; //donde escribirá para enviar al server
		InputStreamReader inclient = null; 
		BufferedReader inb = null; //para leer la entrada
		
		try 
		{
			clientsocket= new Socket("127.0.0.1", 53000);
			System.out.println("Socket Cliente TCP: " + clientsocket);
			outclient = new PrintWriter(clientsocket.getOutputStream(),true);	
			inclient= new InputStreamReader(clientsocket.getInputStream());
			inb = new BufferedReader(inclient) ;
		}
		
		catch (UnknownHostException e)
		{
			System.err.println("Don't know about host " + "127.0.0.1");
			System.exit(1);
		} 
		catch (IOException e) 
		{
			System.err.println("Couldn't get I/O for the connection to "+ "127.0.0.1");
			System.exit(1);
		}
		
		finally
		{
			System.out.println("  - - - - - Iniciamos el CLIENTE Ejercicio12 - - - - - -" );
			
			//el siguiente paso es escribir en la output del cliete para enviar al servidor:
			try{
				String mensajec="Dame la fecha";
				outclient.println(mensajec);
			}
			catch(Exception e)
			{
				System.out.println("Mensaje imposible de enviar en el CLIENTE");
			}
			
			String inputLine;
			//ahora escuchamos
			for(;;)
			{
				inputLine = inb.readLine();
				if (inputLine != null) //el servidor escucha 
				{
					break;
				}
			}
			
			System.out.println("La fehca es: " + inputLine);
		}
	}
}
