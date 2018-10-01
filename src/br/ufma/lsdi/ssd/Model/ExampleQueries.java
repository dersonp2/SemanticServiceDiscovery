package br.ufma.lsdi.ssd.Model;

public class ExampleQueries {
    OntologyPrefix p = new OntologyPrefix();


    public String getStateFree() {
        String q = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s ?p ?obj "
                + "FROM STREAM <" + p.getPk() + "> [RANGE 10s STEP 5s] "
                + "WHERE { "
                + "?s pk:hasState pk:Free "
                + "} ";
        return q;
    }

    public String getStateBusy() {
        String q = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s ?p ?obj "
                + "FROM STREAM <" + p.getPk() + "> [RANGE 10s STEP 5s] "
                + "WHERE { "
                + "?s pk:hasState pk:Busy "
                + "} ";
        return q;
    }

    public String getStateCarFree() {
        String query2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 5s STEP 5s] "
                + "FROM <http://mycsparql.lsdi/smartParking> "
                + "WHERE { "
                + "?s pk:hasState pk:Free ."
                + "?s pk:hasVehicleSpace pk:CarSpace "
                + "} ";
        return query2;
    }

    public String getStateMotorcycleFree() {
            String query2 = "REGISTER QUERY MhubSemantic AS "
                    + "PREFIX pk:<" + p.getPk() + "> "
                    + "SELECT ?s "
                    + "FROM STREAM <"+ p.getPk() +"> [RANGE 5s STEP 5s] "
                    + "FROM <http://mycsparql.lsdi/smartParking> "
                    + "WHERE { "
                    + "?s pk:hasState pk:Free ."
                    + "?s pk:hasVehicleSpace pk:MotorcycleSpace "
                    + "} ";
            return query2;
    }

    public String getStateCarFreeElderlySpace() {

        String query2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 5s STEP 5s] "
                + "FROM <http://mycsparql.lsdi/smartParking> "
                + "WHERE { "
                + "?s pk:hasState pk:Free ."
                + "?s pk:hasVehicleSpace pk:CarSpace ."
                + "?s pk:hasPreferentialSpace pk:ElderlySpace "
                + "} ";
        return query2;
    }

    public String getStateCarFreeElderlySpaceSnackBar() {

        String query2 = "REGISTER QUERY MhubSemantic AS "
                + "PREFIX pk:<" + p.getPk() + "> "
                + "SELECT ?s "
                + "FROM STREAM <"+ p.getPk() +"> [RANGE 5s STEP 5s] "
                + "FROM <http://mycsparql.lsdi/smartParking> "
                + "WHERE { "
                + "?s pk:hasState pk:Free ."
                + "?s pk:hasVehicleSpace pk:CarSpace ."
                + "?s pk:hasReferencePoint pk:SnackBar ."
                + "?s pk:hasPreferentialSpace pk:ElderlySpace "
                + "} ";
        return query2;

    }

}
