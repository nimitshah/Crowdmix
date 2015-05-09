package com.nimitshah.command;

import com.nimitshah.domain.User;
import com.nimitshah.repo.UserRepo;

public class FollowUserCommand implements Command{
    private String userName;
    private String followUser;

    public FollowUserCommand(String userName, String followUser){
        this.userName = userName;
        this.followUser = followUser;
    }
    @Override
    public String execute() {
        User user = UserRepo.getRepo().get(userName);
        User userToFollow = UserRepo.getRepo().get(followUser);
        if(user!=null && userToFollow!=null){
            user.getFollowing().add(userToFollow);
        }
        return "";
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public String getFollowUser() {
        return followUser;
    }
}
