package Ejercicio12UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
  
class ServidorUDP { 
  public static void main(String args[]) throws Exception 
    { 
     try
     { 
      DatagramSocket serverSocket = new DatagramSocket(9876); 
  
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
      byte[] sendData2  = new byte[1024]; 
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
      
		  DatagramPacket receivePacket = 
		             new DatagramPacket(receiveData, receiveData.length); 

		while(true) 
        { 
  
         // receiveData = new byte[1024]; 
         // System.out.println ("Waiting for datagram packet");

          serverSocket.receive(receivePacket); 

         // String sentence = new String(receivePacket.getData()); 
          String sentence2 = new String("la fecha es : "+ date.toString()); 
         // String sentence3= new String("pedo");
  
          InetAddress IPAddress = receivePacket.getAddress(); 
  
          int port = receivePacket.getPort(); 
  
          System.out.println ("From: " + IPAddress + ":" + port);

         // sendData = capitalizedSentence.getBytes();
          sendData2 = sentence2.getBytes();
  
        /*  DatagramPacket sendPacket = 
             new DatagramPacket(sendData, sendData.length, IPAddress,  port); */
          
          DatagramPacket sendPacket2 = 
                  new DatagramPacket(sendData2, sendData2.length, IPAddress,  port);
         // serverSocket.send(sendPacket);
          serverSocket.send(sendPacket2); 

        } 

     }
      catch (SocketException ex) 
      {
        System.out.println("UDP Port 9876 is occupied.");
        System.exit(1);
      }

    } 
} 