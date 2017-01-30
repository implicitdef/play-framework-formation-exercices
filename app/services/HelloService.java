package services;

public class HelloService {

    public String buildMessage(String name, Boolean uppercase){
        String message = "Bonjour " + name + " !";
        return uppercase ? message.toUpperCase() : message;
    }

}
