package de.thomas_letsch.ap_utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class TopLevelModel extends ElementModel {

    private List<String> imports = new ArrayList<String>();

    protected List<String> extendTypes = new ArrayList<String>();

    protected List<String> implementsTypes = new ArrayList<String>();

    protected List<FieldModel> fields = new ArrayList<FieldModel>();

    private List<ConstructorModel> constructors = new ArrayList<ConstructorModel>();

    private List<MethodModel> methods = new ArrayList<MethodModel>();

    private List<TopLevelModel> innerClasses = new ArrayList<TopLevelModel>();

    protected boolean isInnerClass = false;

    public TopLevelModel(String packageName, String name) {
        this(packageName, "class", name);
    }

    public TopLevelModel(String packageName, String type, String name) {
        super(packageName, type, null, name);
    }

    @Override
    public void addAnnotation(AnnotationModel newAnnotation) {
        super.addAnnotation(newAnnotation);
        if (newAnnotation.getPackageName() != null) {
            addImport(newAnnotation.getClassName());
        }
    }

    public void addImports(String... newImports) {
        for (String newImport : newImports) {
            addImport(newImport);
        }
    }

    public void addImport(String newImport) {
        if (!imports.contains(newImport) && !newImport.startsWith("java.lang.")) {
            imports.add(newImport);
        }
    }

    public void addExtends(String... newExtends) {
        for (String newExtend : newExtends) {
            addExtend(newExtend);
        }
    }

    public void addExtend(String newExtend) {
        extendTypes.add(newExtend);
    }

    public void addExtends(TypeModel... type) {
        for (TypeModel newExtend : type) {
            addExtend(newExtend);
        }
    }

    public void addExtend(TypeModel type) {
        if (type.hasPackage()) {
            addImports(type.toImports().toArray(new String[] {}));
        }
        extendTypes.add(type.toDeclaration());
    }

    public void addImplements(String... newImplements) {
        implementsTypes.addAll(Arrays.asList(newImplements));
    }

    public void addMethods(MethodModel... newMethods) {
        for (MethodModel methodModel : newMethods) {
            addMethod(methodModel);
        }
    }

    public void addMethod(MethodModel method) {
        if (method == null) {
            return;
        }
        addImports(method.toImports().toArray(new String[] {}));
        methods.add(method);
    }

    public void addMethod(ConstructorModel constructor) {
        constructors.add(constructor);
    }

    public void addFields(FieldModel... newFields) {
        for (FieldModel fieldModel : newFields) {
            addField(fieldModel);
        }
    }

    public void addField(FieldModel newField) {
        if (newField == null) {
            return;
        }
        if (fields.contains(newField)) {
            return;
        }
        if (newField.getPackageName() != null) {
            addImports(newField.toImports().toArray(new String[] {}));
        }
        fields.add(newField);
    }

    public void addInnerClass(TopLevelModel innerClass) {
        innerClasses.add(innerClass);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isInnerClass) {
            printPackageAndImports(sb);
        }
        printAnnotations(sb);
        sb.append(tab + StringUtils.join(modifiers, " "));
        sb.append(" " + type + " " + name);
        if (!extendTypes.isEmpty()) {
            sb.append(" extends " + StringUtils.join(extendTypes, ", "));
        }
        if (!implementsTypes.isEmpty()) {
            sb.append(" implements " + StringUtils.join(implementsTypes, ", "));
        }
        sb.append(" {\n");
        if (!fields.isEmpty()) {
            printFields(sb);
        }
        if (!constructors.isEmpty()) {
            sb.append("\n");
            for (MethodModel method : constructors) {
                sb.append(method.toString(tab + TAB) + "\n");
            }
        }
        if (!methods.isEmpty()) {
            sb.append("\n");
            for (MethodModel method : methods) {
                sb.append(method.toString(tab + TAB) + "\n");
            }
        }

        if (!innerClasses.isEmpty()) {
            sb.append("\n");
            for (TopLevelModel innerClass : innerClasses) {
                innerClass.isInnerClass = true;
                innerClass.tab = tab + TAB;
                sb.append(innerClass.toString());
            }
        }

        sb.append(tab + "}");
        if (isInnerClass) {
            sb.append(";");
        }
        sb.append("\n");
        return sb.toString();
    }

    protected void printFields(StringBuilder sb) {
        sb.append("\n");
        for (FieldModel field : fields) {
            sb.append(field.toString(tab + TAB) + "\n");
        }
    }

    protected void printPackageAndImports(StringBuilder sb) {
        sb.append("package " + packageName + ";");
        sb.append("\n\n");
        for (String importClass : imports) {
            sb.append("import " + importClass + ";\n");
        }
        sb.append("\n");
    }

    public void addGetter(String name) {
        for (FieldModel field : fields) {
            if (field.getName().equals(name)) {
                addMethod(field.toGetter());
            }
        }
    }

    public void addGetInstance() {
        FieldModel field = new FieldModel(name, "instance");
        field.setPrivate().setStatic();
        addField(field);
        MethodModel method = new MethodModel("getInstance", name);
        method.setPublic().setStatic();
        method.addLine("if (instance == null) instance = new " + name + "();");
        method.addLine("return instance;");
        addMethod(method);
    }

}
