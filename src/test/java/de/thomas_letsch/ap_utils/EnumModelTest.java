package de.thomas_letsch.ap_utils;

import static org.junit.Assert.*;

import javax.lang.model.element.Modifier;

import org.junit.Test;

public class EnumModelTest {

    @Test
    public void testEnum() {
        String expectedClass = "package com.optible.processor.model;\n\n\n" + "public enum ClassModelTest {\n" + "}\n";
        EnumModel model = new EnumModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testPrintFields() {
        String expectedClass = "package com.optible.processor.model;\n\n\n" + "public enum ClassModelTest {\n" + "testField, testField2"
                + "}\n";
        EnumModel model = new EnumModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addField(new FieldModel("Long", "testField"));
        model.addField(new FieldModel("", "testField2"));
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

}
