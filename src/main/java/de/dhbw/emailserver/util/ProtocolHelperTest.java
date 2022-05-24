package de.dhbw.emailserver.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProtocolHelperTest {
    @Test
    @DisplayName("Parse Protocoll Line")
    void testParseProtocolLine1(){
        String[] l_split = ProtocolHelper.parseProtocolLine("USER mschlegel@usu.com");
        assertArrayEquals(new String[] {"USER", "mschlegel@usu.com"}, l_split);
    }

    @Test
    @DisplayName("Test")
    void readProtocolLine() {
        StringReader l_reader = new StringReader("list\r\n");

        String l_line;

    }

    @Test
    @DisplayName("create Line Response Test")
    void createLineResponsetest(){
        String l_string = ProtocolHelper.createLineResponse("200", "ok");
        assertEquals("200 ok\r\n", l_string);
    }

    @Test
    @DisplayName("multible")
    void multibleTeste(){
        final List<String> l_msg = Arrays.asList("MSG_1", "MSG_2", ".MSG_3");

        String l_string = ProtocolHelper.createMultiLineResponse("200", "ok", l_msg);
        final StringBuilder l_builder = new StringBuilder();
        l_builder.append("200").append(" ").append("ok").append("\r\n");
        l_msg.forEach((line)  -> {
            if (line.startsWith(".")) l_builder.append(".");
            l_builder.append(line).append("\r\n");
        });
        l_builder.append(".\r\n");
        assertEquals(l_builder.toString(), l_string);
    }
    /*
    @Test
    @DisplayName("Single Line")
    void readProtocolLine2() {
        StringReader l_reader = new StringReader("list\r\n");

        String l_line;
        try {
            l_line = ProtocolHelper.readProtocolLine(l_reader);
        } catch (IOException e) {
            fail(e);
            return;
        }

        assertEquals("list", l_line);
    }

    @Test
    @DisplayName("Multiple Lines")
    void readProtocolLine3() {
        StringReader l_reader = new StringReader("list\r\ndele\r\n");

        String l_line;
        try {
            l_line = ProtocolHelper.readProtocolLine(l_reader);
            assertEquals("list", l_line);
            l_line = ProtocolHelper.readProtocolLine(l_reader);
            assertEquals("dele", l_line);

        } catch (IOException e) {
            fail(e);
            return;
        }

    }

    @Test
    @DisplayName("EOL")
    void readProtocolLine4() {
        StringReader l_reader = new StringReader("ab\r");

        String l_line = null;
        try {
            l_line = ProtocolHelper.readProtocolLine(l_reader);


        } catch (IOException e) {
            return;
        }
        fail("Exception expected!" +l_line);


    }*/
}