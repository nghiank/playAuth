package models;

public class CodeCompileResult {

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getLangid() {
        return langid;
    }

    public void setLangid(int langId) {
        this.langid = langId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    private String output;
    private int langid;
    private String code;
    private String errors;
    private double time;
}
