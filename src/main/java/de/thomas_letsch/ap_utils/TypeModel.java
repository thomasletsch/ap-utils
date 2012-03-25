package de.thomas_letsch.ap_utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Modifier;

public class TypeModel {

    protected List<AnnotationModel> annotations = new ArrayList<AnnotationModel>();

    protected List<Modifier> modifiers = new ArrayList<Modifier>();

    protected String packageName;

    protected TypeModel generic;

    protected String tab = "";

    protected String type;

    public TypeModel(String type) {
        this.type = type;
    }

    public TypeModel(String packageName, String type) {
        this.packageName = packageName;
        this.type = type;
    }

    public TypeModel(String packageName, String type, TypeModel generic) {
        this.packageName = packageName;
        this.type = type;
        this.generic = generic;
    }

    public List<String> toImports() {
        List<String> list = new ArrayList<String>();
        if (packageName != null) {
            list.add(getFullClassName());
        }
        if (generic != null) {
            list.addAll(generic.toImports());
        }
        return list;
    }

    public String getFullClassName() {
        return (packageName != null) ? packageName + "." + type : type;
    }

    public String toDeclaration() {
        return (generic != null) ? type + "<" + generic.toDeclaration() + ">" : type;
    }

    public TypeModel addModifiers(Modifier... newModifiers) {
        modifiers.addAll(Arrays.asList(newModifiers));
        return this;
    }

    public void addAnnotations(AnnotationModel... newAnnotations) {
        for (AnnotationModel annotationModel : newAnnotations) {
            addAnnotation(annotationModel);
        }
    }

    public void addAnnotation(AnnotationModel newAnnotation) {
        annotations.add(newAnnotation);
    }

    public void addAnnotations(List<? extends AnnotationMirror> annotationMirrors) {
        for (AnnotationMirror annotationMirror : annotationMirrors) {
            AnnotationModel model = AnnotationModel.from(annotationMirror);
            annotations.add(model);
        }
    }

    protected void printAnnotations(StringBuilder sb) {
        for (AnnotationModel annotation : annotations) {
            sb.append(tab + annotation.toString() + "\n");
        }
    }

    public boolean hasAnnotation(String annotation) {
        for (AnnotationModel single : annotations) {
            if (single.isType(annotation)) {
                return true;
            }
        }
        return false;
    }

    public String getPackageName() {
        return packageName;
    }

    public TypeModel setStatic() {
        addModifiers(Modifier.STATIC);
        return this;
    }

    public TypeModel setPublic() {
        addModifiers(Modifier.PUBLIC);
        return this;
    }

    public TypeModel setPrivate() {
        addModifiers(Modifier.PRIVATE);
        return this;
    }

    public TypeModel setProtected() {
        addModifiers(Modifier.PROTECTED);
        return this;
    }

    public boolean hasPackage() {
        return packageName != null;
    }

    public String getType() {
        return type;
    }

}