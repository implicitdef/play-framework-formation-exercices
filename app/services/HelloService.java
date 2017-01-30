package services;

import play.Configuration;

import javax.inject.Inject;

public class HelloService {


    private Configuration conf;

    @Inject
    public HelloService(Configuration conf) {
        this.conf = conf;
    }

    public String buildMessage(String name, Boolean uppercase){
        String message = "Bonjour " + name + " ";
        for (int i = 0; i < conf.getInt("hello.exclamationPoints"); i++){
            message += "!";
        }
        return uppercase ? message.toUpperCase() : message;
    }

}
