package InterfaceGrafica;

import br.ufma.lsdi.ssd.Formatter.Formatter;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Model.ExampleQueries;
import br.ufma.lsdi.ssd.Model.OntologyPrefix;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.StaticModel.StaticModel;
import eu.larkc.csparql.common.RDFTuple;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SmartParking extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane textLista;
    private JTextField textVagas;
    private JButton button1;

    public SmartParking() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new StaticModel().putStaticNamedModel("http://mycsparql.lsdi/smartParking",
                        "examples_files/ParkingRDF.owl");

                String query = new ExampleQueries().getStateFree();

                Query q = new Query.Builder().query(query)
                        .continuos(true)
                        .publisherID("Anderson@lsdi.ufma.br")
                        .build();

                ResultReceiver consulta = new ResultReceiver();
                    consulta.addListener(q, new Listener() {
                    @Override
                    public void update(java.util.Observable o, ArrayList<RDFTuple> rdfTuples) {

                        ArrayList<String> result = new Formatter().toString(rdfTuples);
                        System.out.println("Quantidade de vagas: "+result.size());
                        textVagas.setText(""+result.size());
                        textLista.setText("");
                        for(int i =0; i<result.size();i++){
                            System.out.println("Vagas livres: "+result.get(i));
                            textLista.setText(textLista.getText()+"\n"+result.get(i));
                        }
                        System.out.println("\n");
                    }

                });
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SmartParking dialog = new SmartParking();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
