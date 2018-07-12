package codesquad.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnswerTest {

    @Test
    public void isWriter(){

        User writer = new User();
        writer.setId((long)1);
        User notWirter = new User();
        writer.setId((long)2);

        Answer answer = new Answer();
        answer.setWriter(writer);

        assertThat(true,is(answer.isWriter(writer)));
        assertThat(false,is(answer.isWriter(notWirter)));

    }

}