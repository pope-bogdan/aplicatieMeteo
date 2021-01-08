package ro.mta.se.lab.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Locale;

public class oras {
    StringProperty Tara;
    StringProperty Oras;
    DoubleProperty latitudine;
    DoubleProperty longitudine;

    public void setTara(String tara) {
        this.Tara.set(tara);
    }

    public void setOras(String oras) {
        this.Oras.set(oras);
    }

    public void setLatitudine(double latitudine) {
        this.latitudine.set(latitudine);
    }

    public void setLongitudine(double longitudine) {
        this.longitudine.set(longitudine);
    }

    public oras(String tara, String oras, double latitudine, double longitudine) {
        Locale l = new Locale("", tara);
        Tara = new SimpleStringProperty(l.getDisplayCountry());
        Oras = new SimpleStringProperty(oras);
        this.latitudine = new SimpleDoubleProperty(latitudine);
        this.longitudine = new SimpleDoubleProperty(longitudine);
    }

    public String getTara() {
        return Tara.get();
    }

    public StringProperty taraProperty() {
        return Tara;
    }

    public String getOras() {
        return Oras.get();
    }

    public StringProperty orasProperty() {
        return Oras;
    }

    public double getLatitudine() {
        return latitudine.get();
    }

    public DoubleProperty latitudineProperty() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine.get();
    }

    public DoubleProperty longitudineProperty() {
        return longitudine;
    }
}
