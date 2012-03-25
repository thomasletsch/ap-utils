package de.thomas_letsch.ap_utils;

import static org.junit.Assert.*;

import javax.lang.model.element.Modifier;

import org.junit.Test;

public class TopLevelModelTest {

    @Test
    public void testSimple() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n"
                + "public class ClassModelTest {\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithExtends() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n"
                + "public class ClassModelTest extends BaseTest {\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addExtends("BaseTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithExtendsWithPath() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import test.BaseTest;\nimport org.junit.Test;\n\n"
                + "public class ClassModelTest extends BaseTest {\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addExtends(new TypeModel("test", "BaseTest"));
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithGenericsExtends() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n"
                + "public class ClassModelTest extends BaseTest<View.view, Presenter.presenter> {\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addExtends("BaseTest<View.view, Presenter.presenter>");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithImplements() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n"
                + "public class ClassModelTest implements BaseInterface {\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addImplements("BaseInterface");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithAnnotations() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n" + "@org.junit.Test\n@Generated\n"
                + "public class ClassModelTest {\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        model.addAnnotations(new AnnotationModel("org.junit.Test"), new AnnotationModel("Generated"));
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithMethods() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n" + "@org.junit.Test\n"
                + "public class ClassModelTest {\n\n" + "    public void testWithMethods() {\n    }\n\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        model.addAnnotations(new AnnotationModel("org.junit.Test"));
        MethodModel methodModel = new MethodModel("testWithMethods");
        methodModel.addModifiers(Modifier.PUBLIC);
        model.addMethods(methodModel);
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithFields() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n" + "@org.junit.Test\n"
                + "public class ClassModelTest {\n\n" + "    public Long id;\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        model.addAnnotations(new AnnotationModel("org.junit.Test"));
        FieldModel methodModel = new FieldModel("Long", "id");
        methodModel.addModifiers(Modifier.PUBLIC);
        model.addFields(methodModel);
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithFieldWithPackage() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n" + "@org.junit.Test\n"
                + "public class ClassModelTest {\n\n" + "    public Long id;\n    Long id2;\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        model.addAnnotations(new AnnotationModel("org.junit.Test"));
        FieldModel methodModel = new FieldModel("java.lang", "Long", "id");
        methodModel.addModifiers(Modifier.PUBLIC);
        model.addFields(methodModel);
        FieldModel methodModel2 = new FieldModel("java.lang", "Long", "id2");
        model.addFields(methodModel2);
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

    @Test
    public void testWithFieldWithInnerClass() {
        String expectedClass = "package com.optible.processor.model;\n\n" + "import org.junit.Test;\n\n" + "@org.junit.Test\n"
                + "public class ClassModelTest {\n\n" + "    public class InnerClass {\n    };\n" + "}\n";
        TopLevelModel model = new TopLevelModel("com.optible.processor.model", "ClassModelTest");
        model.addModifiers(Modifier.PUBLIC);
        model.addImports("org.junit.Test");
        model.addAnnotations(new AnnotationModel("org.junit.Test"));
        TopLevelModel inner = new TopLevelModel("package", "InnerClass");
        inner.addModifiers(Modifier.PUBLIC);
        model.addInnerClass(inner);
        String resultClass = model.toString();
        assertEquals(expectedClass, resultClass);
    }

}
