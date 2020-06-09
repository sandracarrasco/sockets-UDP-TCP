package ejercicio1TCP;
import java.io.*;
import java.net.*;

public class ClienteTCP {
	public static void main(String[] args)  throws IOException {
	    Socket socketCliente = null;
	    BufferedReader entrada = null;
	    PrintWriter salida = null;
	    System.err.println("Carmela Sandra Calliconde Carrasco \n");
	    System.err.println("juego iniciado para terminar 'Salir' \n");
	   // crearemos un socket en cliente que se enlazara con un servidor y en el mismo puerto
	    
	    try {
	      socketCliente = new Socket("localhost", 4444);
	      // Obtenemos el canal de entrada
	      
	      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      
	      // Obtenemos el canal de salida
	      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
	    } catch (IOException e) {
	    	
		System.err.println("No puede establer canales de E/S para la conexión");
	        System.exit(-1);
	    }
	    //leendo el buffer de entrada
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	    String linea;
	    
	    try {
	    	boolean sw = true;
	      while (sw) {
	    	  System.out.println("Elije [1=Piedra, 2=Papel, 3=Tijera]: ");
	        linea = stdIn.readLine();
	        
	        salida.println(linea);
	        
	        if (linea=="Salir") sw = false ;
	        else {
	        linea = entrada.readLine();
	        System.out.println("Respuesta del servidor: " + linea);
	        // Si es "exit" es que finaliza la comunicación
	        }
	        
	      }
	    } catch (IOException e) {
		System.out.println("IOException: " + e.getMessage());
	    }
	 
	    //liberando recursos
	    salida.close();
	    entrada.close();
	    stdIn.close();
	    socketCliente.close();
	  }
	
}
