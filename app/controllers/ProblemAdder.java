package controllers;

import codegen.models.Type;
import play.data.Form;
import play.mvc.*;
import providers.ProblemForm;
import views.html.problem;

import static play.data.Form.form;

public class ProblemAdder extends Controller {

    public static final Form<ProblemForm> ADD_PROBLEM_FORM = form(ProblemForm.class);

    public static Result problem() {
        return ok(problem.render(ADD_PROBLEM_FORM));
    }

    public static Result doCreateProblem() {
        //TODO : need to authenticate user's session
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<ProblemForm> filledForm = ADD_PROBLEM_FORM.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(problem.render(filledForm));
        } else {
            return handleNewProblem(ctx());
        }
    }

    private static Result handleNewProblem(final Http.Context ctx) {
        return redirect(routes.Application.index());
    }
}
