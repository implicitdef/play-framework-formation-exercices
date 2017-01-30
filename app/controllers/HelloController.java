package controllers;

import play.Logger;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import services.HelloService;

import javax.inject.Inject;


public class HelloController extends Controller {

    private HelloService helloService;
    private WSClient wsClient;

    @Inject
    public HelloController(HelloService helloService, WSClient wsclient) {
        this.helloService = helloService;
        this.wsClient = wsclient;
    }

    public Result hello(String name, boolean uppercase) {
        if (name.length() > 20){
            return badRequest("Nom trop long, pas bonjour !");
        }

        wsClient.url("https://jsonplaceholder.typicode.com/users/5").get().thenApply((response) -> {
            if (response.getStatus() >= 200 && response.getStatus() < 300) {
                String nameFromJson = response.asJson().get("name").asText();
                Logger.info("Name from json : " + nameFromJson);
            }
            throw new RuntimeException("Bad response");
        });

        String message = helloService.buildMessage(name, uppercase);
        return ok(views.html.hello.render(message));
    }


}
