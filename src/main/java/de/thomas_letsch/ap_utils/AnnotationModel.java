package de.thomas_letsch.ap_utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;

import org.apache.commons.lang.StringUtils;

public class AnnotationModel {

    private String name;

    private String packageName;

    private Map<String, String> values = new HashMap<String, String>();

    public static AnnotationModel from(AnnotationMirror annotationMirror) {
        AnnotationModel model = new AnnotationModel(annotationMirror.getAnnotationType().toString());
        for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
            model.addValue(entry.getKey().getSimpleName().toString(), entry.getValue());
        }
        return model;
    }

    public static AnnotationModel from(Class<?> annotationClass) {
        AnnotationModel model = new AnnotationModel(annotationClass.getPackage().getName(), annotationClass.getSimpleName());
        return model;
    }

    public AnnotationModel(String name) {
        this.name = name;
    }

    public AnnotationModel(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }

    public AnnotationModel setValue(String value) {
        addValue("value", value);
        return this;
    }

    public void addValue(String key, String value) {
        values.put(key, "\"" + value + "\"");
    }

    public void addValue(String key, AnnotationValue value) {
        values.put(key, value.toString());
    }

    public String getClassName() {
        if (packageName != null) {
            return packageName + "." + name;
        }
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isType(String type) {
        return getClassName().equals(type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("@" + name);
        if (!values.isEmpty()) {
            sb.append("(");
            List<String> valueAssigns = new ArrayList<String>();
            for (Entry<String, String> entry : values.entrySet()) {
                valueAssigns.add(entry.getKey() + " = " + entry.getValue());
            }
            sb.append(StringUtils.join(valueAssigns, ", "));
            sb.append(")");
        }
        return sb.toString();
    }

}
