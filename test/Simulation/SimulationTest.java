//package Simulation;

import Logic.Verwaltung;
import Model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//class SimulationTest {
//
//    Verwaltung verwaltung;
//
//    Kuchen kuchen1;
//
//    @Test
//    void simulationtest() throws Exception {
//        verwaltung = spy(Verwaltung.class);
//        Simulation simulation = new Simulation(verwaltung);
//        simulation
//
//        Thread thread = new Thread(simulation);
//        thread.start();
//        Thread.sleep(1000);    // hint from stack overflow
//        verify(verwaltung,atLeast(1)).addKuchen(any());
//    }
//}