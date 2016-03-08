package codegen.models;


public enum Type {

    INT("int", null),
    DOUBLE("double", null),
    STRING("string", null),
    VEC_INT("vector", Type.INT),
    VEC_DOUBLE("vector", Type.DOUBLE),
    VEC_STRING("vector", Type.STRING),
    VEC_VEC_INT("vector", Type.VEC_INT),
    VEC_VEC_DOUBLE("vector", Type.VEC_DOUBLE),
    VEC_VEC_STRING("vector", Type.VEC_STRING);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getSubType() {
        return subType;
    }

    public void setSubType(Type subType) {
        this.subType = subType;
    }

    Type(String name, Type subType) {
        this.name = name;
        this.subType = subType;
    }

    public boolean isContainer() {
        return name.equals("vector");
    }

    private String name;
    private Type subType;
}
