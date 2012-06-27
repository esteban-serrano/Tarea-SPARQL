/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaiiiredes.client.GUI;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
//import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.query.*;
import tareaiiiredes.QueryRemoteSparql;

public class SparqlClient extends JFrame {

    private JTextArea queryTextArea;
    private JTextField textField;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public SparqlClient() {
        initComponents();
        //Todo de aqui en adelante en este metodo agregado para comodidad del team:
        queryTextArea.setText
                (   "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
                    +"SELECT ?pais\r\n"
                    +"WHERE {?pais rdf:type <http://dbpedia.org/ontology/Country>}"
                );
        textField.setText("http://dbpedia.org/sparql");
    }

    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente SPARQL");


        queryTextArea = new JTextArea();

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnSendQuery = new JButton("Query");

        JRadioButton rdbtnHttpGet = new JRadioButton("HTTP GET");
        rdbtnHttpGet.setSelected(true);
        buttonGroup.add(rdbtnHttpGet);
        JRadioButton rdbtnHttpPost = new JRadioButton("HTTP POST");
        buttonGroup.add(rdbtnHttpPost);


        GroupLayout layout = new GroupLayout(getContentPane());

        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGap(90).addGroup(
                layout.createParallelGroup(Alignment.TRAILING).addComponent(
                textField,
                Alignment.LEADING,
                263,
                263,
                263).addComponent(
                queryTextArea,
                Alignment.LEADING,
                GroupLayout.DEFAULT_SIZE,
                263,
                Short.MAX_VALUE).addGroup(
                layout.createSequentialGroup().addComponent(
                rdbtnHttpGet).addPreferredGap(
                ComponentPlacement.RELATED,
                72,
                Short.MAX_VALUE).addComponent(
                rdbtnHttpPost)))).addGroup(
                layout.createSequentialGroup().addGap(181).addComponent(
                btnSendQuery))).addGap(95)));

        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGap(19).addComponent(textField,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(queryTextArea,
                GroupLayout.PREFERRED_SIZE, 153,
                GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnSendQuery).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(
                layout.createParallelGroup(
                Alignment.BASELINE).addComponent(rdbtnHttpPost).addComponent(rdbtnHttpGet)).addContainerGap(12, Short.MAX_VALUE)));

        btnSendQuery.addActionListener(
                new queryButtonListener(
                queryTextArea,
                textField,
                buttonGroup,
                rdbtnHttpGet,
                rdbtnHttpPost));

        getContentPane().setLayout(layout);

        // Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        pack();
    }

    class queryButtonListener implements ActionListener {

        JTextArea mqueryTextArea;
        JTextField mEndpointTextField;
        JRadioButton mGetButton;
        JRadioButton mPostButton;

        public queryButtonListener(
                JTextArea textArea,
                JTextField endpointTextField,
                ButtonGroup buttonGroup,
                JRadioButton rdbtnHttpGet,
                JRadioButton rdbtnHttpPost) {
            mqueryTextArea = textArea;
            mEndpointTextField = endpointTextField;
            mGetButton = rdbtnHttpGet;
            mPostButton = rdbtnHttpPost;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof javax.swing.JButton) {
                if (((javax.swing.JButton) e.getSource()).getLabel().equals("Query")) {
                    String query = mqueryTextArea.getText();
                    String dir = mEndpointTextField.getText();
                    try {
                        ResultSet rs = QueryRemoteSparql.getResults(dir, query);
                        while (rs.hasNext()) {
                            String a=rs.next().toString();
                            int init=a.indexOf("<")+1;
                            int end=a.indexOf(">");
                            System.out.println(a.subSequence(init, end));
                        }
                    } catch (Exception e1) {
                        System.out.println("---INICIO Mensaje de excepción---");
                        System.console().printf(e1.toString());
                        System.out.println("---FIN Mensaje de excepción---");
                    }
                }
            }
        }
    }
}