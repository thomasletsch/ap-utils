package de.thomas_letsch.ap_utils;



public class ConstructorModel extends MethodModel {

    public static ConstructorModel from(ElementModel model) {
        return new ConstructorModel(model.getName());
    }

    public ConstructorModel(String name) {
        super(name);
        returnObject = null;
    }

}
