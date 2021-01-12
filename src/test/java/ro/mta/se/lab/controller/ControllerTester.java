package ro.mta.se.lab.controller;

import com.eclipsesource.json.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerTester {

    @Test
    void getTemperatura() {
        JsonObject informatii=mock(JsonObject.class);
        Controller test=mock(Controller.class);
        when(informatii.getDouble("temp",0)).thenReturn((double) 23);
        when(test.getTemperatura(informatii)).thenReturn((double) 23);
        assertEquals(test.getTemperatura(informatii),23);
    }

    @Test
    void getPresiune() {
        JsonObject informatii=mock(JsonObject.class);
        Controller test=mock(Controller.class);
        when(informatii.getDouble("pressure",0)).thenReturn((double) 1066);
        when(test.getPresiune(informatii)).thenReturn((double) 1066);
        assertEquals(test.getPresiune(informatii), 1066);
    }
}