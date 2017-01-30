package controllers;

import play.Logger;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public class MyLoggingAction extends play.mvc.Action.Simple {
    public CompletionStage<Result> call(Http.Context ctx) {
        Logger.info("Juste avant la requête " + ctx.request().uri());
        return delegate.call(ctx).thenApply((result) -> {
            Logger.info("Juste après la requête " + ctx.request().uri());
            return result;
        });
    }
}
