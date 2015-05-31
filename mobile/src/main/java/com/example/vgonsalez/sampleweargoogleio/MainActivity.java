package com.example.vgonsalez.sampleweargoogleio;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText mEditTitle;
    private EditText mEditContext;
    private EditText mEditData;
    private Button mBtnNotification;

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

        mEditTitle = (EditText) findViewById(R.id.title_notification);
        mEditContext = (EditText) findViewById(R.id.context_notification);
        mEditData = (EditText) findViewById(R.id.data_notification);
    }

    private void sendNotification() {

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(mEditData.getText().toString()));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent = PendingIntent.getActivity(this, 0, mapIntent, 0);

        Intent videoIntent = new Intent(Intent.ACTION_VIEW);
        Uri videoUri = Uri.parse("https://www.youtube.com/results?search_query="+mEditData.getText().toString());
        videoIntent.setData(videoUri);
        PendingIntent videoPendingIntent = PendingIntent.getActivity(this, 0, videoIntent, 0);

        Action mapAction = new Action.Builder(R.drawable.map,
                getString(R.string.map_action), mapPendingIntent).build();
        Action videoAction = new Action.Builder(R.drawable.youtube,
                getString(R.string.video_action), videoPendingIntent).build();

        Bitmap bitmap = BitmapFactory.decodeResource(mEditContext.getResources(),R.drawable.android_background);
        int notificationId = 1;
        Builder notificationBuilder = new Builder(MainActivity.this)
                .setSmallIcon(R.drawable.compass)
                .setLargeIcon(bitmap)
                .setContentTitle(mEditTitle.getText().toString())
                .setContentText(mEditContext.getText().toString())
                .extend(new WearableExtender()
                        .addAction(mapAction)
                        .addAction(videoAction));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
