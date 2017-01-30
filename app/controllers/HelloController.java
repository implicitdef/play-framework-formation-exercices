package controllers;

import play.Logger;
import play.cache.Cached;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.HelloService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;


public class HelloController extends Controller {

    private HelloService helloService;
    private WSClient wsClient;

    @Inject
    public HelloController(HelloService helloService, WSClient wsclient) {
        this.helloService = helloService;
        this.wsClient = wsclient;
    }

    @Cached(key = "hello")
    @With(MyLoggingAction.class)
    public CompletionStage<Result> hello(boolean uppercase) {
        Logger.info("Ici je suis vraiment dans l'action");
        return wsClient.url("https://jsonplaceholder.typicode.com/users/5").get().thenApply((response) -> {
            if (response.getStatus() >= 200 && response.getStatus() < 300) {
                String nameFromJson = response.asJson().get("name").asText();
                String message = helloService.buildMessage(nameFromJson, uppercase);
                return ok(views.html.hello.render(message));
            }
            throw new RuntimeException("Bad response");
        });
    }


}
