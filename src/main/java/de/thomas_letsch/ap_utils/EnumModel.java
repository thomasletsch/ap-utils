package de.thomas_letsch.ap_utils;


public class EnumModel extends TopLevelModel {

    public EnumModel(String packageName, String name) {
        super(packageName, "enum", name);
    }

    @Override
    public void addMethod(MethodModel method) {
        method.setIncludeBody(false);
        super.addMethod(method);
    }

    /**
     * No import of field types
     */
    @Override
    public void addField(FieldModel newField) {
        if (newField == null) {
            return;
        }
        if (fields.contains(newField)) {
            return;
        }
        fields.add(newField);
    }

    @Override
    protected void printFields(StringBuilder sb) {
        boolean isFirst = true;
        for (FieldModel field : fields) {
            if (!isFirst) {
                sb.append(", ");
            }
            sb.append(field.getName());
            isFirst = false;
        }
    }
}
