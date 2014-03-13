package Ejercicio14;

import java.io.*;
import java.net.*;


public class Servidor14 {
	
	public static void main(String[] args) throws IOException{
		
		ServerSocket ServSocket=null;
		Socket ClientSocket=null;
		int puerto=10009, i=0;
		
		try{
			ServSocket= new ServerSocket(puerto);
		}catch(IOException ioe){
			System.err.println("The "+puerto + "is blocked!");
			System.exit(1);
		}
		
		System.out.println("Waiting for connexion ....");
		
		while(true){
			ClientSocket=ServSocket.accept();

			System.out.println("Conecction Accepted");
			System.out.println("EWaiting for Input.....");
			
			ThreadServer cliente = new ThreadServer(ClientSocket,i);
			cliente.start();
			i++;
			
		}
		
	}

}
