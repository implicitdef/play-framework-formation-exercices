package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import objects.MessageResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


public class HelloController extends Controller {

    public Result hello(String name, boolean uppercase) {
        if (name.length() > 20){
            return badRequest("Nom trop long, pas bonjour !");
        }
        String content = "Bonjour " + name + " !";
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(uppercase ? content.toUpperCase() : content);
        JsonNode jsonNode = Json.toJson(messageResponse);
        return ok(jsonNode);
    }

}
