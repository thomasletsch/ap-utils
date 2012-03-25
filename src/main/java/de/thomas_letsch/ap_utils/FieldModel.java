package de.thomas_letsch.ap_utils;


import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

import org.apache.commons.lang.StringUtils;

public class FieldModel extends ElementModel {

    private String assignment;

    public static TypeModel from(VariableElement field) {
        return new FieldModel(field.asType().toString(), field.getSimpleName().toString());
    }

    public FieldModel(String type, String name) {
        super(type, name);
    }

    public FieldModel(String packageName, String type, String name) {
        super(packageName, type, name);
    }

    public FieldModel(String packageName, String type, TypeModel generic, String name) {
        super(packageName, type, generic, name);
    }

    public FieldModel setAssignment(String assignment) {
        this.assignment = assignment;
        return this;
    }

    public String toString(String tab) {
        this.tab = tab;
        return toString();
    }

    @Override
    public FieldModel setPrivate() {
        return (FieldModel) super.setPrivate();
    }

    @Override
    public FieldModel setProtected() {
        return (FieldModel) super.setProtected();
    }

    @Override
    public FieldModel setPublic() {
        return (FieldModel) super.setPublic();
    }

    @Override
    public FieldModel addModifiers(Modifier... newModifiers) {
        return (FieldModel) super.addModifiers(newModifiers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(tab);
        if (!annotations.isEmpty()) {
            sb.append(StringUtils.join(annotations, "\n"));
            sb.append("\n");
        }
        if (!modifiers.isEmpty()) {
            sb.append(StringUtils.join(modifiers, " "));
            sb.append(" ");
        }
        sb.append(toDeclaration());
        sb.append(" " + name);
        if (assignment != null) {
            sb.append(" = " + assignment);
        }
        sb.append(";");
        return sb.toString();
    }

    public MethodModel toGetter() {
        MethodModel method = new MethodModel("get" + StringUtils.capitalize(name), type);
        method.setPublic();
        method.addLine("return " + name + ";");
        return method;
    }

}
