package ro.mta.se.lab.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model test;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        test=new Model("RO","Bucharest",12.2,13.2);
    }
    @org.junit.jupiter.api.Test
    void setTara() {
        test.setTara("HU");
        assertEquals(test.getTara(),"HU");
    }

    @org.junit.jupiter.api.Test
    void setOras() {
        test.setOras("Timisoara");
        assertEquals("Timisoara",test.getLastOras());
    }

    @org.junit.jupiter.api.Test
    void setLatitudine() {
        test.setLatitudine(10);
        assertEquals(test.getLatitudine().lastElement().doubleValue(),10);
    }

    @org.junit.jupiter.api.Test
    void setLongitudine() {
        test.setLongitudine(10);
        assertEquals(test.longitudineProperty().lastElement().doubleValue(),10);
    }

    @Test
    void getTara() {
        assertEquals(test.getTara(),"RO");
    }

    @org.junit.jupiter.api.Test
    void taraProperty() {
        assertEquals("RO",test.taraProperty().getValue().toString());
    }

    @org.junit.jupiter.api.Test
    void orasProperty() {
        assertEquals(test.orasProperty().lastElement().getValue().toString(),"Bucharest");
    }

    @org.junit.jupiter.api.Test
    void getLatitudine() {
        assertEquals(12.2,test.getLatitudine().lastElement().doubleValue());
    }
    @Test
    void getLastOras(){
        test.setOras("Timisoara");
        assertEquals("Timisoara",test.getLastOras());
    }
    @org.junit.jupiter.api.Test
    void longitudineProperty() {
        test.setLongitudine(10);
        assertEquals(test.longitudineProperty().lastElement().doubleValue(),10);
    }
}