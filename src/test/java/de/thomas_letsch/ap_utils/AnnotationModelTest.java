package de.thomas_letsch.ap_utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnnotationModelTest {

    @Test
    public void testSimpleModel() {
        AnnotationModel model = new AnnotationModel("org.junit.Test");
        assertEquals("@org.junit.Test", model.toString());
    }

    @Test
    public void testWithOneValue() {
        AnnotationModel model = new AnnotationModel("org.junit.Test");
        model.addValue("value", "something");
        assertEquals("@org.junit.Test(value = \"something\")", model.toString());
    }

    @Test
    public void testWithTwoValues() {
        AnnotationModel model = new AnnotationModel("org.junit.Test");
        model.addValue("value", "something");
        model.addValue("value2", "something2");
        assertEquals("@org.junit.Test(value = \"something\", value2 = \"something2\")", model.toString());
    }

}
