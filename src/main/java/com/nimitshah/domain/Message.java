package com.nimitshah.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class Message {
    private String message;
    private LocalDateTime dateTime;

    public Message(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    protected Message(String message, LocalDateTime dateTime) {
        this(message);
        this.dateTime = dateTime;
    }

    public String durationFromCurrentTime(){
        Duration duration = Duration.between(dateTime, LocalDateTime.now());
        if(duration.toDays() > 0 ){
            return pluralize(duration.toDays(),"day");
        }else if(duration.toHours() > 0){
            return pluralize(duration.toHours(),"hour");
        }else if(duration.toMinutes() > 0){
            return pluralize(duration.toMinutes(),"minute");
        }else{
            return pluralize(duration.toMillis()/1000,"second");
        }
    }

    private String pluralize(long val, String unit) {
        return String.format("%s %s%s", val, unit, val>1?"s":"");
    }

    public String formattedMessage(User user) {
        return String.format("%s - %s (%s ago)\n", user.getUserName(), message, durationFromCurrentTime());
    }

    public String formattedMessage(){
        return String.format("%s (%s ago)\n", message, durationFromCurrentTime());
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
