package com.example.companyuo.tools;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by sheshihao385 on 16/1/15.
 */
public class FileTools {

    public static File newFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
        if (file.exists()) {
            Log.d("MainActivity", file.exists() + "-->");
            file.delete();
        }
        return file;
    }

}
