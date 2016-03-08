package codegen.generator.Cplusplus;

import codegen.models.FunctionSignature;
import codegen.models.Type;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nghian on 2/25/16.
 */
public class CplusplusGenTest {


    @Test
    public void testGenerateFunctionSignatureWithTwoParams() {
        CplusplusGen gen = new CplusplusGen();
        String name = "twosum";
        List<Type> params = new ArrayList<Type>();
        params.add(Type.DOUBLE);
        params.add(Type.STRING);
        FunctionSignature signature = new FunctionSignature(name, Type.INT, params);
        String res = gen.generateFunctionSignature(signature);
        assertEquals("int twosum(double a, const string& b)", res);
    }

    @Test
    public void testGenerateFunctionSignatureWithParamVector() {
        CplusplusGen gen = new CplusplusGen();
        String name = "twosum";
        List<Type> params = new ArrayList<Type>();
        params.add(Type.VEC_DOUBLE);
        FunctionSignature signature = new FunctionSignature(name, Type.INT, params);
        String res = gen.generateFunctionSignature(signature);
        assertEquals("int twosum(const vector<double>& a)", res);
    }

    @Test
    public void testGenerateFunctionSignatureWithReturnVector() {
        CplusplusGen gen = new CplusplusGen();
        String name = "twosum";
        List<Type> params = new ArrayList<Type>();
        params.add(Type.VEC_DOUBLE);
        params.add(Type.INT);
        FunctionSignature signature = new FunctionSignature(name, Type.VEC_INT, params);
        String res = gen.generateFunctionSignature(signature);
        assertEquals("vector<int> twosum(const vector<double>& a, int b)", res);
    }

    @Test
    public void testGenerateProgram() {
        CplusplusGen gen = new CplusplusGen();
        String name = "twosum";
        List<Type> params = new ArrayList<Type>();
        params.add(Type.VEC_DOUBLE);
        params.add(Type.INT);
        FunctionSignature signature = new FunctionSignature(name, Type.VEC_INT, params);
        String res = gen.generateProgram("vector<int> twosum(const vector<double>& a, int b){ return vector<int>();}", signature);
        assertTrue(res.contains("twosum"));
    }
}
