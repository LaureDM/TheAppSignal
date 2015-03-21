package com.theappsignal.lauresoft.theappsignal.contacts;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.theappsignal.lauresoft.theappsignal.domain.Contact;
import com.theappsignal.lauresoft.theappsignal.dal.ContactMapper;
import com.theappsignal.lauresoft.theappsignal.R;
import com.theappsignal.lauresoft.theappsignal.adapters.ContactAdapter;

import java.util.ArrayList;


public class ContactsFragment extends Fragment implements View.OnClickListener {

    private Button btn_AddContact;
    private ListView contactList;
    private TextView noContacts;

    private ArrayList<Contact> contacts;
    private ContactMapper mapper;
    private ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_contacts, container, false);

        actionBar = getActivity().getActionBar();
        actionBar.setTitle("New Contact");

        btn_AddContact = (Button)view.findViewById(R.id.btn_addContact);
        contactList = (ListView)view.findViewById(R.id.contactList);
        noContacts = (TextView)view.findViewById(R.id.txt_noContacts);

        contactList.setAdapter(null);

        mapper = ContactMapper.getInstance();


        contacts = mapper.getContacts();

        if(!contacts.isEmpty())
        {
            noContacts.setVisibility(View.GONE);
        }


        ContactAdapter contactAdapter = new ContactAdapter(getActivity(),R.layout.contact_detail,contacts);

        contactList.setAdapter(contactAdapter);

        btn_AddContact.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v)
    {
        showContent(NewContact.class);
    }

    private void showContent(Class content)
    {
        Intent intent = new Intent(getActivity(), content);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
