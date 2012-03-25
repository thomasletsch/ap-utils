package de.thomas_letsch.ap_utils;


public class ElementModel extends TypeModel {

    public static final String TAB = "    ";

    protected String name;

    public ElementModel(String type, String name) {
        super(type);
        this.name = name;
    }

    public ElementModel(String packageName, String type, String name) {
        super(packageName, type);
        this.name = name;
    }

    public ElementModel(String packageName, String type, TypeModel generic, String name) {
        super(packageName, type, generic);
        this.name = name;
    }

    ElementModel() {
        super(null);
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ElementModel)) {
            return false;
        }
        ElementModel other = (ElementModel) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}