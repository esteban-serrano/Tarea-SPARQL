/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaiiiredes;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Rodrigo
 */
public class Servidor {
    
    ServerSocket SS;
    Socket NewCon = null;
    int port = 8003;
    int id = -1;
    
    public void run() throws IOException
    {
        // Abrir socket de servidor
        
        try {
            SS = new ServerSocket(port); 
        } catch (IOException e) {
            System.err.println("No se pudo iniciar el servidor: " + e);
            System.exit(-1);
        }
        System.out.println("Servidor HTTP aceptando conexiones en el puerto: " + port);
        
         // Loop de request handlers
        while (true) {
            
            try {
                // Esperar un request
                NewCon = SS.accept();
                
                // Manejar la conexión en un nuevo thread
                new Thread(new NewConHandler(NewCon, id++)).start();
            }
            finally{}   
        }
    }
    
    public static void main(String args[]) throws IOException {
		Servidor server = new Servidor();
		server.run();
	}
}
