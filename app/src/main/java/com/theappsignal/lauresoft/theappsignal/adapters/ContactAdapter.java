package com.theappsignal.lauresoft.theappsignal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.theappsignal.lauresoft.theappsignal.domain.Contact;
import com.theappsignal.lauresoft.theappsignal.contacts.NewContact;
import com.theappsignal.lauresoft.theappsignal.R;

import java.util.ArrayList;

/**
 * Created by Laure on 9/11/2014.
 */
public class ContactAdapter extends ArrayAdapter<Contact>
{
    private static class ViewHolder {
        private TextView itemView;
    }
    public ArrayList<Contact> contacts;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> contacts)
    {
        super(context, resource,contacts);
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.contact_detail, null);
        }
        Contact contact = contacts.get(position);
        if (contact != null) {
            TextView name = (TextView) v.findViewById(R.id.name_detail);
            TextView number = (TextView) v.findViewById(R.id.number_detail);
            CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkbox);
            if (name != null) {
                name.setText(contact.getName());
            }
            if (number != null) {
                number.setText(contact.getNumber());
            }

            contact.setSelected(checkBox.isSelected());

            final Contact finalContact = contact;

            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getContext(), NewContact.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("kampitem",finalContact);
                    getContext().startActivity(intent);
                }
            });
        }



        return v;
    }




}
