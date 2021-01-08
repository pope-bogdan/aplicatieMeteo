package ro.mta.se.lab.ro.mta.se.lab.controller;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import ro.mta.se.lab.model.Model;

import javax.lang.model.type.NullType;

public class Controller {
    private ObservableList<Model> meteoData;
    @FXML
    private ComboBox comboBox;
    @FXML
    private MenuButton taraDown;
    @FXML
    private MenuButton orasDown;
    @FXML
    private Label tempLabel;
    @FXML
    private Label tempLabelMin;
    @FXML
    private Label tempMax;
    @FXML
    private Label presLabel;
    @FXML
    private Label umidLabel;
    @FXML
    private Label vitezaVant;
    @FXML
    private Label Descriere;
    public Controller(ObservableList<Model> meteoData) {
        this.meteoData = meteoData;
    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        for(int i=0;i<meteoData.size();i++)
        {
            MenuItem item=new MenuItem(meteoData.get(i).getTara());
            Vector<StringProperty> vectorOrase=meteoData.get(i).orasProperty();
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    orasDown.getItems().clear();
                    tempLabel.setText("");
                    umidLabel.setText("");
                    presLabel.setText("");
                    Descriere.setText("");
                    tempMax.setText("");
                    tempLabelMin.setText("");
                    Label nimic=new Label("");
                    Label choice1Label = new Label(item.getText());
                    taraDown.setText(item.getText());
                    //taraDown.setGraphic(nimic);
                    //taraDown.setGraphic(choice1Label);
                    for(int j=0;j<vectorOrase.size();j++)
                    {
                        MenuItem item1=new MenuItem(vectorOrase.get(j).getValue().toString());
                        //System.out.println(item1.getText());
                        item1.setOnAction(new EventHandler<ActionEvent>() {
                                              @Override
                                              public void handle(ActionEvent event) {
                                                  try {
                                                      orasDown.setText(item1.getText());
                                                      String API_KEY = "d3339fef53127fb39334d4cf514533b8";
                                                      String LOCATION = item1.getText()+","+item.getText();
                                                      String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric&lang=RO";
                                                      StringBuilder result = new StringBuilder();
                                                      URL url = new URL(urlString);
                                                      URLConnection conn = url.openConnection();
                                                      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                                      String line;
                                                      while ((line = rd.readLine()) != null) {
                                                          result.append(line);
                                                      }
                                                      JsonArray items = Json.parse(String.valueOf(result)).asObject().get("weather").asArray();
                                                      for (JsonValue item : items) {
                                                          String principal = item.asObject().getString("main", "Unknown Item");
                                                          String descriere = item.asObject().getString("description", "Unknown Item");
                                                          Descriere.setText(principal+": "+descriere);
                                                      }
                                                      JsonObject temp=Json.parse(String.valueOf(result)).asObject().get("main").asObject();

                                                          double temperatura = temp.getDouble("temp", 0);
                                                          double temp_min = temp.asObject().getDouble("temp_min", 0);
                                                          double temp_max=temp.asObject().getDouble("temp_max", 0);
                                                          double presiune=temp.asObject().getDouble("pressure", 0);
                                                          double umiditate=temp.asObject().getDouble("humidity", 0);
                                                          tempLabel.setText(String.valueOf(temperatura)+" ºC");
                                                          umidLabel.setText(String.valueOf(umiditate)+" %");
                                                          presLabel.setText(String.valueOf(presiune)+" mm Hg");
                                                          tempLabelMin.setText((String.valueOf(temp_min)));
                                                          tempMax.setText((String.valueOf(temp_max)));
                                                      JsonObject temp1=Json.parse(String.valueOf(result)).asObject().get("wind").asObject();
                                                      double viteza_vant=temp1.getDouble("speed",0);
                                                      vitezaVant.setText(String.valueOf(viteza_vant));
                                                          String data="Temperatura:"+String.valueOf(temperatura)+"ºC temp_min:"+String.valueOf(temp_min)+"ºC temp_max:"+String.valueOf(temp_max)+"ºC presiunea:"+String.valueOf(presiune)+"mm Hg";
                                                                  data=data+" umiditatea:"+String.valueOf(umiditate)+"%"+" vant:"+viteza_vant+"Descriere:"+Descriere.getText()+" Oras:"+orasDown.getText()+" Tara:"+taraDown.getText();
                                                      try {
                                                          File afisari = new File("afisari.log");
                                                          FileWriter fr = new FileWriter(afisari, true);
                                                          String complete_data;
                                                          Date date = new Date(); // This object contains the current date value
                                                          SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                                          complete_data=formatter.format(date);
                                                          complete_data+=" "+data;
                                                          fr.write(complete_data+"\n");
                                                          fr.close();
                                                      }
                                                      catch (IOException e)
                                                      {
                                                          e.printStackTrace();
                                                      }


                                                      rd.close();

                                                  }
                                                  catch (IOException e){
                                                      System.out.println(e.getMessage());
                                                  }
                                              }
                                          });
                        orasDown.getItems().addAll(item1);
                    }
                    //orasDown.getItems().addAll(new MenuItem("Bucuresti"),new MenuItem("Timisoara"),new MenuItem("Brasov"));

                }
            });
            taraDown.getItems().addAll(item);
        }




    }

}
