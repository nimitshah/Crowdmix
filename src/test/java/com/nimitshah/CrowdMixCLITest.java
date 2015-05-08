package com.nimitshah;

import com.nimitshah.domain.Message;
import com.nimitshah.domain.User;
import com.nimitshah.repo.UserRepo;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CrowdMixCLITest {

    private CrowdMixCLIApp app= new CrowdMixCLIApp();

    @Before
    public void setup(){

    }

    @After
    public void teardown(){
        UserRepo.getRepo().clear();
    }

    @Test
    public void createNewUserWithMessageToPersonalTimeline(){
        //given
        String command = "Alice -> I love the weather today";

        //when
        app.execute(command);

        //then
        User user = UserRepo.getRepo().get("Alice");
        assertNotNull(user);
        assertThat(1, equalTo(user.getMessages().size()));
    }

    @Test
    public void viewPersonalTimeline(){
        //given
        User alice = new User("Alice");
        alice.getMessages().add(new Message("Hello"));
        UserRepo.getRepo().add(alice);
        String command = "Alice";

        //when
        String res = app.execute(command);

        //then
        assertThat(res, containsString("Hello"));
    }

    @Test
    public void userFollowsAnotherUser(){
        //given
        User alice = new User("Alice");
        User bob = new User("Bob");
        UserRepo.getRepo().add(alice);
        UserRepo.getRepo().add(bob);
        String command = "Alice follows Bob";

        //when
        String res = app.execute(command);

        //then
        alice = UserRepo.getRepo().get("Alice");
        assertThat(1, equalTo(alice.getFollowing().size()));
        assertThat(alice.getFollowing().get(0).getUserName(), equalTo("Bob"));
    }

    @Test
    public void viewUserWall(){
        //given
        User alice = new User("Alice");
        alice.getMessages().add(new Message("Hi from Alice"));
        User bob = new User("Bob");
        bob.getMessages().add(new Message("Hi from Bob"));
        alice.getFollowing().add(bob);

        //when
        String res = app.execute("Alice wall");

        //then
        String[] lines= res.split("\n");
        assertThat(lines[0], containsString("Alice - Hi from Alice"));
        assertThat(lines[1], containsString("Bob - Hi from Bob"));
    }
}