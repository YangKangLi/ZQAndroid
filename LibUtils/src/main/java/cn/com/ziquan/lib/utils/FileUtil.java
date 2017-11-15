package cn.com.ziquan.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/15.
 */

public class FileUtil {

    // 获取文件夹
    public static String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 私有构造方法
     */
    private FileUtil() {
        throw new UnsupportedOperationException("u can't instantiate here!");
    }

    /**
     * 复制Assets中的文件到指定的地方
     *
     * @param context
     * @param sourceFileName
     * @param targetFile
     * @return
     */
    public static boolean copyAssetsFile(Context context, String sourceFileName, File targetFile) {
        boolean retResult = false;
        try {
            AssetManager assetManager = context.getAssets();
            BufferedInputStream inBuff = new BufferedInputStream(assetManager.open(sourceFileName));
            BufferedOutputStream outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            int len;
            byte[] b = new byte[1024];
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
            inBuff.close();
            outBuff.close();
            retResult = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retResult;
    }

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @return
     */
    public static boolean copyFile(File sourceFile, File targetFile) {
        boolean retResult = false;
        try {
            BufferedInputStream inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            int len;
            byte[] b = new byte[1024];
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
            inBuff.close();
            outBuff.close();
            retResult = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retResult;
    }

    /**
     * 打开文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean openFile(Activity context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(Uri.fromFile(file), type);
        //跳转
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    private static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        if (MIME_MapTable.get(end) != null) {
            type = MIME_MapTable.get(end);
        }
        return type;
    }

    private final static HashMap<String, String> MIME_MapTable = new HashMap<String, String>() {{
        put(".apk", "application/vnd.android.package-archive");
        put(".txt", "text/plain");
        put(".bmp", "image/*");
        put(".jpg", "image/*");
        put(".jpeg", "image/*");
        put(".png", "image/*");
        put(".gif", "image/*");
        put(".avi", "video/x-msvideo");
        put(".mp3", "audio/x-mpeg");
        put(".wav", "audio/x-wav");
        put(".wma", "audio/x-ms-wma");
        put(".mp4", "video/mp4");
        put(".3gp", "video/3gp");
        put(".pdf", "application/pdf");
        put(".doc", "application/msword");
        put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        put(".xls", "application/vnd.ms-excel");
        put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        put(".ppt", "application/vnd.ms-powerpoint");
        put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        put(".pps", "application/vnd.ms-powerpoint");
        put(".zip", "application/x-zip-compressed");
        put("", "*/*");
    }};

    /**
     * 保存图片
     *
     * @param mBitmap
     * @param path
     * @param imgName
     * @return
     */
    public static boolean saveBitmap(Bitmap mBitmap, String path, String imgName) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return false;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + imgName;
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 在SD卡中创建文件夹
     *
     * @param dirName
     * @return
     * @throws IOException
     */
    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDCARD_PATH + dirName);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(SDCARD_PATH + fileName);
        file.isFile();
        return file.exists();
    }

    /**
     * 删除文件
     *
     * @param path
     * @param fileName
     */
    public static void delFile(String path, String fileName) {
        File file = new File(path + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    /**
     * 删除文件夹和文件夹里面的文件
     */
    public static void deleteDir() {
        File dir = new File(SDCARD_PATH);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }
}
