package com.nimitshah.command;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class CommandFactoryTest {
    @Test
    public void publishCommand() {
        Command cmd = CommandFactory.getCommand("Alice -> Hello");
        assertThat(cmd, instanceOf(PostMessageCommand.class));
        assertThat(cmd.getUserName(), CoreMatchers.equalTo("Alice"));
        assertThat(((PostMessageCommand)cmd).getMessage(), CoreMatchers.equalTo("Hello"));
    }

    @Test
    public void viewWallCommand() {
        Command cmd = CommandFactory.getCommand("Alice wall");
        assertThat(cmd, instanceOf(ViewWallCommand.class));
        assertThat(cmd.getUserName(), CoreMatchers.equalTo("Alice"));
    }

    @Test
    public void followCommand() {
        Command cmd = CommandFactory.getCommand("Alice follows Bob");
        assertThat(cmd, instanceOf(FollowUserCommand.class));
        assertThat(cmd.getUserName(), CoreMatchers.equalTo("Alice"));
        assertThat(((FollowUserCommand)cmd).getFollowUser(), CoreMatchers.equalTo("Bob"));
    }

    @Test
    public void readPersonalTimelineCommand() {
        Command cmd = CommandFactory.getCommand("Alice");
        assertThat(cmd, instanceOf(ReadTimelineCommand.class));
        assertThat(cmd.getUserName(), CoreMatchers.equalTo("Alice"));
    }
}