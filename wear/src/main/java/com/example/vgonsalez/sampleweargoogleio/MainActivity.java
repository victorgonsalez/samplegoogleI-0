package com.example.vgonsalez.sampleweargoogleio;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button mBtnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mBtnNotification = (Button) stub.findViewById(R.id.btn_send_notification);
                mBtnNotification.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int notificationId = 1;
                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.compass)
                                .setContentTitle(getResources().getString(R.string.title_notification))
                                .setContentText(getResources().getString(R.string.context_notification));

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                        notificationManager.notify(notificationId, notificationBuilder.build());
                    }
                });
            }
        });
    }
}
