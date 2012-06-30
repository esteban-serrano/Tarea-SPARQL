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
    int port = 8002;
    
    public void run() throws IOException
    {
        
                
        // open server socket
        
        try {
            SS = new ServerSocket(port); 
        } catch (IOException e) {
            System.err.println("Could not start server: " + e);
            System.exit(-1);
        }
        System.out.println("FileServer accepting connections on port " + port);
        
         // request handler loop
        while (true) {
            Socket connection = null;
            try {
                // wait for request
                connection = SS.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                PrintStream pout = new PrintStream(out);
             // read first line of request (ignore the rest)
                String request = in.readLine();
                
                
                if (request==null)
                    continue;
                //log(connection, request);
                int i = 0;
                while (true) {                    
                   //Read the http data message
                    String misc = in.readLine();
                    if(i==2)
                        misc = URLDecoder.decode(misc, "UTF-8");
                    System.out.println(misc);
                    i++;
                    
                    if (misc==null || misc.length()==0)
                        break;
                }
                
            }
            finally{}
            
            
        }
    }
    
    public static void main(String args[]) throws IOException {
		Servidor server = new Servidor();
		server.run();
	}
}
