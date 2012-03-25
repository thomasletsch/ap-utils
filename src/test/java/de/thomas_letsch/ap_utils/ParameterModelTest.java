package de.thomas_letsch.ap_utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParameterModelTest {

    @Test
    public void testSimple() {
        ParameterModel model = new ParameterModel("Long", "id");
        assertEquals("Long id", model.toString());
    }

    @Test
    public void testWithAnnotations() {
        ParameterModel model = new ParameterModel("Long", "id");
        AnnotationModel am = new AnnotationModel("Test");
        model.addAnnotations(am);
        assertEquals("@Test Long id", model.toString());
    }

}
