package codegen.generator;

import codegen.generator.Cplusplus.CplusplusGen;
import codegen.generator.IGen;

import javax.transaction.NotSupportedException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nghian on 2/25/16.
 */
public class CodeGenFactory {
    public enum Lang {
        CPLUSPLUS,
        JAVA,
        PYTHON,
        Unsupported
    }

    private static Map<Lang, IGen> langGen;;
    {{
        langGen = new HashMap<Lang, IGen>();
        langGen.put(Lang.CPLUSPLUS, new CplusplusGen());
    }};

    public IGen getCodeGenerator(Lang lang) throws NotSupportedException {
        if (!langGen.containsKey(lang)) {
            throw new NotSupportedException();
        }
        return langGen.get(lang);
    }
}
