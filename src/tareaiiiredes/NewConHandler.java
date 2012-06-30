/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaiiiredes;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;

/**
 *
 * @author Rodrigo
 */
public class NewConHandler implements Runnable {

    Socket m_connection = null;
    int m_clientID = -1;
    boolean m_bRunThread = true;

    NewConHandler(Socket NewCon, int clientID) throws IOException {
        m_connection = NewCon;
        m_clientID = clientID;
        System.out.println("server, client id is: " + m_clientID);

    }
    
    private ResultSet getQueryResults(String host, String query)
    {
        ResultSet rs = null;
        QueryExecution qexec = QueryExecutionFactory.sparqlService(host, query);
        try
        {
            rs = qexec.execSelect();
        }
        catch(Exception e)
        {
            System.out.println("Excepción en ResultSet QueryRemoteSparql.ResultSet en NewConHandler, client id: " + m_clientID + ":");
            System.out.printf(e.toString());
        }
        return rs;
    }
    
    private void sendHTTP200OKResponse(BufferedWriter out, String body)
    {
        try
        {
            out.write("HTTP/1.1 200 OK\r\n");
            out.write(body);
            out.write("\r\n");
        }
        catch (Exception e)
        {
            
        }
    }
    
    private void sendHTTP500InternalServerErrorResponse(BufferedWriter out)
    {
        try
        {
            out.write("HTTP/1.1 500 Internal Server Error\r\n");
            out.write("\r\n");
            out.flush();
        }
        catch (Exception e)
        {
            
        }
    }
    
    
    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in               =  new BufferedReader(new InputStreamReader(m_connection.getInputStream()));
            //OutputStream out =  new BufferedOutputStream(m_connection.getOutputStream());
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(m_connection.getOutputStream(), "UTF8"));
            //PrintStream pout =  new PrintStream(out);
            
            // read first line of request (ignore the rest)
            String request = in.readLine();
            
            if (request.substring(0, 3).equals("POST"))
            {
                int i = 0;
                while (true) {
                    //Read the http data message
                    String misc = in.readLine();
                    if (i == 2) {
                        // Según el número de encabezados definidos en nuestro cliente,
                        // cuando i = 2, misc corresponde al contenido del mensaje,
                        // en este caso, la consulta SPARQL.
                        misc = URLDecoder.decode(misc, "UTF-8");
                        ResultSet r = this.getQueryResults("http://dbpedia.org/sparql", misc);
                        
                        if(r == null)
                        {
                            this.sendHTTP500InternalServerErrorResponse(out);
                            break;
                        }
                        else
                        {
                            this.sendHTTP200OKResponse(out, r.toString());
                        }
                        
                    }
                    System.out.println("[i = " + i + "]\t" + misc);
                    i++;

                    if (misc == null || misc.length() == 0) {
                        break;
                    }
                }
            }
            else if (request.substring(0, 2).equals("GET"))
            {
                
            }
        } catch (IOException ex) {
            Logger.getLogger(NewConHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(NewConHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
