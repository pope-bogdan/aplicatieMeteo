package ro.mta.se.lab.controller;

import com.eclipsesource.json.JsonObject;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ro.mta.se.lab.model.Model;
import ro.mta.se.lab.model.oras;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerTester {
    private static Controller test;
    private static oras _oras;

    @Test
    void getTemperatura() throws IOException {
        ObservableList meteoData=mock(ObservableList.class);
        test=new Controller(meteoData);
        _oras = mock(oras.class);
        when(_oras.getTara()).thenReturn("RO");
        when(_oras.getOras()).thenReturn("Bucharest");
        assertDoesNotThrow(() -> test.getTemperatura(_oras));
    }

    @Test
    void getPresiune() {
        ObservableList meteoData=mock(ObservableList.class);
        test=new Controller(meteoData);
        _oras = mock(oras.class);
        when(_oras.getTara()).thenReturn("RO");
        when(_oras.getOras()).thenReturn("Bucharest");
        assertDoesNotThrow(() -> test.getPresiune(_oras));
    }
}