import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Model.OntologyPrefix;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Interfaces.Listener;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
import org.slf4j.Logger;

public class Main {

    private static Logger logger = null;

    public static void main(String[] args) {
        logger = new ConfigLog().log(Main.class);
        consulta();
    }

    public static void consulta(){

        OntologyPrefix p = new OntologyPrefix();
        String query = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX ex:<http://mycsparql.lsdi/> "
                + "SELECT ?s ?p ?o "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 5s STEP 1s] "
                + "WHERE { ?s ?p ?o }";

        String query2 ="REGISTER QUERY MhubSemantic AS "
                + "PREFIX iotlite:<"+p.getIotlite()+"> "
                + "PREFIX sosa:<"+p.getSosa()+"> "
                + "SELECT ?result "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 5s STEP 1s] "
                + "WHERE { "
                + "?id iotlite:hasQuatityKind iotlite:Temperature . "
                + "?id sosa:hasResult ?result"
                + " }";

        String query3 ="REGISTER QUERY MhubSemantic AS "
                + "PREFIX iotlite:<"+p.getIotlite()+"> "
                + "SELECT ?id "
                + "FROM STREAM <http://mycsparql.lsdi/stream> [RANGE 5s STEP 1s] "
                + "WHERE { ?id iotlite:hasQuatityKind iotlite:Temperature }";


        Query q = new Query.Builder().query(query3)
                .continuos(true)
                .publisherID("Anderson@lsdi.ufma.br")
                .build();

        ResultReceiver consulta = new ResultReceiver();
        consulta.addListener(q, new Listener() {
            @Override
            public void update(String obj) {
            logger.info(obj);
            }
        });
    }
}
