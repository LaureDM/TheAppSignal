package com.theappsignal.lauresoft.theappsignal.message;

import android.app.ActionBar;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.theappsignal.lauresoft.theappsignal.R;
import com.theappsignal.lauresoft.theappsignal.dal.MessageMapper;
import com.theappsignal.lauresoft.theappsignal.util.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MessageFragment extends Fragment {

    private EditText message;
    private MessageMapper mapper;
    private ActionBar actionBar;
    private Button save;
    private TextView location;
    GPSTracker gps;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(
                R.layout.fragment_message, container, false);

        actionBar = getActivity().getActionBar();
        actionBar.setTitle("Message");

        gps = new GPSTracker(this.getActivity());

        mapper = MessageMapper.getInstance();
        message = (EditText)view.findViewById(R.id.message);
        location = (TextView)view.findViewById(R.id.gpsLocation);
        try {
            location.setText(locationToString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        save = (Button)view.findViewById(R.id.btn_saveMessage);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    mapper.setMessage(message.getText().toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Message saved",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    private String locationToString() throws IOException {

        String location = "test";
        Geocoder geocoder;
        if(gps.canGetLocation())
        {

            List<Address> addresses;
            geocoder = new Geocoder(this.getActivity(), Locale.getDefault());
            addresses = geocoder.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);

            String a = addresses.get(0).getAddressLine(0)+"\n";
            String b = addresses.get(0).getAddressLine(1)+"\n";
            String c = addresses.get(0).getAddressLine(2)+"\n";

        }
        else
            gps.showSettingsAlert();

        gps.stopUsingGPS();
        return location;
    }

    public boolean isValid()
    {
        if(message.getText().toString().isEmpty())
            message.setError("Fill in a message");
        else return true;
        return false;
    }

    private void showContent(Class content)
    {
        Intent intent = new Intent(getActivity(), content);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
