package codegen.generator.Cplusplus;

import codegen.generator.IGen;
import codegen.models.FunctionSignature;
import codegen.models.Type;
import com.google.common.collect.Lists;
import controllers.Assets;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nghian on 2/25/16.
 */
public class CplusplusGen implements IGen {

    //File template
    public static final String FILE_MAIN_BODY = "mainBody.cc";
    public static final String FILE_HEADER = "header.cc";
    public static final String HEADER_SECTION = "includeHeader";

    public static final List<String> FILE_NAME_READ_TEMPLATE = Arrays.asList("single.cc", "array1D.cc", "array2D.cc");

    //Section need to be replaced
    public static final String USER_SOLUTION_SECTION = "userFunctionSolution";
    public static final String READ_DECLARE_SECTION = "readAndDeclare";
    public static final String CALL_USER_SOLUTION_SECTION = "callUserSolution";
    public static final String WRITE_USER_OUTPUT = "writeUserOutput";
    public static final String VARIABLE_NAME_SECTION = "variableName";
    public static final String VARIABLE_TYPE_SECTION = "variableType";

    final static Charset ENCODING = StandardCharsets.UTF_8;

    @Override
    public String generateProgram(String userCode, FunctionSignature functionSignature) {
        try {
            String program = readFile(FILE_MAIN_BODY);
            String header = readFile(FILE_HEADER);

            String readAndDeclare = generateBody(functionSignature);
            String callUserFunction = generateCallUserFunction(functionSignature);
            String writeUserOutput = generateWriteUserOutput(functionSignature);

            program = program.replace(HEADER_SECTION, header);
            program = program.replace(USER_SOLUTION_SECTION, userCode);
            program = program.replace(READ_DECLARE_SECTION, readAndDeclare);
            program = program.replace(CALL_USER_SOLUTION_SECTION, callUserFunction);
            program = program.replace(WRITE_USER_OUTPUT, writeUserOutput);
            return program;
        }catch(IOException ex) {
            return "";
        }
    }

    @Override
    public String generateFunctionSignature(FunctionSignature functionSignature) {
        String res = getFullName(functionSignature.getReturnType());
        res += " ";
        res += functionSignature.getName();
        res += "(";
        for (int i = 0; i < functionSignature.getParams().size(); i++) {
            Type type = functionSignature.getParams().get(i);
            if (type.isContainer() || type == Type.STRING) {
                res += "const ";
                res += getFullName(type);
                res += "&";
            } else {
                res += getFullName(type);
            }
            res += " ";
            res += getVariableName(i);
            if (i != functionSignature.getParams().size() - 1) {
                res += ", ";
            }
        }

        res += ")";
        return res;
    }

    private String getFullName(Type type) {
        if (!type.isContainer()) {
            return type.getName();
        }
        String res = type.getName();
        res += "<";
        res += getFullName(type.getSubType());
        res += ">";
        return res;
    }

    private String readFile(String fileRelativePath) throws IOException {
        String filePath = "public/snippet";
        String fileName = filePath + "/";
        fileName += fileRelativePath;
        String res = "";
        Path path = Paths.get(fileName);
        try {
            res = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            System.err.println("Exception when reading file " + ex.getMessage());
            throw ex;
        }
        return res;
    }

    private String getVariableName(int index) {
        return Character.toString((char) (index + 97));
    }

    private String getFileNameTemplate(Type type) {
        int cnt = 0; // detect if it is non-array, 1D array or 2D array
        while(type != null) {
            Type subtypes = type.getSubType();
            if (type.getName().equals("vector")) {
                ++cnt;
            }
            type = subtypes;
        }
        return FILE_NAME_READ_TEMPLATE.get(cnt);
    }

    private String genReadFromFile(Type type, String variableName) throws IOException {
        String fileNameTemplate = getFileNameTemplate(type);
        String code = readFile("read/" + fileNameTemplate);
        code = code.replaceAll(VARIABLE_NAME_SECTION, variableName);
        String variableType = getFullName(type);
        code = code.replaceAll(VARIABLE_TYPE_SECTION, variableType);
        return code;
    }

    private String generateBody(FunctionSignature functionSignature) throws IOException {
        List<Type> types = functionSignature.getParams();

        List<String> arr = new ArrayList<String>();

        for (int i = 0; i < types.size(); ++i) {
            arr.add(genReadFromFile(types.get(i), getVariableName(i)));
        }
        return String.join("\n", arr);
    }

    private String generateCallUserFunction(FunctionSignature functionSignature) throws IOException {
        List<Type> params = functionSignature.getParams();
        int sz = params.size();
        String variableName = this.getVariableName(sz);

        String res = this.getFullName(functionSignature.getReturnType());
        res += " " + variableName + " = ";
        res += functionSignature.getName();
        res += "(";
        List<String> arr = new ArrayList<String>();
        for(int i = 0; i < sz; ++i) {
            arr.add(this.getVariableName(i));
        }
        res += String.join(",", arr);
        res += ");";

        return res;
    }

    private String generateWriteUserOutput(FunctionSignature functionSignature) throws IOException {
        List<Type> params = functionSignature.getParams();
        int sz = params.size();
        String variableName = this.getVariableName(sz);

        String fileNameTemplate = "write/" + getFileNameTemplate(functionSignature.getReturnType());
        String code = readFile(fileNameTemplate);
        code = code.replace(VARIABLE_NAME_SECTION, variableName);
        return code;
    }
}
