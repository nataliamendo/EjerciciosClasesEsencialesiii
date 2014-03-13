package Ejercicio15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server15 {
	public static void main(String[] args) throws IOException 
	{

		ServerSocket serverSocket = null; // inicializamos la variables
											// Socket dl Server
		Socket clientSocket = null; // aceptar el socket
		PrintWriter outserver = null; // declaramos una variable para escribir
		InputStreamReader inserver = null; // para coger lo que recibe por el
											// socket
		BufferedReader inb = null; // Buffer para leer la entrada del socket -
									// el mensaje que contiene-
		
		//PROTOCOLO
		Protocolo ps = new Protocolo();
		
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
				System.out.println(" - - - - - - - - - - - - - - - - - - -");
				System.out.println(" - - - -| SERVIDOR INICIADO |- - - - -");
				System.out.println(" - - - - - - - - - - - - - - - - - - -");
				String inputLine; // para leer la línea del buffer
				
				for(;;)
				{
					String inputLine1 = inb.readLine();
					if (inputLine1 != null) //el servidor escucha 
					{
						serverSocket.accept();
						System.out.println("Has recibido una petición de socket");
					}
						
						//recibe mensaje -> separar por opción
						String[] m = new String [20];
						m= inputLine1.split(":");
						int op= Integer.parseInt(m[0]); //pasamos el valor a int para poder indentificar mejor 
												// 		que opción quiere realizar el clietne:
						
					if(op != 0)
					{
						
						switch(op)
						{
							case 1:
								//RECIBIMOS: 1:Natalia 
								System.out.println("Mensaje recibido");
								ps.jugadores[ps.numPlays] = m[ps.numPlays+1];
								ps.numPlays++; //aumentamos el número de jugadores
								ps.processInput(ps.PLAY);
								if(ps.numPlays ==1)
								{
									String mnsj1 = ps.mensajeWAIT;
									outserver.println(mnsj1);
								}
								if(ps.numPlays==2)
								{
									String mnsjv1 = ps.mensajeVersus1;
									String mnsjv2 = ps.mensajeVersus2;
									outserver.println(mnsjv1); //al jugador 1
									outserver.println(mnsjv2); //al jugador 2
									
									//a continuación debe envíar a un jugador que es su turno y a otro 'wait'
									String yourbet = ps.mensaje_yourbet;
									String waitbet = ps.mensaje_waitbet;
									outserver.println(yourbet);
									outserver.println(waitbet);
								}
								
								break;
							
							//Envían apuesta:
							case 2:
								int bet = Integer.parseInt(m[1]);
								ps.apuestas[ps.numBets] = bet;
								ps.numBets ++;
								if(ps.numBets == 1)
								{
									//le envía wait al que ha envía bet y al revés
									String mwb= "3:"+ps.jugadores[0];
									String myb = "4:"+ps.jugadores[1];
									outserver.println(mwb);
									outserver.println(myb);
								}
								else if(ps.numBets == 2)
								{
									ps.processInput(ps.YOURBET);
									//ahora envíamos mensaje a los dos jugadores con el ganador:
									outserver.println(ps.mensaje_yourbet);
									outserver.println(ps.mensaje_yourbet);
								}
								
								break;
								
							}
						}
					else
					{
						
						break;
					}
						
					}
				
				
			}
		}
	}
}
