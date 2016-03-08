package codegen.generator.Cplusplus;

import codegen.models.FunctionSignature;
import codegen.models.Type;
import models.Code;
import models.CodeCompileResult;
import org.junit.Test;
import play.libs.ws.WS;
import service.CodeCompileService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nghian on 3/7/16.
 */
public class CplusplusGenIntegTest {
    public static final int LANG_ID = 7;
    public static final String URL = "http://localhost:2015/compile";

    @Test
    public void testBuildFailed() {
        String  str = "int main1() {}";
        Code code = new Code(str, LANG_ID, "");
        CodeCompileService service = new CodeCompileService(code, URL, WS.newClient(2005));
        CodeCompileResult result = service.compile();
        assertNotNull(result.getErrors());
        assertNotNull(result.getOutput());
    }

    @Test
    public void testBuildOk() {
        String  str = "int main() { return 0; }";
        Code code = new Code(str, LANG_ID, "");
        CodeCompileService service = new CodeCompileService(code, URL, WS.newClient(2005));
        CodeCompileResult result = service.compile();
        assertTrue(result.getErrors().isEmpty());
        assertTrue(result.getOutput().isEmpty());
    }

    @Test
    public void testGenerateProgramAndBuildFine() {
        CplusplusGen gen = new CplusplusGen();
        String name = "twosum";
        List<Type> params = new ArrayList<Type>();
        params.add(Type.VEC_DOUBLE);
        params.add(Type.INT);
        FunctionSignature signature = new FunctionSignature(name, Type.VEC_INT, params);
        String str = gen.generateProgram("vector<int> twosum(const vector<double>& a, int b){ return vector<int>();}", signature);
        System.out.println("String="+str);
        CodeCompileResult result = compile(str);
        assertTrue(result.getErrors().isEmpty());
        assertTrue(result.getOutput().isEmpty());
    }

    private CodeCompileResult compile(String codeStr) {
        Code code = new Code(codeStr, LANG_ID, "");
        CodeCompileService service = new CodeCompileService(code, URL, WS.newClient(2005));
        CodeCompileResult result = service.compile();
        return result;
    }
}
