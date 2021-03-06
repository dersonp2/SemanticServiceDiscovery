package br.ufma.lsdi.ssd.StaticModel;

import br.ufma.lsdi.ssd.Communication.Connect;
import br.ufma.lsdi.ssd.Model.ModelStaticModel;
import com.google.gson.Gson;
import com.hp.hpl.jena.rdf.model.Model;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class StaticModel {
    //Mqtt
    private MqttClient client = null;
    private ModelStaticModel model = new ModelStaticModel();
    private String topicStaticModel = null;

    public void putStaticNamedModel(String var1, String var2){
        model.setVar1(var1);
        model.setVar2(var2);
        topicStaticModel = "SSD/StaticNamedModel";
        pubishStaticModel(this.model);
    }

    public void putStaticJenaModel(String var1, Model model){
        this.model.setModel(model);
        this.model.setVar3(var1);
        pubishStaticModel(this.model);
        topicStaticModel = "SSD/StaticJenaModel";
        pubishStaticModel(this.model);
    }

    private void pubishStaticModel(ModelStaticModel model){
        connect();
        //Tratamento de NullPoint
        if (client != null){
            Gson gson = new Gson();
            String m = gson.toJson(model);
            try {
                MqttMessage message = new MqttMessage();
                message.setQos(2);
                message.setRetained(false);
                message.setPayload(m.getBytes());
                client.publish(topicStaticModel, message);
                System.out.println("StaticModel  - published the StaticModel\n");
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println("Erro ao publicar a StaticModel");
            }
        }else{
            failedConnect();
        }

    }

    private void connect() {
        client = Connect.getInstance().Connection("StaticModel");
    }

    private void failedConnect(){
        System.out.println("Error to Conect Broker");
    }
}
