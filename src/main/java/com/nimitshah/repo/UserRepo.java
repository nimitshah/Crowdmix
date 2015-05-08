package com.nimitshah.repo;

import com.nimitshah.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    private Map<String, User> userMap = new HashMap<>();

    private static final UserRepo repo = new UserRepo();

    private UserRepo(){
    }

    public static UserRepo getRepo(){
        return repo;
    }

    public User get(String user){
        return userMap.get(user);
    }

    public void add(User user){
        userMap.put(user.getUserName(), user);
    }

    public void clear() {
        userMap.clear();
    }
}
