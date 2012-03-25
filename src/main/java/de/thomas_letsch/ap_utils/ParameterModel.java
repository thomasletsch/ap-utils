package de.thomas_letsch.ap_utils;


import javax.lang.model.element.VariableElement;

import org.apache.commons.lang.StringUtils;

public class ParameterModel extends ElementModel {

    public static ParameterModel from(VariableElement parameter) {
        ParameterModel model = new ParameterModel(parameter.asType().toString(), parameter.getSimpleName().toString());
        model.addAnnotations(parameter.getAnnotationMirrors());
        return model;
    }

    public ParameterModel(String type, String name) {
        this(null, type, name);
    }

    public ParameterModel(String packageName, String type, String name) {
        super(packageName, type, null, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!annotations.isEmpty()) {
            sb.append(StringUtils.join(annotations, " "));
            sb.append(" ");
        }
        if (!modifiers.isEmpty()) {
            sb.append(StringUtils.join(modifiers, " "));
            sb.append(" ");
        }
        sb.append(type + " " + name);
        return sb.toString();
    }

}
