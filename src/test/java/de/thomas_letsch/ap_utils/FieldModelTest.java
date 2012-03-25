package de.thomas_letsch.ap_utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldModelTest {

    @Test
    public void testSimple() {
        TypeModel model = new FieldModel("Long", "id");
        assertEquals("Long id;", model.toString());
    }

    @Test
    public void testGetter() {
        FieldModel model = new FieldModel("Long", "id");
        MethodModel getter = model.toGetter();
        assertEquals("getId", getter.name);
        assertEquals("Long", getter.returnObject);
        assertEquals(null, getter.type);
    }

    @Test
    public void testWithAnnotations() {
        ElementModel model = new FieldModel("Long", "id");
        AnnotationModel am = new AnnotationModel("Test");
        model.addAnnotations(am);
        assertEquals("@Test\nLong id;", model.toString());
    }

    @Test
    public void testWithGenerics() {
        ElementModel model = new FieldModel(null, "Class", new TypeModel("Long"), "id");
        AnnotationModel am = new AnnotationModel("Test");
        model.addAnnotations(am);
        assertEquals("@Test\nClass<Long> id;", model.toString());
    }

    @Test
    public void testWithAssignment() {
        FieldModel model = new FieldModel(null, "Long", "id");
        model.setAssignment("new Long(3)");
        assertEquals("Long id = new Long(3);", model.toString());
    }

}
