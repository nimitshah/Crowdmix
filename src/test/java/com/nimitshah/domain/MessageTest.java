package com.nimitshah.domain;

import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertThat;

public class MessageTest {

    @Test
    public void durationFromCurrentTime() throws Exception {
        Message message = new Message("Hello", LocalDateTime.now().minusDays(1)){};
        assertThat(message.durationFromCurrentTime(), CoreMatchers.equalTo("1 day"));

        Message message1 = new Message("Hello", LocalDateTime.now().minusDays(2)){};
        assertThat(message1.durationFromCurrentTime(), CoreMatchers.equalTo("2 days"));

        Message message2 = new Message("Hello", LocalDateTime.now().minusHours(1)){};
        assertThat(message2.durationFromCurrentTime(), CoreMatchers.equalTo("1 hour"));

        Message message3 = new Message("Hello", LocalDateTime.now().minusHours(3)){};
        assertThat(message3.durationFromCurrentTime(), CoreMatchers.equalTo("3 hours"));

        Message message4 = new Message("Hello", LocalDateTime.now().minusMinutes(1)){};
        assertThat(message4.durationFromCurrentTime(), CoreMatchers.equalTo("1 minute"));

        Message message5 = new Message("Hello", LocalDateTime.now().minusMinutes(3)){};
        assertThat(message5.durationFromCurrentTime(), CoreMatchers.equalTo("3 minutes"));

        Message message6 = new Message("Hello", LocalDateTime.now().minusSeconds(15)){};
        assertThat(message6.durationFromCurrentTime(), CoreMatchers.equalTo("15 seconds"));
    }

    @Test
    public void formattedMessage(){
        Message message = new Message("Hello", LocalDateTime.now().minusDays(1)){};
        assertThat(message.formattedMessage(), CoreMatchers.equalTo("Hello (1 day ago)\n"));
        assertThat(message.formattedMessage(new User("bob")), CoreMatchers.equalTo("bob - Hello (1 day ago)\n"));
    }
}