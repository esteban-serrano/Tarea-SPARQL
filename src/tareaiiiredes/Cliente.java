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
    
    public String sendRequest(String dir, String msg, String format)
    {
        String mensajeRespuesta = "No se ha recibido la respuesta.";
        
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            
            // Codificar la consulta
            String data = URLEncoder.encode(msg, "UTF-8");

            //InetAddress addr = InetAddress.getByName(host);

            // Agregar request
            wr.write("POST "+dir+" HTTP/1.0\r\n");
            
            // Agregar encabezados
            wr.write("Content-Length: "+data.length()+"\r\n");
            wr.write("Content-Type: text/"+format+"\r\n");
            wr.write("\r\n");
            
            // Agregar cuerpo
            wr.write(data);
            wr.write("\r\n");

            // Enviar
            wr.flush();

            
            // Obtener respuesta
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            mensajeRespuesta = URLDecoder.decode(this.getMessage(rd), "UTF-8");
            
            // Cerrar los buffered r/w
            wr.close();
            rd.close();
        }
        catch (Exception e)
        {
            System.out.println("Excepción en String sendRequest en Cliente:");
            System.out.println(e.getMessage());
        }
        
        return mensajeRespuesta;
    }
    
    private String getMessage(BufferedReader br)
    {
        try{
            String line = br.readLine();
            
            int indexPrimerEspacio = line.indexOf(' ');
            int indexSegundoEspacio = line.indexOf(' ', indexPrimerEspacio + 1);
            
            int statusCode = Integer.parseInt(line.substring(indexPrimerEspacio + 1, indexSegundoEspacio));
            
            if(statusCode != 200)
            {
                return "Error al recibir la respuesta. Código HTTP: " + statusCode;
            }
            else
            {
                int i = 1;
                int blankLineCounter = 0;
                StringBuilder builder = new StringBuilder();
                
                while ((line = br.readLine()) != null)
                {
                    // Se ignoran los headers
                    if (line.length() == 0)
                        blankLineCounter++;
                    else if (blankLineCounter == 1)
                    {
                        // Acumular las líneas de la respuesta HTTP
                        builder.append(line).append("\r\n");
                    }
                    else if (blankLineCounter == 2)
                    {
                        // Indicador de final de la respuesta
                        break;
                    }
                    i++;
                    System.out.println("[i = " + i + "]\t" + line);
                }
                
                return builder.toString();
            }
        }
        catch(Exception e){
            System.out.println("Excepción en String getMessage en Cliente:");
            System.out.println(e.getMessage());
            return "Error al recibir la respuesta.";
        }
    }
}
