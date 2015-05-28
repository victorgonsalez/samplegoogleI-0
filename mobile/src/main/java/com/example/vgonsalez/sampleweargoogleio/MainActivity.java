package com.example.vgonsalez.sampleweargoogleio;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView mTxResponse = null;
    private Button mBtnNotification = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNotification = (Button) findViewById(R.id.btn_send_notification);
        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        mTxResponse = (TextView) findViewById(R.id.main_response);
    }

    private void sendNotification() {
        int notificationId = 1;
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.drawable.compass67)
                .setContentTitle(getResources().getString(R.string.title_notification))
                .setContentText(getResources().getString(R.string.context_notification)).extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
