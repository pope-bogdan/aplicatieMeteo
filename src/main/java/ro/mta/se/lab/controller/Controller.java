package ro.mta.se.lab.controller;

import java.io.*;
import java.net.MalformedURLException;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import ro.mta.se.lab.model.Model;
import ro.mta.se.lab.model.oras;

import javax.lang.model.type.NullType;

/**
 * Clasa ce se ocupa de functionalitatile interfetei grafice.
 *
 * @author Bogdan Popescu
 * @see Model
 * @see oras
 */
public class Controller {
    private ObservableList<Model> meteoData;
    private ObservableList<ro.mta.se.lab.model.oras> orasData = FXCollections.observableArrayList();
    @FXML
    private TableView<Model> optiuniTable;
    @FXML
    private TableView<oras> orasTable;
    @FXML
    private TableColumn<Model, String> taraColumn;
    @FXML
    private TableColumn<oras, String> orasColumn;
    @FXML
    private Label oras;
    @FXML
    private Label temperatura;
    @FXML
    private Label descriere;
    @FXML
    private Label vant;
    @FXML
    private Label presiune;
    @FXML
    private Label umiditate;

    /**
     * Constructer al clasei
     *
     * @param meteoData Informatiile pentru aplicatie(tari,orase...).
     */
    public Controller(ObservableList<Model> meteoData) {
        this.meteoData = meteoData;
    }

    /**
     * Metoda de initializare.
     * Aici se initializeaza tabelul cu tari si se adauga Listener pentru
     * lista de tari si altul pentru lista de orase.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        taraColumn.setCellValueFactory(cellData -> cellData.getValue().taraFullProperty());
        optiuniTable.setItems(meteoData);
        orasColumn.setCellValueFactory(cellData -> cellData.getValue().orasProperty());
        orasTable.setItems(orasData);
        optiuniTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Model>() {
            @Override
            public void changed(ObservableValue<? extends Model> observable, Model oldValue, Model newValue) {
                try {
                    ShowOrase(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        orasTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<oras>() {
            @Override
            public void changed(ObservableValue<? extends oras> observable, oras oldValue, oras newValue) {
                try {
                    ShowInfo(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    /**
     * Metoda de actualizare a listei de orase.
     * Aceasta metoda este apelata de listenerul listei de tari.
     */

    private void ShowOrase(Model oras) throws IOException {
        if (oras != null) {
            orasData.clear();
            for (int i = 0; i < oras.orasProperty().size(); i++) {
                orasData.add(new oras(oras.getTara(), oras.orasProperty().get(i).getValue().toString(), oras.getLatitudine().get(i).doubleValue(), oras.longitudineProperty().get(i).doubleValue()));
            }
        } else {
            orasData.clear();
            throw new IOException("Modelul este gol");
        }
    }

    public Double getTemperatura(oras _oras) throws IOException {
        String API_KEY = "d3339fef53127fb39334d4cf514533b8";
        String LOCATION = _oras.getOras() + "," + _oras.getTara();
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        JsonObject temp = Json.parse(String.valueOf(result)).asObject().get("main").asObject();
        return temp.getDouble("temp", 0);

    }

    public Double getPresiune(oras _oras) throws IOException {
        String API_KEY = "d3339fef53127fb39334d4cf514533b8";
        String LOCATION = _oras.getOras() + "," + _oras.getTara();
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        JsonObject temp = Json.parse(String.valueOf(result)).asObject().get("main").asObject();
        return temp.getDouble("pressure", 0);

    }

    /**
     * Metoda de actualizare a informatiilor despre un oras.
     * Aceasta metoda este apelata de listenerul listei de orase.
     */
    private void ShowInfo(oras _oras) throws IOException {
        if (_oras != null) {
            oras.setText(_oras.getOras());
            String API_KEY = "d3339fef53127fb39334d4cf514533b8";
            String LOCATION = _oras.getOras() + "," + _oras.getTara();
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";
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
                String _descriere = item.asObject().getString("description", "Unknown Item");
                descriere.setText(_descriere);

            }
            JsonObject temp = Json.parse(String.valueOf(result)).asObject().get("main").asObject();
            double _temperatura = getTemperatura(_oras);
            double _presiune = getPresiune(_oras);
            double _umiditate = temp.asObject().getDouble("humidity", 0);
            temperatura.setText(String.valueOf(_temperatura) + " ºC");
            presiune.setText(String.valueOf(_presiune) + " mmHg");
            umiditate.setText(String.valueOf(_umiditate) + " %");
            JsonObject temp1 = Json.parse(String.valueOf(result)).asObject().get("wind").asObject();
            double viteza_vant = temp1.getDouble("speed", 0);
            vant.setText(String.valueOf(viteza_vant) + " m/s");
            try {
                File afisari = new File("afisari.log");
                FileWriter fr = new FileWriter(afisari, true);
                String complete_data;
                Date date = new Date(); // This object contains the current date value
                String data = "Temperatura:" + temperatura.getText() + " " + "presiunea:" + presiune.getText() + "";
                data = data + " umiditatea:" + umiditate.getText() + "" + " vant:" + viteza_vant + "m/s Descriere:" + descriere.getText() + " Oras:" + oras.getText() + " Tara:" + _oras.getTara();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                complete_data = formatter.format(date);
                complete_data += " " + data;
                fr.write(complete_data + "\n");
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            oras.setText("");
            descriere.setText("");
            temperatura.setText("");
            presiune.setText("");
            umiditate.setText("");
            vant.setText("");
        }
    }
}
