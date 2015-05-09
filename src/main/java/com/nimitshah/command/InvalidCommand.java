package com.nimitshah.command;

public class InvalidCommand implements Command {
    private String stCommand;
    public InvalidCommand(String stCommand){

    }
    @Override
    public String execute() {
        return "Invalid command: "+stCommand+"\n";
    }

    @Override
    public String getUserName() {
        return null;
    }


}
