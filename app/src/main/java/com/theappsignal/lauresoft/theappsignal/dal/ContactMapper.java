package com.theappsignal.lauresoft.theappsignal.dal;

import com.theappsignal.lauresoft.theappsignal.domain.Contact;

import java.util.ArrayList;

/**
 * Created by Laure on 9/11/2014.
 */
public class ContactMapper
{
    private static ContactMapper instance = null;

    public static ContactMapper getInstance()
    {
        if(instance==null)
            instance = new ContactMapper();

        return instance;
    }
    private ArrayList<Contact> contacts;

    private ContactMapper()
    {
        contacts = new ArrayList<Contact>();
        addContact(new Contact("Laure","+32499213660",true));
    }

    public void addContact(Contact contact)
    {
        contacts.add(contact);
    }

    public void removeContact(Contact contact)
    {
        contacts.remove(contact);
    }

    public ArrayList<Contact> getContacts()
    {
        return contacts;
    }
}
