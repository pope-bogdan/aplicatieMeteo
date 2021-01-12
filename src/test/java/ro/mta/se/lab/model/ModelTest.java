package ro.mta.se.lab.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model test;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        test = new Model("RO", "Bucharest", 12.2, 13.2);
    }

    @org.junit.jupiter.api.Test
    void setTara() {
        test.setTara("HU");
        assertEquals("HU", test.getTara());
    }

    @org.junit.jupiter.api.Test
    void setOras() {
        test.setOras("Timisoara");
        assertEquals("Timisoara", test.getLastOras());
    }

    @org.junit.jupiter.api.Test
    void setLatitudine() {
        test.setLatitudine(10);
        assertEquals(10, test.getLatitudine().lastElement().doubleValue());
    }

    @org.junit.jupiter.api.Test
    void setLongitudine() {
        test.setLongitudine(10);
        assertEquals(10, test.longitudineProperty().lastElement().doubleValue());
    }

    @Test
    void getTara() {
        assertEquals("RO", test.getTara());
    }

    @Test
    void getTaraFull() {
        assertEquals("Romania", test.getTaraFull().toString());
    }

    @Test
    void taraFullProperty() {
        assertEquals("Romania", test.taraFullProperty().getValue().toString());
    }

    @org.junit.jupiter.api.Test
    void taraProperty() {
        assertEquals("RO", test.taraProperty().getValue().toString());
    }

    @org.junit.jupiter.api.Test
    void orasProperty() {
        assertEquals("Bucharest", test.orasProperty().lastElement().getValue().toString());
    }

    @org.junit.jupiter.api.Test
    void getLatitudine() {
        assertEquals(12.2, test.getLatitudine().lastElement().doubleValue());
    }

    @Test
    void getLastOras() {
        test.setOras("Timisoara");
        assertEquals("Timisoara", test.getLastOras());
    }

    @org.junit.jupiter.api.Test
    void longitudineProperty() {
        test.setLongitudine(10);
        assertEquals(test.longitudineProperty().lastElement().doubleValue(), 10);
    }
}