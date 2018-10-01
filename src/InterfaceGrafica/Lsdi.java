package InterfaceGrafica;

import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Formatter.Formatter;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Model.OntologyPrefix;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Model.ResponseQuery;
import br.ufma.lsdi.ssd.StaticModel.StaticModel;
import eu.larkc.csparql.common.RDFTuple;
import org.slf4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class Lsdi {
    private static Logger logger;
    private JLabel controleDeAmbienteDoLabel;
    private JLabel Vtemperatura;
    private JLabel Vumidade;
    private JLabel Vfumaca;
    private JLabel pVtemperatura;
    private JLabel pVumidade;
    private JLabel pVfumaca;
    private JPanel Controle;
    private JButton btStartStop;
    private JLabel Vluminosidade;
    private JLabel pVluminosidade;
    private OntologyPrefix prefix;

    public Lsdi() {
        btStartStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btStartStop.setText("Starting...");
                temperatureControl();
            }
        });
    }

    private void createUIComponents() {

    }

    public static void main(String[] args) {
        //GUI
        JFrame frame = new JFrame("Lsdi");
        frame.setContentPane(new Lsdi().Controle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        logger = new ConfigLog().log(Lsdi.class);
    }

    private void temperatureControl() {

        new StaticModel().putStaticNamedModel("http://streamreasoning.org/roomConnection",
                "examples_files/OntoRDF.owl");

        String query2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX iotlite:<" + prefix.getIotlite() + "> "
                + "PREFIX sosa:<" + prefix.getSosa() + "> "
                + "SELECT ?result "
                + "FROM STREAM <" + prefix.getStreamLsdi() + "> [RANGE 10s STEP 5s] "
                + "WHERE { "
                + "?id iotlite:hasQuatityKind iotlite:Temperature . "
                + "?id sosa:hasResult ?result"
                + " }";

        Query q = new Query.Builder().query(query2).continuos(true)
                .publisherID("Anderson@lsdi.ufma.br").build();

        btStartStop.setText("Starting......");

        ResultReceiver receiver = new ResultReceiver();
        receiver.addListener(q, new Listener() {

            @Override
            public void update(Observable o, ArrayList<RDFTuple> rdfTuples) {
                ArrayList<String> result = new Formatter().toString(rdfTuples);
                updateGUI(result.get(0),1);
            }
        });
    }

    private void updateGUI(String result, int type) {
        new Runnable() {
            @Override
            public void run() {

            }
        };
    }
}
