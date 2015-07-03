package org.saarang.saarangsdk.Helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Ahammad on 02/06/15.
 */
public class SaarangImageHelper {

    public static String compressSaveImage(Context context, Bitmap bitmap, String fileName){

        String fname;
        String root = Environment.getExternalStorageDirectory().toString();
//        String root = context.getCacheDir().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        fname = fileName + System.currentTimeMillis() + ".jpg";
        File file = new File (myDir, fname);
        Log.d("Saved file path", file.getAbsolutePath());
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, out);
            out.flush();
            out.close();

            Log.d("ImageHelper fname", fname);
            Log.d("ImageHelper path", file.getAbsolutePath());
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fname;
    }

}
