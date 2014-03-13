package Ejercicio14;

import java.io.*;
import java.net.*;

public class Cliente14 {
	
	public static void main(String[] args) throws IOException{
		
	
		String ServerS= new String ("127.0.0.1");
		int puerto= 10009;
		if(args.length>0)
			ServerS=args[0];
		System.out.println("Connecting with   " + ServerS+ " prt "+ puerto);
		
		
		Socket ecoSocket = null;
		
		PrintWriter out=null;

		BufferedReader in = null;
				
		try{
			
			ecoSocket = new Socket (ServerS,puerto);

			out = new PrintWriter ( ecoSocket.getOutputStream(),true);
		
			in = new BufferedReader ( new InputStreamReader ( ecoSocket.getInputStream()));	
		}catch(UnknownHostException uhe){
			System.err.println("Host not found  : "+ ServerS);
			System.exit(1);
		}catch(IOException ioe){
			System.err.println("Not able to take I/O "+ ServerS);
			System.exit(1);
		}
		
		

		String UserOutput;
		

		UserOutput="0";
		out.println(UserOutput);
		System.out.println("Cliente ==> > The data was sended correctly.");
		
		
		System.out.println("Clien ==> Server "+in.readLine());


	
		UserOutput="1";
		out.println(UserOutput);
		System.out.println("Cliente=> Data was sended correctly");
				

		System.out.println("Client ==> Server: "+in.readLine()); 
		
		out.close();
		in.close();
		ecoSocket.close();
		System.out.println("Client == > All is closed");
		
		
	}

}
