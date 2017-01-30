package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class HelloController extends Controller {

    public Result hello(String name, boolean uppercase) {
        if (name.length() > 20){
            return badRequest("Nom trop long, pas bonjour !");
        }
        String content = "Bonjour " + name + " !";
        content = uppercase ? content.toUpperCase() : content;
        return ok(views.html.hello.render(content));
    }

    public Result goodbye(String name, boolean uppercase) {
        if (name.length() > 20){
            return badRequest("Nom trop long, pas bonjour !");
        }
        String content = "Au revoir " + name + " !";
        content = uppercase ? content.toUpperCase() : content;
        return ok(views.html.goodbye.render(content));
    }

}
