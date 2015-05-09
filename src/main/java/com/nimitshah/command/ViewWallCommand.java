package com.nimitshah.command;

import com.nimitshah.domain.Message;
import com.nimitshah.domain.User;
import com.nimitshah.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewWallCommand implements Command{
    private String userName;
    public ViewWallCommand(String userName){
        this.userName = userName;
    }


    @Override
    public String execute() {
        User user = UserRepo.getRepo().get(userName);
        if(user == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        user.getMessagesSortByEarliestTime().forEach(message -> {
            sb.append(message.formattedMessage(user));
        });
        sb.append(messagesFromFollowings(user));
        return sb.toString();
    }

    private String messagesFromFollowings(User user) {
        StringBuilder sb = new StringBuilder();
        List<Message> allUserMessages = new ArrayList<>();
        user.getFollowing().forEach(user1 -> {
            List<Message> userMessages= user1.getMessages().stream()
                    .map(m -> new Message(m.formattedMessage(user1), m.getDateTime()) {
                    })
                    .collect(Collectors.toList());
            allUserMessages.addAll(userMessages);
        });
        allUserMessages.sort((m1,m2)->m2.getDateTime().compareTo(m1.getDateTime()));
        allUserMessages.forEach(message -> {sb.append(message.getMessage());});
        return sb.toString();
    }

    @Override
    public String getUserName() {
        return userName;
    }
}
