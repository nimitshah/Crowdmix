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

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CrowdMixCLITest {

    private CrowdMixCLIApp app;

    @Before
    public void setup(){
        app= new CrowdMixCLIApp();
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
    public void createMessageToPersonalTimelineForExistingUser(){
        //given
        User alice = new User("Alice");
        UserRepo.getRepo().add(alice);
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
        alice.getMessages().add(new Message("Hello1", LocalDateTime.now().minusSeconds(5)){});
        alice.getMessages().add(new Message("Hello2", LocalDateTime.now().minusSeconds(1)){});
        UserRepo.getRepo().add(alice);
        String command = "Alice";

        //when
        String res = app.execute(command);

        //then
        assertThat(res.split("\n")[0], containsString("Hello2"));
        assertThat(res.split("\n")[1], containsString("Hello1"));
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
        bob.getMessages().add(new Message("Hi from Bob - 1", LocalDateTime.now().minusSeconds(10)){});
        bob.getMessages().add(new Message("Hi from Bob - 2", LocalDateTime.now().minusSeconds(5)){});
        alice.getFollowing().add(bob);
        User charlie = new User("Charlie");
        charlie.getMessages().add(new Message("Hi from Charlie - 1", LocalDateTime.now().minusSeconds(8)){});
        charlie.getMessages().add(new Message("Hi from Charlie - 2", LocalDateTime.now().minusSeconds(3)){});
        alice.getFollowing().add(charlie);
        UserRepo.getRepo().add(alice);
        UserRepo.getRepo().add(bob);
        UserRepo.getRepo().add(charlie);
        //when
        String res = app.execute("Alice wall");

        //then
        String[] lines= res.split("\n");
        assertThat(lines[0], containsString("Alice - Hi from Alice"));
        assertThat(lines[1], containsString("Charlie - Hi from Charlie - 2"));
        assertThat(lines[2], containsString("Bob - Hi from Bob - 2"));
        assertThat(lines[3], containsString("Charlie - Hi from Charlie - 1"));
        assertThat(lines[4], containsString("Bob - Hi from Bob - 1"));
    }

    @Test
    public void invalidCommand(){
        //given

        //when
        String res = app.execute("asdf asdf");

        //then
        assertThat(res, containsString("Invalid command"));
    }
}