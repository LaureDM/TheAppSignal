package com.theappsignal.lauresoft.theappsignal.message;

import android.app.ActionBar;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.theappsignal.lauresoft.theappsignal.R;
import com.theappsignal.lauresoft.theappsignal.dal.ContactMapper;
import com.theappsignal.lauresoft.theappsignal.dal.MessageMapper;
import com.theappsignal.lauresoft.theappsignal.domain.Contact;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ActionBar actionBar;
    private Button send;
    private String message;
    private ArrayList<Contact> contacts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_home, container, false);

        actionBar = getActivity().getActionBar();
        actionBar.setTitle("Message");

        send = (Button)view.findViewById(R.id.btn_sendMessage);

        contacts = ContactMapper.getInstance().getContacts();
        message = MessageMapper.getInstance().getMessage();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Contact contact : contacts)
                {
                    if(contact.isSelected())
                        sendSMS(contact.getNumber(),message);
                }
            }
        });


        return view;

    }

    // Method to send SMS.
    private void sendSMS(String phoneNumber, String message)
    {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getActivity().getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "SMS failed, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
