package de.thomas_letsch.ap_utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MethodModel extends ElementModel {

    private List<ParameterModel> parameters = new ArrayList<ParameterModel>();

    protected String returnObject = "void";

    private List<String> body = new ArrayList<String>();

    private List<String> addImports = new ArrayList<String>();

    private boolean includeBody = true;

    public MethodModel(String name) {
        super(null, name);
    }

    public MethodModel(String name, String returnObject) {
        this(name);
        this.returnObject = returnObject;
    }

    public void setReturnObject(String returnObject) {
        if (returnObject == null) {
            returnObject = "void";
        }
        this.returnObject = returnObject;
    }

    public void setIncludeBody(boolean includeBody) {
        this.includeBody = includeBody;
    }

    public void addParameters(ParameterModel... newParameters) {
        parameters.addAll(Arrays.asList(newParameters));
    }

    public void addImports(String... newImports) {
        addImports.addAll(Arrays.asList(newImports));
    }

    @Override
    public List<String> toImports() {
        List<String> imports = super.toImports();
        imports.addAll(addImports);
        return imports;
    }

    public void addLine(String line) {
        if (!line.endsWith("\n")) {
            line = line + "\n";
        }
        body.add(line);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printAnnotations(sb);
        sb.append(tab + StringUtils.join(modifiers, " "));
        if (returnObject != null) {
            sb.append(" " + returnObject);
        }
        sb.append(" " + name + "(");
        sb.append(StringUtils.join(parameters, ", "));
        sb.append(")");
        if (includeBody) {
            sb.append(" {\n");
            for (String line : body) {
                sb.append(tab + TAB + line);
            }
            sb.append(tab + "}\n");
        } else {
            sb.append(";");
        }
        return sb.toString();
    }

    public String toString(String tab) {
        this.tab = tab;
        return toString();
    }

    public String toMethodCall() {
        return name + "()";
    }
}
