package com.nimitshah.command;

import com.nimitshah.domain.Message;
import com.nimitshah.domain.User;
import com.nimitshah.repo.UserRepo;

public class PostMessageCommand implements Command{
    private String userName;
    private String message;


    public PostMessageCommand(String userName, String message){
        this.userName = userName;
        this.message = message;
    }
    @Override
    public String execute() {
        User user = UserRepo.getRepo().get(userName);
        if(user == null){
            user = new User(userName);
            UserRepo.getRepo().add(user);
        }
        user.getMessages().add(new Message(message));
        return "";
    }


    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
