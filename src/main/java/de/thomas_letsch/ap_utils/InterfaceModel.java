package de.thomas_letsch.ap_utils;


public class InterfaceModel extends TopLevelModel {

    public InterfaceModel(String packageName, String name) {
        super(packageName, "interface", name);
    }

    @Override
    public void addMethod(MethodModel method) {
        method.setIncludeBody(false);
        super.addMethod(method);
    }
}
