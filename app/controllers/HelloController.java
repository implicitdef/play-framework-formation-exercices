package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class HelloController extends Controller {

    public Result hello(String name, boolean uppercase) {
        String content = "Bonjour " + name + " !";
        return ok(uppercase ? content.toUpperCase() : content);
    }

}
