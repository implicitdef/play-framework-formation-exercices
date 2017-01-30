package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class HelloController extends Controller {

    public Result hello(String name, boolean uppercase) {
        if (name.length() > 20){
            return badRequest("Nom trop long, pas bonjour !");
        }
        String content = "Bonjour " + name + " !";
        return ok(uppercase ? content.toUpperCase() : content);
    }


    public Result storeName(String name) {
        session().put("name", name);
        return ok();
    }

    public Result sayHelloToStoredName(){
        String name  = session().get("name");
        if (name == null){
            name = "Unknown";
        }
        return ok("Bonjour " + name + " !");
    }

}
