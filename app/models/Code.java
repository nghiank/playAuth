package models;

/**
 * Created by nghian on 3/1/16.
 */
public class Code {

    public Code(String code, int language, String stdinData) {
        this.code = code;
        this.language = language;
        this.stdinData = stdinData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    private int language;

    public String getStdinData() {
        return stdinData;
    }

    public void setStdinData(String stdinData) {
        this.stdinData = stdinData;
    }

    private String stdinData;
}
