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
        
        @Override
	public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(m_connection.getInputStream()));
            OutputStream out = new BufferedOutputStream(m_connection.getOutputStream());
            PrintStream pout = new PrintStream(out);
            // read first line of request (ignore the rest)
            String request = in.readLine();
            if (request != null)
            {
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
