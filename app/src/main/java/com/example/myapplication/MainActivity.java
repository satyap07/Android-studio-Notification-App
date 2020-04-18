package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager=NotificationManagerCompat.from(this);
        editTextMessage=findViewById(R.id.edit_text_message);
        editTextTitle=findViewById(R.id.edit_text_title);
    }
    public void sendOnChannel1(View view){
        String title=editTextTitle.getText().toString();
        String Message=editTextMessage.getText().toString();

        Intent activityIntent= new Intent(this,MainActivity.class);
        PendingIntent contentIntent= PendingIntent.getActivity(this,0,activityIntent,0);

        Intent broadcastIntent= new Intent(this,NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage",Message);
        PendingIntent actionIntent=PendingIntent.getBroadcast(this,
                0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap picture=BitmapFactory.decodeResource(getResources(),R.drawable.photo);

        Notification notification=new NotificationCompat.Builder(this,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setLargeIcon(picture)
                .setContentTitle(title)
                .setContentText(Message)
//                .setStyle(new NotificationCompat.BigPictureStyle()
//                .bigPicture(picture)
//                .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.drawable.ic_launcher_background,"Toast",actionIntent)
                .build();

        notificationManager.notify(1,notification);
    }
    public void sendOnChannel2(View view){
        String title=editTextTitle.getText().toString();
        String Message=editTextMessage.getText().toString();
        Notification notification=new NotificationCompat.Builder(this,App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_ltwo)
                .setContentTitle(title)
                .setContentText(Message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2,notification);

    }
}
