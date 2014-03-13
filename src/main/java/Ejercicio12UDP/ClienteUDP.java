package Ejercicio12UDP;

import java.io.*;
import java.net.*;

class ClienteUDP {
	public static void main(String[] args) {

		try {
			String serverHostname = new String("127.0.0.1");

			if (args.length > 0)
				serverHostname = args[0];

			BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));

			DatagramSocket clientSocket = new DatagramSocket();

			InetAddress IPAddress = InetAddress.getByName(serverHostname);
			System.out.println("- - - - - CLIENTE INICIADO - - - - -");

			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];

			String hora = "¿Qué hora es?";
			sendData = hora.getBytes();

			System.out.println("Sending data to " + sendData.length
					+ " bytes to server.");
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, 9876);

			clientSocket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);

			System.out.println("Waiting for return packet");
			clientSocket.setSoTimeout(10000);

			try {
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());

				InetAddress returnIPAddress = receivePacket.getAddress();

				int port = receivePacket.getPort();

				System.out.println("From server at: " + returnIPAddress + ":"
						+ port);
				System.out.println("Message: " + modifiedSentence);

			} catch (SocketTimeoutException ste) {
				System.out.println("Timeout Occurred: Packet assumed lost");
			}

			clientSocket.close();
		} catch (UnknownHostException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}