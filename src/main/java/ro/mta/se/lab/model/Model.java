package ro.mta.se.lab.model;

import javafx.beans.property.*;

import java.util.Locale;
import java.util.Vector;
/**
 * Clasa ce implementeaza modelul tarilor cu orase aferente.
 *
 * @author Bogdan Popescu
 * @see ro.mta.se.lab.controller.Controller
 */
public class Model {
    StringProperty TaraCode;
    StringProperty TaraFull;
    Vector<StringProperty> Oras;
    Vector<DoubleProperty> latitudine;
    Vector<DoubleProperty> longitudine;
    /**
     * Metoda ce returneaza numele complet al tarii ca si String
     */
    public String getTaraFull() {
        return TaraFull.get();
    }
    /**
     * Metoda ce returneaza numele complet al tarii ca si StringProperty
     */
    public StringProperty taraFullProperty() {
        return TaraFull;
    }
    /**
     * Constructer al clasei
     *@param tara Codul tarii.
     * @param oras Numele orasului
     * @param latitudine Latitudinea orasului
     * @param longitudine Longitudinea orasului
     */
    public Model(String tara, String oras, double latitudine, double longitudine) {
        TaraCode = new SimpleStringProperty(tara);
        Locale l = new Locale("", tara);
        TaraFull = new SimpleStringProperty(l.getDisplayCountry());
        Oras = new Vector<StringProperty>();
        this.latitudine = new Vector<DoubleProperty>();
        this.longitudine = new Vector<DoubleProperty>();
        this.Oras.add(new SimpleStringProperty(oras));
        this.latitudine.add(new SimpleDoubleProperty(latitudine));
        this.longitudine.add(new SimpleDoubleProperty(longitudine));
    }
    /**
     * Metoda ce seteaza codul tarii.
     * @param tara codul tarii de setat
     */
    public void setTara(String tara) {
        this.TaraCode.set(tara);
    }
    /**
     * Metoda ce adauga un oras in vectorul de orase.
     * @param oras orasul de adaugat
     */
    public void setOras(String oras) {
        this.Oras.add(new SimpleStringProperty(oras));
    }

    /**
     * Metoda ce adauga latiduinea in vectorul de latitudini
     * @param latitudine latitudinea de adaugat
     */
    public void setLatitudine(double latitudine) {
        this.latitudine.add(new SimpleDoubleProperty(latitudine));
    }
    /**
     * Metoda ce adauga longitudinea in vectorul de longitudini
     * @param longitudine lognitudinea de adaugat
     */
    public void setLongitudine(double longitudine) {
        this.longitudine.add(new SimpleDoubleProperty(longitudine));
    }
    /**
     * Metoda ce returneaza codul tarii ca si String
     */
    public String getTara() {
        return TaraCode.get();
    }
    /**
     * Metoda ce returneaza codul tarii ca si StringProperty
     */
    public StringProperty taraProperty() {
        return TaraCode;
    }
    /**
     * Metoda ce returneaza orasele ca si vector de StringProperty
     */
    public Vector<StringProperty> orasProperty() {
        return Oras;
    }
    /**
     * Metoda ce returneaza latitudinea ca si vector de DoubleProperty
     */
    public Vector<DoubleProperty> getLatitudine() {
        return latitudine;
    }
    /**
     * Metoda ce returneaza ultimul oras adaugat
     */
    public String getLastOras() {
        return this.Oras.lastElement().getValue().toString();
    }
    /**
     * Metoda ce returneaza longitudinea ca si vector de DoubleProperty
     */
    public Vector<DoubleProperty> longitudineProperty() {
        return longitudine;
    }
}
