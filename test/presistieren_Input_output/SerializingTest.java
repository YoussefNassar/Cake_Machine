package presistieren_Input_output;

import Logic.Verwaltung;
import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class SerializingTest {

    final String fileName = "Data";
    Verwaltung verwaltung;
    Serializing serializing;
    Kuchen kuchen;

    @BeforeEach
    void setUp(){
        this.verwaltung = new Verwaltung();
        this.serializing = new Serializing();
    }


    @Test
    void serializeWritingTest() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = spy(ObjectOutputStream.class);
        serializing.serialize(oos,verwaltung);
        verify(oos).writeObject(any());
    }

    @Test
    void deserializeReadingTest() throws Exception {
        ObjectInputStream ois = spy(ObjectInputStream.class);
        serializing.deserialize(ois);
        verify(ois).readObject();
    }
}