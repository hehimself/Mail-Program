package de.dhbw.emailserver.util;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ProtocolHelper {
    public static String readProtocolLine(Reader p_reader) throws IOException {
        StringBuilder l_builder = new StringBuilder();
        int l_char;
        while ((l_char = p_reader.read()) != -1){
            l_builder.append(l_char);
            if (l_builder.length() >= 2){
                char lastChar = l_builder.charAt(l_builder.length() -1);
                char prelastChar =  l_builder.charAt(l_builder.length()-2);
                if (prelastChar == '\r' && lastChar == '\n'){
                    break;
                }
            }
        }
        l_builder.deleteCharAt(l_builder.length() -1);
        l_builder.deleteCharAt(l_builder.length() -2);
        return l_builder.toString();
    }

    public static String[] parseProtocolLine(String p_line){
        return p_line.split(" ", 2);
    }

    public static String createLineResponse( String p_statusCode, String p_statusMessage ){
        if (p_statusMessage != null && !p_statusMessage.isBlank()){
            return p_statusCode + " " + p_statusMessage + "\r\n";
        }
        return p_statusCode+ "\r\n";
    }

    public static String createMultiLineResponse(String p_statusCode, String p_statusMessage, List<String> p_messages){
        StringBuilder l_builder = new StringBuilder();

        l_builder.append(createLineResponse(p_statusCode,p_statusMessage));
        if (p_messages != null && !p_messages.isEmpty()){
            for (String l_msg : p_messages){
                l_builder.append(l_msg).append("\r\n");
            }
        }
        return l_builder.toString();
    }

}
