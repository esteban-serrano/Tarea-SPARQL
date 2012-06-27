/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaiiiredes;

import tareaiiiredes.client.GUI.SparqlClient;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author orlando
 */
public class TareaIIIRedes {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.console().printf(ex.toString());
        } catch (InstantiationException ex) {
            System.console().printf(ex.toString());
        } catch (IllegalAccessException ex) {
            System.console().printf(ex.toString());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.console().printf(ex.toString());
        }
        //</editor-fold>
        
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new SparqlClient().setVisible(true);
            }
        });
    }
}
