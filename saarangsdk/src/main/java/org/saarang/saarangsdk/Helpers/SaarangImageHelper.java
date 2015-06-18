package org.saarang.saarangsdk.Helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Ahammad on 02/06/15.
 */
public class SaarangImageHelper {

    public static Bitmap compressSaveImage(Context context, Bitmap bitmap, String fileName){

        String root = context.getCacheDir().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        String fname = fileName +".jpg";
        File file = new File (myDir, fname);
        Log.d("Saved file path", file.getAbsolutePath());
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, out);
            out.flush();
            out.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
