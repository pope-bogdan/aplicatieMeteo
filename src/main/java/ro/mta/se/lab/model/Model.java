package ro.mta.se.lab.model;

import javafx.beans.property.*;

import java.util.Vector;

public class Model {
    StringProperty Tara;
   Vector<StringProperty> Oras;
    Vector<DoubleProperty> latitudine;
    Vector<DoubleProperty> longitudine;


    public Model(String tara, String oras, double latitudine, double longitudine) {
        Tara =  new SimpleStringProperty(tara);
        Oras = new Vector<StringProperty>();
        this.latitudine=new Vector<DoubleProperty>();
        this.longitudine=new Vector<DoubleProperty>();
        this.Oras.add(new SimpleStringProperty(oras));
        this.latitudine.add(new SimpleDoubleProperty(latitudine));
        this.longitudine.add(new SimpleDoubleProperty(longitudine));
    }

    public void setTara(String tara) {
        this.Tara.set(tara);
    }

    public void setOras(String oras) {
        this.Oras.add(new SimpleStringProperty(oras));
    }


    public void setLatitudine(double latitudine) {
        this.latitudine.add(new SimpleDoubleProperty(latitudine));
    }

    public void setLongitudine(double longitudine) {
        this.longitudine.add(new SimpleDoubleProperty(longitudine));
    }

    public String getTara() {
        return Tara.get();
    }

    public StringProperty taraProperty() {
        return Tara;
    }

    public Vector<StringProperty> orasProperty() {
        return Oras;
    }

    public Vector<DoubleProperty> getLatitudine() {
        return latitudine;
    }

    public String getLastOras()
    {
        return this.Oras.lastElement().getValue().toString();
    }

    public Vector<DoubleProperty> longitudineProperty() {
        return longitudine;
    }
}
