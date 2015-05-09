package com.nimitshah.command;

import com.nimitshah.domain.User;
import com.nimitshah.repo.UserRepo;

public class ReadTimelineCommand implements Command {
    private String userName;

    public ReadTimelineCommand(String userName) {
        this.userName = userName;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        User user = UserRepo.getRepo().get(userName);
        if(user == null){
            return "";
        }
        user.getMessagesSortByEarliestTime().forEach(message -> {
            sb.append(message.formattedMessage());
        });
        return sb.toString();
    }

    @Override
    public String getUserName() {
        return userName;
    }
}
