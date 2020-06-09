package ejercicio2UDP;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class servidorUDP {
	public static String recuperar(String cad,int tam){
		String respuesta ="";
		for(int i=0;i<tam;i++) {
			respuesta +=cad.charAt(i);
		}
		return respuesta;
	}
	
	public static void main(String[] args)throws IOException {
		System.out.println(" contador de palabras-");
		try {
			DatagramSocket socketUDP= new DatagramSocket(5555);
			byte [] mensaje=new byte[20000];
			while(true) {
				DatagramPacket peticion=new DatagramPacket(mensaje,mensaje.length);
				socketUDP.receive(peticion);
				
				
				String res= new String(peticion.getData());
				String x=recuperar(res,peticion.getLength());
			
				StringTokenizer stringTokenizer = new StringTokenizer(x, " ");
				String env ="numero de palabras ->"+stringTokenizer.countTokens();
				
				byte [] enviar= env.getBytes();
				DatagramPacket mensaje1= new DatagramPacket(enviar,env.length(),peticion.getAddress(),peticion.getPort());
				socketUDP.send(mensaje1);
				
				System.out.print("Datos:"+new String(peticion.getData()));
			
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
	}
}
