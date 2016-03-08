package codegen.models;

import java.util.List;

/**
 * Created by nghian on 2/25/16.
 */
public class FunctionSignature {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public List<Type> getParams() {
        return params;
    }

    public void setParams(List<Type> params) {
        this.params = params;
    }

    public FunctionSignature(String name, Type returnType, List<Type> params) {

        this.name = name;
        this.returnType = returnType;
        this.params = params;
    }

    private String name;
    private Type returnType;
    private List<Type> params;

}
