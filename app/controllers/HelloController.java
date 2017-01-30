package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.HelloService;

import javax.inject.Inject;


public class HelloController extends Controller {

    private HelloService helloService;

    @Inject
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public Result hello(String name, boolean uppercase) {
        if (name.length() > 20){
            return badRequest("Nom trop long, pas bonjour !");
        }
        String message = helloService.buildMessage(name, uppercase);
        return ok(views.html.hello.render(message));
    }


}
