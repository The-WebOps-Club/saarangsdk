package org.saarang.saarangsdk.Network;

import android.graphics.Bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ahammad on 17/06/15.
 */
public class ImageUploader {

    public static void execute(String urlString, Bitmap bitmap){
        try {

            // Static stuff:
            String attachmentName = "bitmap";
            String attachmentFileName = "bitmap.bmp";
            String crlf = "\r\n";
            String twoHyphens = "--";
            String boundary =  "*****";

            // Setup the request:
            HttpURLConnection httpUrlConnection = null;
            URL url = new URL(urlString);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" +  boundary);

            // Start content wrapper:
            DataOutputStream request = new DataOutputStream(httpUrlConnection.getOutputStream());

            request.writeBytes(twoHyphens +  boundary +  crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" +  attachmentName + "\";filename=\"" +  attachmentFileName + "\"" +  crlf);
            request.writeBytes( crlf);
            // Convert Bitmap to ByteBuffer

            //I want to send only 8 bit black & white bitmaps
            byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
            for (int i = 0; i < bitmap.getWidth(); ++i) {
                for (int j = 0; j < bitmap.getHeight(); ++j) {
                    //we're interested only in the MSB of the first byte,
                    //since the other 3 bytes are identical for B&W images
                    pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                }
            }

            request.write(pixels);
            // End content wrapper:

            request.writeBytes( crlf);
            request.writeBytes( twoHyphens +  boundary +  twoHyphens +  crlf);
            // Flush output buffer:

            request.flush();
            request.close();
            // Get response:

            InputStream responseStream = new BufferedInputStream(httpUrlConnection.getInputStream());

            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();

            String response = stringBuilder.toString();
            //Close response stream:

            responseStream.close();
            // Close the connection:

            httpUrlConnection.disconnect();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
