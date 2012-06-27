/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SparqlOutput.java
 *
 * Created on Jun 26, 2012, 7:54:13 PM
 */
package tareaiiiredes.client.GUI;

import com.hp.hpl.jena.query.*;

/**
 *
 * @author Esteban
 */
public class SparqlOutput extends javax.swing.JFrame {

    String query;
    String resultado;
    String url;
    long time;
    
    /** Creates new form SparqlOutput */
    public SparqlOutput(String url, String query, long t, ResultSet result) {
        initComponents();
        this.query=query;
        this.url=url;
        System.out.println("Formateando el resultado...");
        resultado=resultToString(result);
        System.out.println("Formato listo.");
        time=t;
        showTab0();
    }
    
    private void showTab0(){
        textFieldTiempoTotalEj.setText(time + " ms");
        textAreaConsulta.setText(query);
        textFieldURL.setText(url);
    }
    
    private void showTab1(){
        textAreaResultado.setText(resultado);
    }
    
    private String resultToString(ResultSet r) {
        String output = "";
        while (r.hasNext()) {
            output = output + r.next().toString() + "\r\n";
        }
        return output;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelDetalles = new javax.swing.JPanel();
        textFieldTiempoTotalEj = new java.awt.TextField();
        textAreaConsulta = new java.awt.TextArea();
        textFieldURL = new java.awt.TextField();
        labelURL = new java.awt.Label();
        labelConsulta = new java.awt.Label();
        labelTiempoTotalEj = new java.awt.Label();
        jPanelResultado = new javax.swing.JPanel();
        textAreaResultado = new java.awt.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resultado de la consulta");

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        textFieldTiempoTotalEj.setEditable(false);

        textAreaConsulta.setEditable(false);

        textFieldURL.setEditable(false);

        labelURL.setText("URL de la BD RDF:");

        labelConsulta.setText("Consulta:");

        labelTiempoTotalEj.setText("Tiempo total:");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textAreaConsulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(textFieldURL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(labelURL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(labelTiempoTotalEj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldTiempoTotalEj, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                    .addComponent(labelConsulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTiempoTotalEj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldTiempoTotalEj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelURL.getAccessibleContext().setAccessibleName("URL de la BD RDF");

        jTabbedPane1.addTab("Detalles", jPanelDetalles);

        textAreaResultado.setEditable(false);
        textAreaResultado.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanelResultadoLayout = new javax.swing.GroupLayout(jPanelResultado);
        jPanelResultado.setLayout(jPanelResultadoLayout);
        jPanelResultadoLayout.setHorizontalGroup(
            jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textAreaResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );
        jPanelResultadoLayout.setVerticalGroup(
            jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textAreaResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Resultado", jPanelResultado);

        jTabbedPane1.setSelectedIndex(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int a= jTabbedPane1.getSelectedIndex();
        if(jTabbedPane1.getSelectedIndex()==0){
            showTab0();
        }
        else if(jTabbedPane1.getSelectedIndex()==1){
            showTab1();
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelResultado;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.Label labelConsulta;
    private java.awt.Label labelTiempoTotalEj;
    private java.awt.Label labelURL;
    private java.awt.TextArea textAreaConsulta;
    private java.awt.TextArea textAreaResultado;
    private java.awt.TextField textFieldTiempoTotalEj;
    private java.awt.TextField textFieldURL;
    // End of variables declaration//GEN-END:variables
}
