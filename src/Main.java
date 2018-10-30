import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Formatter.Formatter;
import br.ufma.lsdi.ssd.Model.OntologyPrefix;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
import br.ufma.lsdi.ssd.StaticModel.StaticModel;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    private static Logger logger = null;
    private static String[] result;

    public static void main(String[] args) {
        logger = new ConfigLog().log(Main.class);
        consulta();
    }

    public static void consulta() {

        OntologyPrefix p = new OntologyPrefix();
        String query2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 1s STEP 1s] "
                + "FROM <http://mycsparql.lsdi/smartParking> "
                + "WHERE { "
                + "?s pk:hasState pk:Free "
                + "} ";

        //new StaticModel().putStaticNamedModel("http://mycsparql.lsdi/smartParking",
                //"examples_files/ParkingRDF.owl");

        Query q = new Query.Builder().query(query2)
                 .continuos(true)
                .publisherID("Anderson@lsdi.ufma.br")
                .build();

        ResultReceiver consulta = new ResultReceiver();
        consulta.addListener(q, new Listener() {
            @Override
            public void update(java.util.Observable o, ArrayList<RDFTuple> rdfTuples) {

              ArrayList<String> result = new Formatter().toString(rdfTuples);
                System.out.println("Quantidade de vagas: "+result.size());
              for(int i =0; i<result.size();i++){
                    System.out.println("Vagas livres: "+result.get(i));
                }
                System.out.println("\n");
            }

        });
    }

    public static void Querys(){
        OntologyPrefix p = new OntologyPrefix();


        String queryp1 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s ?p ?obj "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 10s STEP 5s] "
                + "WHERE { "
                + "?s pk:hasState pk:Busy "
                + "} ";

        String queryp2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s ?vehicle "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 10s STEP 5s] "
                + "FROM <http://streamreasoning.org/roomConnection> "
                + "WHERE { "
                + "?s pk:hasState pk:Free ."
                + "?s pk:hasVehicleSpace pk:CarSpace "
                + "} ";

        String queryp3 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 5s STEP 1s] "
                + "FROM <http://streamreasoning.org/roomConnection> "
                + "WHERE { "
                + "?s pk:hasState pk:Free ."
                + "?s pk:hasVehicleSpace pk:MotorcycleSpace "
                + "} ";
    }

}

