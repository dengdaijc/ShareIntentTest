package com.by.shareintenttest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        TextView textView = (TextView) findViewById(R.id.textView);


        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        String type = intent.getType();
        String action = intent.getAction();

        textView.setText(text + "\n" + type + "\n" + action);

        if (type.equals("image/*")) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            if (action.equals(Intent.ACTION_SEND)) {
                Uri img = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                imageView.setImageURI(img);
            } else if (action.equals(Intent.ACTION_SEND_MULTIPLE)) {
                ArrayList<Uri> list = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                if (list.size() > 0) {
                    imageView.setImageURI(list.get(0));
                }
            }
        }



    }
}
