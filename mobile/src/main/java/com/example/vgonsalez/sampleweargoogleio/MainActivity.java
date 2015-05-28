package com.example.vgonsalez.sampleweargoogleio;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.*;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends Activity {

    private Button mBtnNotification = null;
    private EditText mEditMaps;
    private EditText mEditTitle;
    private EditText mEditMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditMaps = (EditText) findViewById(R.id.edit_maps);
        mEditTitle = (EditText) findViewById(R.id.edit_title);
        mEditMessage = (EditText) findViewById(R.id.edit_message);
        mBtnNotification = (Button) findViewById(R.id.btn_send_notification);
        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }

    private void sendNotification() {

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(mEditMaps.getText().toString()));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent = PendingIntent.getActivity(this, 0, mapIntent, 0);

        Uri webpage = Uri.parse("http://www.youtube.com/" + mEditMaps.getText().toString());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        PendingIntent videoPendingIntent = PendingIntent.getActivity(this, 0, webIntent, 0);

        NotificationCompat.Action mapApp = new NotificationCompat.Action(R.drawable.pin,
                getResources().getString(R.string.map_app), mapPendingIntent);

        NotificationCompat.Action videoApp = new NotificationCompat.Action(R.drawable.youtube,
                getResources().getString(R.string.video_app), videoPendingIntent);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.androidbackground);

        Random random = new Random();
        int notificationId = random.nextInt(200);
        WearableExtender wearableExtender = new WearableExtender();
        Builder notificationBuilder = new Builder(MainActivity.this)
                .setSmallIcon(R.drawable.compassicon)
                .setLargeIcon(bitmap)
                .setContentTitle(mEditTitle.getText().toString())
                .setContentText(mEditMessage.getText().toString())
                .extend(wearableExtender.addAction(mapApp)
                        .addAction(videoApp));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
