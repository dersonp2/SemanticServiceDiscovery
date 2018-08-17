import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Formatter.Formatter;
import br.ufma.lsdi.ssd.Model.OntologyPrefix;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
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
        String query = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX ex:<http://mycsparql.lsdi/> "
                + "SELECT ?s ?p ?o "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 5s STEP 1s] "
                + "WHERE { ?s ?p ?o }";

        String query2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX iotlite:<" + p.getIotlite() + "> "
                + "PREFIX sosa:<" + p.getSosa() + "> "
                + "SELECT ?result "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 10s STEP 5s] "
                + "WHERE { "
                + "?id iotlite:hasQuatityKind iotlite:Temperature . "
                + "?id sosa:hasResult ?result"
                + " }";

        String query3 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX iotlite:<" + p.getIotlite() + "> "
                + "SELECT ?id "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 5s STEP 1s] "
                + "WHERE { ?id iotlite:hasQuatityKind iotlite:Temperature }";

        String query4 = "REGISTER QUERY WhoLikesWhat AS "
                + "PREFIX ex: <" + p.getSosa() + "> "
                + "PREFIX iot: <" + p.getIotlite() + "> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "SELECT ?s ?o "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 5s STEP 1s] "
                + "WHERE { ?s iot:hasQuatityKind ?o }";


        Query q = new Query.Builder().query(query2)
                .continuos(true)
                .publisherID("Anderson@lsdi.ufma.br")
                .build();

        ResultReceiver consulta = new ResultReceiver();
        consulta.addListener(q, new Listener() {
            @Override
            public void update(java.util.Observable o, ArrayList<RDFTuple> rdfTuples) {

              ArrayList<String> result = new Formatter().toString(rdfTuples);
              System.out.println("O valor da temperatura é de: "+result.get(0));

              /*for(int i =0; i<result.size();i++){
                  System.out.println("O valor da temperatura é de: "+result.get(i));
              }*/
            }

        });

    }
}
