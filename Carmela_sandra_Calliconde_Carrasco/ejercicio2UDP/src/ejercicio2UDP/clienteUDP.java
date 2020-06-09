package ejercicio2UDP;

import java.io.*;
import java.net.*;
public class clienteUDP {
	public static void main(String[] args)throws IOException {//
		System.out.println("corriendo contador de palabras\n");
		try {
			DatagramSocket socketUDP= new DatagramSocket();
			int puerto =5555;
			InetAddress host= InetAddress.getByName("localhost");
			BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
			String cad;
			while(true){
			cad=sc.readLine();
			if(cad.equals("0"))break;
			byte [] men=cad.getBytes();
			DatagramPacket peticion=new DatagramPacket(men,cad.length(),host,puerto);
			socketUDP.send(peticion);
			
			byte [] bufer=new byte[10000];
			DatagramPacket mensaje=new DatagramPacket(bufer,bufer.length);
			socketUDP.receive(mensaje);
			System.out.println("servidor:"+new String(mensaje.getData()));
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
