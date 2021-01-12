package ro.mta.se.lab.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Locale;
/**
 * Clasa ce implementeaza modelul unui oras.
 *
 * @author Bogdan Popescu
 * @see ro.mta.se.lab.controller.Controller
 */
public class oras {
    StringProperty Tara;
    StringProperty Oras;
    DoubleProperty latitudine;
    DoubleProperty longitudine;
    /**
     * Metoda ce seteaza codul tarii.
     * @param tara codul tarii de setat
     */
    public void setTara(String tara) {
        this.Tara.set(tara);
    }
    /**
     * Metoda ce seteaza  orasul .
     * @param oras orasul de setat.
     */
    public void setOras(String oras) {
        this.Oras.set(oras);
    }
    /**
     * Metoda ce seteaza latiduinea .
     * @param latitudine latitudinea de setat.
     */
    public void setLatitudine(double latitudine) {
        this.latitudine.set(latitudine);
    }
    /**
     * Metoda ce seteaza longitudinea .
     * @param longitudine longitudinea de setat.
     */
    public void setLongitudine(double longitudine) {
        this.longitudine.set(longitudine);
    }
    /**
     * Constructer al clasei
     *@param tara Codul tarii.
     * @param oras Numele orasului
     * @param latitudine Latitudinea orasului
     * @param longitudine Longitudinea orasului
     */
    public oras(String tara, String oras, double latitudine, double longitudine) {
        Locale l = new Locale("", tara);
        Tara = new SimpleStringProperty(l.getDisplayCountry());
        Oras = new SimpleStringProperty(oras);
        this.latitudine = new SimpleDoubleProperty(latitudine);
        this.longitudine = new SimpleDoubleProperty(longitudine);
    }
    /**
     * Metoda ce returneaza codul tarii ca si String
     */
    public String getTara() {
        return Tara.get();
    }
    /**
     * Metoda ce returneaza codul tarii ca si StringProperty
     */
    public StringProperty taraProperty() {
        return Tara;
    }
    /**
     * Metoda ce returneaza orasul ca si String.
     */
    public String getOras() {
        return Oras.get();
    }
    /**
     * Metoda ce returneaza orasul ca si StringProperty.
     */
    public StringProperty orasProperty() {
        return Oras;
    }
    /**
     * Metoda ce returneaza latitudinea ca si Double.
     */
    public double getLatitudine() {
        return latitudine.get();
    }
    /**
     * Metoda ce returneaza latitudine ca si DoubleProperty.
     */
    public DoubleProperty latitudineProperty() {
        return latitudine;
    }
    /**
     * Metoda ce returneaza longitudinea ca si Double.
     */
    public double getLongitudine() {
        return longitudine.get();
    }
    /**
     * Metoda ce returneaza longitudinea ca si DoubleProperty.
     */
    public DoubleProperty longitudineProperty() {
        return longitudine;
    }
}
