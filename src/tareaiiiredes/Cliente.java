/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaiiiredes;

import java.io.*;
import java.net.*;
import java.util.*;/**
 *
 * @author Rodrigo
 */
public class Cliente {
    
    int port;
    public String host;
    private Socket socket;
    
    public Cliente(String host,int port){
        this.port=port;
        this.host=host;
    }
    
    public void run()
    {
            // Create a socket without a timeout
        try {
            // This constructor will block until the connection succeeds
            socket = new Socket(host, port);
        } 
        catch (UnknownHostException e)
        {
        } 
        catch (IOException e) {
        } 
      }
    
    public Socket GetSocketStatus(){
        return socket;
    } 
    
    public void sendmessage(String msg)
    {
        try {
            // Construct data
            String data = URLEncoder.encode(msg, "UTF-8");


            //InetAddress addr = InetAddress.getByName(host);

            // Send header
            String path = "/redes/Tarea3/consultaRDF";
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            wr.write("POST "+path+" HTTP/1.0\r\n");
            wr.write("Content-Length: "+data.length()+"\r\n");
            wr.write("Content-Type: query/RDF\r\n");
            wr.write(data);
            wr.write("\r\n");

            // Agregamos data

            wr.flush();

            // Get response
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
        }
    }
}
