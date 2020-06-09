package ejercicio1TCP;
import java.net.*;
import java.io.*;
import java.nio.channels.SocketChannel;


public class ServidorTCP {

	//creando el puerto donde escucharemos 
		public static final int PORT = 4444;
		//metodo para seleccionar generar piedra papel o tijera
		public static String PiedraPapelTijera(String seleccionUsuario) {
	
			  int seleccionCompu = (int)(Math.random()*3+1); // genera un número al azar
			  // entre 1 y 3 La computadora escoge
			  String res ="";
			  switch ( seleccionCompu )
		      {
		         case 1:
		            System.out.println("Piedra");
		            switch ( seleccionUsuario )
		            {
		               case "1": System.out.println("Empate!"); res ="Es empate!"+"...Cpu eligio 'Piedra'";break;
		               case "2": System.out.println("Usted gana!");res ="Usted gana!"+"...Cpu eligio 'Piedra'";break;
		               case "3": System.out.println("La computadora gana!");res ="La computadora gana!"+"...Cpu eligio 'Piedra'"; break;
		            }
		            break;
		 
		         case 2:
		            System.out.println("Papel");
		            switch ( seleccionUsuario )
		            {
		               case "1": System.out.println("La computadora gana!"); res ="La computadora gana!"+"...Cpu eligio 'Papel'"; break;
		               case "2": System.out.println("Empate!"); res ="Es empate!"+"...Cpu eligio 'Papel'"; break;
		               case "3": System.out.println("Usted gana!"); res ="Usted gana!"+"...Cpu eligio 'Papel'"; break;
		            }
		            break;
		 
		         case 3:
		            System.out.println("Tijera");
		            switch ( seleccionUsuario )
		            {
		               case "1": System.out.println("Usted gana!"); res ="Usted gana!"+"...Cpu eligio 'Tijera'"; break;
		               case "2": System.out.println("La computadora gana!"); res ="La computadora gana!"+"...Cpu eligio 'Tijera'"; break;
		               case "3": System.out.println("Empate!"); res ="Es empate!"+"...Cpu eligio 'Tijera'"; break;
		            }
		            break;
		            
		      }
			  return res;
		}
		
		  public static void main(String[] args) throws IOException {
		    // establece el puerto en el que escucha peticiones
		    ServerSocket socketServidor = null;
		    try {
		    	//creando el servidor con el puerto en que escuchara
		      socketServidor = new ServerSocket(PORT);
		    } catch (IOException e) {
		    	
		      System.out.println("No se puede acceder al puerto: " + PORT);
		      System.exit(-1);
		    }
	      //creando el socket cliente
		    Socket socketCliente = null;
		   
		    BufferedReader entrada = null;
		    // creando donde  mostraremos 
		    PrintWriter salida = null;
	       ///mensaje muestra en q puerto y direccion escucha el servidor
		    System.out.println("Escuchando: " + socketServidor+"\n--");
		    try {
		    	
		    	while(true) {
			     
			      socketCliente = socketServidor.accept();
			      //mesaje de conexion aceptada del cliente
			      System.out.println("Connexión acceptada: "+ socketCliente+"\n--");
			      //muestra el puerto en el que esta el cliente
			      System.out.println(socketCliente.getPort()+"\n***");
			      
			      
			      // establece un canal de entrada
			      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			      // establece un canal de salida
			      salida = new PrintWriter(new BufferedWriter(new 
				  OutputStreamWriter(socketCliente.getOutputStream())),true);
			      
			      boolean sw = true;
			      // hace eco de lo que le proporciona el cliente, hasta que recibe "Salir"
			      while (sw) {  
			        String palabra = entrada.readLine();
			        if (palabra.equals("Salir")) sw = false;
			           else {
				       //enviamos la respuesta al metodo
			        	String cad = PiedraPapelTijera(palabra);
				    	        	   
				        //enviando respuesta al cliente
				        System.out.println("Cliente: " +palabra);
				        salida.println(cad);
			        }
		        
			    }
		    }

		    } catch (IOException e) {
		      System.out.println("IOException: " + e.getMessage());
		    }   
		    //liberando recursos
		    salida.close();
		    entrada.close();
		    socketCliente.close();
		    socketServidor.close();
		  }	
	
}
