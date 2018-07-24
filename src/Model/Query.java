package Model;

import java.util.UUID;

/**
 * Created by Anderson on 11/06/2018.
 */

public class Query {
    private String Query;
    private Boolean Continuos;
    String publisherID;
    final String returnCode = UUID.randomUUID().toString();


    public Query(String query, Boolean continuos, String publisherID) {
        Query = query;
        Continuos = continuos;
        this.publisherID = publisherID;
    }

    public static class Builder{
        private String query;
        private Boolean continuos;
        String publisherID;
        final String returnCode = UUID.randomUUID().toString();

        public Builder(){

        }

        public Builder query(String query){
            this.query = query;
            return  this;
        }
        public Builder continuos(Boolean continuos){
            this.continuos = continuos;
            return  this;
        }
        public Builder publisherID(String publisherID){
            this.publisherID = publisherID;
            return  this;
        }

        public Query build(){
            return  new Query(query,continuos,publisherID);
        }
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public Boolean getContinuos() {
        return Continuos;
    }

    public String getQuery() {

        return Query;
    }

}
