package com.example.myapplication500;

import android.net.Uri;
import android.os.Bundle;
//import android.support.annotation.NonNull;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends WearableActivity implements DataClient.OnDataChangedListener,
        MessageClient.OnMessageReceivedListener,
        CapabilityClient.OnCapabilityChangedListener {

    private TextView mTextView;
    private static final String COUNT_KEY = "com.example.torbengab.sendwatchmessage";
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text34);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Instantiates clients without member variables, as clients are inexpensive to create and
        // won't lose their listeners. (They are cached and shared between GoogleApi instances.)
        Wearable.getDataClient(this).addListener(this);
        Wearable.getMessageClient(this).addListener(this);
        Wearable.getCapabilityClient(this)
                .addListener(
                        this, Uri.parse("wear://"), CapabilityClient.FILTER_REACHABLE);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Wearable.getDataClient(this).removeListener(this);
        Wearable.getMessageClient(this).removeListener(this);
        Wearable.getCapabilityClient(this).removeListener(this);
    }

    @Override
    public void onDataChanged( DataEventBuffer dataEventBuffer) {
        //Toast.makeText(getApplicationContext(), "elie", Toast.LENGTH_LONG).show();
        mTextView.setText("tu as gagner");
        //vibrator.vibrate( 1000);
        for (DataEvent event : dataEventBuffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed ou ajouter
                DataItem item = event.getDataItem();
                //if (item.getUri().getPath().compareTo("/message") == 0) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    //Toast.makeText(getApplicationContext(), String.valueOf(dataMap.getInt(COUNT_KEY)), Toast.LENGTH_LONG).show();
                    if (dataMap.getInt(COUNT_KEY)%2==0) {
                        vibrator.vibrate( 1000);
                    }

                //}
            } else if (event.getType() == DataEvent.TYPE_DELETED) {
                // DataItem deleted
            }
        }
    }

    @Override
    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        Toast.makeText(getApplicationContext(), "yoy", Toast.LENGTH_LONG).show();
        //mTextView.setText("yoy");

    }

    @Override
    public void onMessageReceived( MessageEvent messageEvent) {
        Toast.makeText(getApplicationContext(), "sandrine", Toast.LENGTH_LONG).show();
        //mTextView.setText("yey");

    }
}
