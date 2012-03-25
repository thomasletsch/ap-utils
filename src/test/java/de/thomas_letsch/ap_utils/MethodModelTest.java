package de.thomas_letsch.ap_utils;

import static org.junit.Assert.assertEquals;

import javax.lang.model.element.Modifier;

import org.junit.Test;

public class MethodModelTest {

    @Test
    public void testSimple() {
        TypeModel model = new MethodModel("testSimple");
        assertEquals(" void testSimple() {\n}\n", model.toString());
    }

    @Test
    public void testWithModifiers() {
        ElementModel model = new MethodModel("testSimple");
        model.addModifiers(Modifier.PUBLIC);
        assertEquals("public void testSimple() {\n}\n", model.toString());
    }

    @Test
    public void testWithAnnotations() {
        ElementModel model = new MethodModel("testSimple");
        AnnotationModel am = new AnnotationModel("org.junit.Test");
        model.addModifiers(Modifier.PUBLIC);
        model.addAnnotations(am);
        assertEquals("@org.junit.Test\npublic void testSimple() {\n}\n", model.toString());
    }

    @Test
    public void testWithParameters() {
        MethodModel model = new MethodModel("testSimple");
        ParameterModel am = new ParameterModel("Long", "id");
        model.addModifiers(Modifier.PUBLIC);
        model.addParameters(am);
        assertEquals("public void testSimple(Long id) {\n}\n", model.toString());
    }

}
