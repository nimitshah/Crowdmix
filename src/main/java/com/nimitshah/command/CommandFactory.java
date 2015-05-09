package com.nimitshah.command;

public class CommandFactory {
    private static final String POST_MESSAGE_COMMAND = "^(\\w*)\\s*->\\s*(.+)";
    private static final String VIEW_WALL_COMMAND = "^(\\w*)\\s*wall";
    private static final String FOLLOW_USER_COMMAND = "^(\\w*)\\s*follows\\s*(\\w*)";
    private static final String READ_TIMELINE_COMMAND = "^(\\w*)";

    public static Command getCommand(String command){
        if(command.matches(POST_MESSAGE_COMMAND)){
            return new PostMessageCommand(command.split("->")[0].trim(), command.split("->")[1].trim());
        }else if(command.matches(VIEW_WALL_COMMAND)){
            return new ViewWallCommand(command.split(" ")[0]);
        }else if(command.matches(FOLLOW_USER_COMMAND)){
            return new FollowUserCommand(command.split(" follows ")[0], command.split(" follows ")[1]);
        }else if(command.matches(READ_TIMELINE_COMMAND)){
            return new ReadTimelineCommand(command.trim());
        }else{
            return new InvalidCommand(command);
        }

    }
}
