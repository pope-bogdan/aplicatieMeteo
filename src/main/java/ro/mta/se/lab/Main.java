package ro.mta.se.lab;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.Controller;
import ro.mta.se.lab.model.Model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    private ObservableList<Model> meteoData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public void initializeMeteo() {

        try {
            File Orase = new File("orase.txt");
            Scanner myReader = new Scanner(Orase);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split("\t");
                if (meteoData.size() > 0) {
                    Integer i;
                    boolean ok;
                    ok = true;
                    for (i = 0; i < meteoData.size(); i++) {
                        if (meteoData.get(i).getTara().equals(tokens[4])) {
                            ok = false;
                            meteoData.get(i).setOras(tokens[1]);
                            meteoData.get(i).setLatitudine(Double.valueOf(tokens[2]));
                            meteoData.get(i).setLongitudine(Double.valueOf(tokens[3]));
                            break;
                        }
                    }
                    if (ok == true) {
                        meteoData.add(new Model(tokens[4], tokens[1], Double.valueOf(tokens[2]), Double.valueOf(tokens[3])));
                    }

                } else {
                    meteoData.add(new Model(tokens[4], tokens[1], Double.valueOf(tokens[2]), Double.valueOf(tokens[3])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // meteoData.add(new Model("Romania", "Bucuresti", 23.5, 23.0));
        //meteoData.add(new Model("Romania", "Brasov", 13.5, 45.0));
        //  meteoData.add(new Model("Romania", "Timisoara", 29, 15.0));

    }

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        initializeMeteo();
        try {
            loader.setLocation(this.getClass().getResource("/view/meteoApp.fxml"));
            loader.setController(new Controller(meteoData));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
