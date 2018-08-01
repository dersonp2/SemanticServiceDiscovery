package br.ufma.lsdi.ssd.Communication;

import br.ufma.lsdi.ssd.ConfigLog.ConfigLog;
import br.ufma.lsdi.ssd.Model.Query;
import br.ufma.lsdi.ssd.Implements.ResultReceiver;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;


public class Communication {
    //Mqtt
    private MqttClient client = null;
    final private String TOPIC_RESPONSE = "responseQuery/";
    final private String TOPIC_QUERY = "QueryCSparql";
    private MqttMessage message;

    //Classe
    private ResultReceiver observable = null;
    private Query query = null;

    //Json
    private Gson gson = null;
    private String topic = null;
    private Logger logger;

    public void query(Query query, ResultReceiver resultReceiver) {
        logger = new ConfigLog().log(ResultReceiver.class);
        logger.info("Recebeu a consulta");
        this.observable = resultReceiver;
        this.query = new Query.Builder().build();
        this.query = query;
        connect();
        //Tratamento de NullPoint
        if (client != null){
            publish();
        }else{
            failedConnect();
        }
    }

    private void publish() {
        //connect();
        gson = new Gson();
        String m = gson.toJson(query);
        try {
            message = new MqttMessage();
            message.setQos(2);
            message.setRetained(false);
            message.setPayload(m.getBytes());
            client.publish(TOPIC_QUERY, message);
            logger.info("Publicou a consulta");
            responseQuery();
        } catch (MqttException e) {
            e.printStackTrace();
            logger.error("Erro ao publicar a consulta");
        }
    }

    private void responseQuery() {
        //connect();
        topic = TOPIC_RESPONSE + query.getReturnCode();
        try {
            client.subscribe(topic);
            logger.info("Subscreveu em: " + topic);
            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {
                    logger.info("connectComplete");
                }

                @Override
                public void connectionLost(Throwable throwable) {
                    logger.info("ConnectionLost");
                    responseQuery();
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    logger.info("messageArrived");
                    String m = String.valueOf(message.getPayload());
                    observable.notifyListener(m);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    logger.info("deliveryComplete");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            logger.error("Erro ao se subscrever em: " + topic);
        }

    }

    public void connect() {
        client = Connect.getInstance().Connection(query.getPublisherID());
    }

    public void disconnect() {
        try {
            client.unsubscribe(topic);
            logger.info("Desconectou");
        } catch (MqttException e) {
            e.printStackTrace();
            logger.error("Erro ao se desconectar");
        }
    }

    public void failedConnect(){
        observable.notifyListener("Failed to connect to broker");
    }
}
