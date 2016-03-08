package codegen.generator;

import codegen.generator.Cplusplus.CplusplusGen;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;

import javax.transaction.NotSupportedException;

import static org.junit.Assert.assertTrue;

/**
 * Created by nghian on 2/25/16.
 */
public class CodeGenFactoryTest {
    @Test
    public void testGetCodeGenerator() throws NotSupportedException {
        CodeGenFactory factory = new CodeGenFactory();
        IGen gen = factory.getCodeGenerator(CodeGenFactory.Lang.CPLUSPLUS);
        assertTrue(gen instanceof CplusplusGen);
    }
}
