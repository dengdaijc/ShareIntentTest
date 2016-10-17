package com.by.shareintenttest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.by.shareintenttest.utils.PackageName;
import com.by.shareintenttest.utils.ShareIntentUtils;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_share_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAll();
            }
        });

        findViewById(R.id.btn_share_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareSingle();
            }
        });
        findViewById(R.id.btn_share_all_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAllImage();
            }
        });
        findViewById(R.id.btn_share_all_imgs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAllImages();
            }
        });
        findViewById(R.id.btn_share_single_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareSingleImg();
            }
        });
        findViewById(R.id.btn_share_single_imgs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareSingleImgs();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner_platform);
        String[] platforms = {"微信", "QQ", "腾讯微博", "新浪微博"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, platforms);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void shareSingleImgs() {
        String packageName = getSelectPackageName();
        if (!ShareIntentUtils.checkInstallation(packageName, this)) {
            Toast.makeText(this, "no install app", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Uri> list = new ArrayList<>();
        list.add(Uri.fromFile(new File("/sdcard/test.png")));
        list.add(Uri.fromFile(new File("/sdcard/test.png")));
        list.add(Uri.fromFile(new File("/sdcard/test.png")));
        ShareIntentUtils.shareImgsToPlatform(this, "test imgs", list, packageName);
    }

    private void shareSingleImg() {
        String packageName = getSelectPackageName();
        if (!ShareIntentUtils.checkInstallation(packageName, this)) {
            Toast.makeText(this, "no install app", Toast.LENGTH_SHORT).show();
            return;
        }
        ShareIntentUtils.shareImgToPlatform(this, "test text", Uri.fromFile(new File("/sdcard/test.png")) ,packageName);

    }

    private void shareAllImages() {
        ArrayList<Uri> list = new ArrayList<>();
        list.add(Uri.fromFile(new File("/sdcard/test.png")));
        list.add(Uri.fromFile(new File("/sdcard/test.png")));
        list.add(Uri.fromFile(new File("/sdcard/test.png")));
        ShareIntentUtils.shareImgsToAll(this, "test imgs", list);
    }

    private void shareAllImage() {
        ShareIntentUtils.shareImgToAll(this, "test img", Uri.fromFile(new File("/sdcard/test.png")));
    }

    private String getSelectPackageName() {
        String packageName;
        switch (type) {
            case 0: {
                packageName = PackageName.WECHAT;
            }
            break;
            case 1: {
                packageName = PackageName.QQ;
            }
            break;
            case 2: {
                packageName = PackageName.QQ_WEIBO;
            }
            break;
            case 3: {
                packageName = PackageName.SINA_WEIBO;
            }
            break;
            default: {
                packageName = "";
            }
            break;
        }
        return packageName;
    }


    private void shareSingle() {
        String packageName = getSelectPackageName();

        if (!ShareIntentUtils.checkInstallation(packageName, this)) {
            Toast.makeText(this, "no install app", Toast.LENGTH_SHORT).show();
            return;
        }

        ShareIntentUtils.shareTextToPlatform(this, "test text", packageName);

    }

    private void shareAll() {
        ShareIntentUtils.shareTextToAll(this, "test text");
    }
}
