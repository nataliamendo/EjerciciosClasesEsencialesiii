package Ejercicio15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente15 {

	static String nomjug = null;

	public static void main(String[] args) {
		// variables sockets:

		// Declaramos las variables que necesitaremos:
		int portNumber = 53000;
		Socket clientsocket = null;
		PrintWriter outclient = null; // donde escribirá para enviar al server
		InputStreamReader inclient = null;
		BufferedReader inb = null; // para leer la entrada

		Protocolo p = new Protocolo();

		try {
			clientsocket = new Socket("127.0.0.1", 53000);
			System.out.println("Socket Cliente TCP: " + clientsocket);
			outclient = new PrintWriter(clientsocket.getOutputStream(), true);
			inclient = new InputStreamReader(clientsocket.getInputStream());
			inb = new BufferedReader(inclient);
		}

		catch (UnknownHostException e) {
			System.err.println("Don't know about host " + "127.0.0.1");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ "127.0.0.1");
			System.exit(1);
		}

		finally {
			System.out.println(" - - - - - - - - - -  - - - - - - - -");
			System.out.println(" - - - -| CLIENTE INICIADO |- - - - -");
			System.out.println(" - - - - - - - -  - - - - - - - - - -");
			System.out.println("Introduce tu NOMBRE");

			Scanner nom = new Scanner(System.in);
			nomjug = nom.nextLine();
			System.out.println("A JUGARRRR");

			Scanner scan = new Scanner(System.in);
			// Le envíamos 1:nombrejugador al servidor
			String mensaje1 = "1:" + nomjug;
			outclient.println(mensaje1);

			try {

				for (;;) {
					String inputLine1 = inb.readLine();
					if (inputLine1 != null) // el servidor escucha
					{
						
						System.out.println("Has recibido una petición de socket");

						// recibe mensaje -> separar por opción
						String[] m = new String[20];
						m = inputLine1.split(":");
						int op = Integer.parseInt(m[0]); 
						// que opción quiere realizar el clietne:

						switch (op) 
						{
							
							case 1:
							break;

							case 2:
								//MENSAJE PARA INDICAR QUE ESPERE A OTRO JUGADOR:
								System.out.println("WAIT");
							break;
							
							case 3:
								//MENSAJE INDICA QUE ESPERE A QUE OTRO JUGADOR PASE SU APUESTA
								System.out.println("WAIT BET");
								break;
								
							case 4:
								//MENSAJE INDICA DAME APUESTA
								System.out.println("Dame tu apuesta");
								Scanner scanbet = new Scanner(System.in);
								String ybet = "2:" + scanbet;
								outclient.println(ybet);
								break;
								
							case 5:
								//PRINTE QUIEN ES EL GANADOR DE LA APUESTA:
								String ganador = m[1];
								System.out.println("GANADOR/A: " + m[1]);
								break;
								
						}

						break;
					}
				}
			} catch (Exception e) {
				System.out.println("ERROR CLIENTE");
			}
			// El cliente inicia escucha del servidor

		}
	}

}
