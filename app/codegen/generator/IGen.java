package codegen.generator;

import codegen.models.FunctionSignature;

/**
 * Created by nghian on 2/25/16.
 */
public interface IGen {
    public String generateProgram(String userCode, FunctionSignature signature);
    public String generateFunctionSignature(FunctionSignature signature);
}
