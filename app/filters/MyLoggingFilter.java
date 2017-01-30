package filters;

import akka.stream.Materializer;
import play.Logger;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class MyLoggingFilter extends Filter {

    @Inject
    public MyLoggingFilter(Materializer mat) {
        super(mat);
    }

    @Override
    public CompletionStage<Result> apply(
            Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
            Http.RequestHeader requestHeader) {
        Logger.info("Juste avant la requête " + requestHeader.uri());
        return nextFilter.apply(requestHeader).thenApply(result -> {
            Logger.info("Juste après la requête " + requestHeader.uri());
            return result;
        });
    }
}
