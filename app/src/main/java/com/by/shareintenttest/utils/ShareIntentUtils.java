package com.by.shareintenttest.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by baiyu on 16/10/17.
 */

public class ShareIntentUtils {

    /**
     * 判断是否安装某个应用
     * @param packageName 应用的包名
     * @param context 上下文关系
     * @return true安装 false未安装
     */
    public static boolean checkInstallation(String packageName, Context context){

        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }

    /**
     * 分享文本到全平台
     * @param context 上下文关系
     * @param text 要分享的文本
     */
    public static void shareTextToAll(Context context, String text) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }

    /**
     * 分享单张图片到全平台
     * @param context 上下文关系
     * @param text 文本内容，并非所有平台在分享图片时候都支持此参数
     * @param img 图片uri地址
     */
    public static void shareImgToAll(Context context, String text, Uri img) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_STREAM, img);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }

    /**
     * 分享多张图片到全平台
     * @param context 上下文关系
     * @param text 文本内容
     * @param images 图片Uri地址的集合
     */
    public static void shareImgsToAll(Context context, String text, ArrayList<Uri> images) {
        Intent intent=new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, images);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }

    /**
     * 分享文本到全平台
     * @param context 上下文关系
     * @param text 要分享的文本
     * @param packageName 对应平台的包名
     */
    public static void shareTextToPlatform(Context context, String text, String packageName) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setPackage(packageName);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }

    /**
     * 分享单张图片到全平台
     * @param context 上下文关系
     * @param text 文本内容，并非所有平台在分享图片时候都支持此参数
     * @param img 图片uri地址
     * @param packageName 对应平台的包名
     */
    public static void shareImgToPlatform(Context context, String text, Uri img, String packageName) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_STREAM, img);
        intent.setPackage(packageName);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }

    /**
     * 分享多张图片到全平台
     * @param context 上下文关系
     * @param text 文本内容
     * @param images 图片Uri地址的集合
     * @param packageName 对应平台的包名
     */
    public static void shareImgsToPlatform(Context context, String text, ArrayList<Uri> images, String packageName) {
        Intent intent=new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, images);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setPackage(packageName);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }

    /**
     * 分享图文到微信朋友圈
     * @param context 上下文关系
     * @param text 文本内容
     * @param img 图片uri地址
     */
    public static void shareToWechatLine(Context context, String text, Uri img) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_STREAM, img);
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        context.startActivity(Intent.createChooser(intent, "请选择"));
    }





}
