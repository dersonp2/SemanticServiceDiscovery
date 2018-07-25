package br.ufma.lsdi.ssd.Communication;

import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Implements.ObservableImpl;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;


public class Communication {
    //Mqtt
    private MqttClient client = null;
    final private String TOPIC_RESPONSE = "responseQuery/";
    final private String TOPIC_QUERY = "QueryCSparql";
    private MqttMessage message;

    //Classe
    private ObservableImpl observable = null;
    private Query query = null;

    //Json
    private Gson gson = null;
    private String topic = null;

    public void query(Query query, ObservableImpl observableImpl) {
        this.observable = observableImpl;
        this.query = new Query.Builder().build();
        this.query = query;
        System.out.println(""+this.query.getQuery()+" - "+this.query.getPublisherID());
        System.out.println("*-*-*-*-*-*-Communication - recebeu a consulta");
        try{
            publish();
        }catch (Exception e){

        }
    }

    private void publish() {
        connect();
        gson = new Gson();
        String m =gson.toJson(query);
        System.out.println("*-*-*-*-*-*-Communication - Publish()");
        try {
            message = new MqttMessage();
            message.setQos(2);
            message.setRetained(false);
            message.setPayload(m.getBytes());
            client.publish(TOPIC_QUERY, message);
            System.out.println("*-*-*-*-*-*-Communication - Publicou a consulta");
            responseQuery();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void responseQuery() {
        connect();
        System.out.println("*-*-*-*-*-*-Communication - responseQuery()");
        topic = TOPIC_RESPONSE + query.getReturnCode();
        try{
            client.subscribe(topic);
            System.out.println("*-*-*-*-*-*-Communication - Subscreveu em: "+ topic);
            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {
                    System.out.println("connectComplete");
                }

                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("ConnectionLost");
                    responseQuery();
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived");
                    System.out.println("*-*-*-*-*-*-Communication - Recebeu uma mensagem porrraa");
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
        client = Connect.getInstance().Connection(query.getPublisherID());
    }

    public void disconnect() {
        try {
            client.unsubscribe(topic);
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
