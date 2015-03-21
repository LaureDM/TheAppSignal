package com.theappsignal.lauresoft.theappsignal.contacts;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.theappsignal.lauresoft.theappsignal.domain.Contact;
import com.theappsignal.lauresoft.theappsignal.dal.ContactMapper;
import com.theappsignal.lauresoft.theappsignal.MainActivity;
import com.theappsignal.lauresoft.theappsignal.R;


public class NewContact extends Activity {

    private static final int PICK_CONTACT = 0;
    private EditText name;
    private EditText number;
    private Button btn_savecontact;
    private Button btn_choosephone;
    private ContactMapper mapper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        name = (EditText)findViewById(R.id.contact_name);
        number = (EditText)findViewById(R.id.contact_number);
        btn_savecontact = (Button)findViewById(R.id.btn_saveContact);
        btn_choosephone = (Button)findViewById(R.id.btn_choosefromphone);

        mapper = ContactMapper.getInstance();

        btn_savecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    Contact contact = new Contact(name.getText().toString(), number.getText().toString(), true);
                    mapper.addContact(contact);
                    showContent(MainActivity.class);
                }
            }
        });

        btn_choosephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        if (hasNumber.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null, null);
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex("data1"));
                            Contact contact = new Contact(name, cNumber, true);
                            ContactMapper.getInstance().addContact(contact);
                            showContent(MainActivity.class);
                        }
                    }
                    break;
                }
        }
    }

    private void showContent(Class content)
    {
        Intent intent = new Intent(this, content);
        String CUSTOM_ACTION = ".NewContact";
        intent.setAction(CUSTOM_ACTION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public boolean isValid() {
        if (name.getText().toString().isEmpty())
            name.setError("Fill in name");
        if (number.getText().toString().isEmpty())
            number.setError("Fill in number");
        else if (number.getText().toString().length() != 10)
            number.setError("Number must be 10 characters");
        else return true;

        return false;
    }
}
