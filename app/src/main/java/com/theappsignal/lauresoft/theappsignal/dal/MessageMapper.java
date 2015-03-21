package com.theappsignal.lauresoft.theappsignal.dal;

/**
 * Created by Laure on 10/11/2014.
 */
public class MessageMapper
{
    private String message = "Help, I am in danger";
    private static MessageMapper instance;
    private MessageMapper()
    {

    }

    public static MessageMapper getInstance()
    {
        if(instance==null)
            instance = new MessageMapper();

        return instance;
    }
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String m)
    {
        message = m;
    }
}
