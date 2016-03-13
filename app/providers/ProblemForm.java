package providers;

import codegen.models.Type;
import play.data.validation.Constraints;

import java.util.List;

/**
 * Created by nghian on 3/8/16.
 */
public class ProblemForm {
    @Constraints.Required
    public String name;

    @Constraints.Required
    public Type returnType;

    @Constraints.Required
    public String desc;

    @Constraints.Required
    public String functionName;

    @Constraints.Required
    public List<Type> params;
}
