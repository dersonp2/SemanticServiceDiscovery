package Communication;

import Model.Query;
import Implements.ObservableImpl;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;


public class Communication {
    //Mqtt
    private MqttClient cliente = null;
    private static String queryTopic = "QueryCSparql";
    private String responseTopic = "responseQuery/";
    private MqttMessage message;

    //Classe
    private ObservableImpl observable = null;
    private Query query = null;

    //Json
    private Gson gson = null;

    public void query(Query query, ObservableImpl observableImpl) {
        this.observable = observableImpl;
        this.query = new Query.Builder().build();
        this.query = query;
        System.out.println(""+this.query.getQuery()+" - "+this.query.getPublisherID());
        System.out.println("*-*-*-*-*-*- Communication - recebeu a consulta");
        try{
            publish();
        }catch (Exception e){

        }
    }

    private void publish() {
        cliente = Connect.getInstance().Connection();
        gson = new Gson();
        String m =gson.toJson(query);
        System.out.println("*-*-*-*-*-*- Communication - Publish()");
        try {
            message = new MqttMessage();
            message.setQos(2);
            message.setRetained(false);
            message.setPayload(m.getBytes());
            cliente.publish(queryTopic, message);
            System.out.println("*-*-*-*-*-*- Communication - Publicou a consulta");
            responseQuery();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void responseQuery() {
        cliente = Connect.getInstance().Connection();
        System.out.println("*-*-*-*-*-*- Communication - responseQuery()");
        responseTopic+= query.getReturnCode();
        try{
            cliente.subscribe(responseTopic);
            System.out.println("*-*-*-*-*-*- Communication - Subscreveu em: "+responseTopic);
            cliente.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {
                    System.out.println("connectComplete");
                }

                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("ConnectionLost");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived");
                    System.out.println("*-*-*-*-*-*- Communication - Recebeu uma mensagem");
                    String m =  String.valueOf(message.getPayload());
                    observable.notifyListener(m);

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete");
                }
            });
        }catch (MqttException e){

        }

    }

    public void connect(){
        cliente = Connect.getInstance().Connection();
    }

}
