package de.thomas_letsch.ap_utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ElementModelTest {

    @Test
    public void testHasAnnotation() {
        ElementModel model = new ElementModel();
        model.addAnnotations(new AnnotationModel("Test"));
        assertTrue(model.hasAnnotation("Test"));
    }

}
