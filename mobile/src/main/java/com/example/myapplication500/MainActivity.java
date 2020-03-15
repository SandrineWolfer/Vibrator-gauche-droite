package com.example.myapplication500;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends AppCompatActivity {
 Button button;
 Button button2;
 DataClient mDataClient;
 int j=2;
 int x=1;
    private static final String COUNT_KEY = "com.example.torbengab.sendwatchmessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStuff();

                //sendStuff3();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStuff2();

                //sendStuff3();
            }
        });
    }

    private void sendStuff() {
        j=j+2;

        mDataClient = Wearable.getDataClient(this);
        final PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/message").setUrgent();
        putDataMapReq.getDataMap().putInt(COUNT_KEY, j);
        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest().setUrgent();
        Task<DataItem> putDataTask = mDataClient.putDataItem(putDataReq);

        putDataTask.addOnSuccessListener(new OnSuccessListener<DataItem>() {
            @Override
            public void onSuccess(DataItem dataItem) {
                //Toast.makeText(getApplicationContext(), "sending :)" +putDataMapReq, Toast.LENGTH_LONG).show();


            }
        });
        if(j>100){j=0;}

    }

    private void sendStuff2() {
        x= x+2;

        mDataClient = Wearable.getDataClient(this);
        final PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/message").setUrgent();
        putDataMapReq.getDataMap().putInt(COUNT_KEY, x);
        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest().setUrgent();
        Task<DataItem> putDataTask = mDataClient.putDataItem(putDataReq);

        putDataTask.addOnSuccessListener(new OnSuccessListener<DataItem>() {
            @Override
            public void onSuccess(DataItem dataItem) {
                //Toast.makeText(getApplicationContext(), "sending :)" +putDataMapReq, Toast.LENGTH_LONG).show();

            }
        });
        if(x>101){x=1;}

    }

    private void sendStuff3() {

        mDataClient = Wearable.getDataClient(this);
        final PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/message").setUrgent();
        putDataMapReq.getDataMap().putInt(COUNT_KEY, 3);
        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest().setUrgent();
        Task<DataItem> putDataTask = mDataClient.putDataItem(putDataReq);

        putDataTask.addOnSuccessListener(new OnSuccessListener<DataItem>() {
            @Override
            public void onSuccess(DataItem dataItem) {
                //Toast.makeText(getApplicationContext(), "sending :)" +putDataMapReq, Toast.LENGTH_LONG).show();
            }
        });
    }
}