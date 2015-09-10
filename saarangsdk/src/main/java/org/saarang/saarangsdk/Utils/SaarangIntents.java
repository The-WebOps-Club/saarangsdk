package org.saarang.saarangsdk.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Ahammad on 12/07/15.
 */
public class SaarangIntents {

    public static void call(Context context, String number){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    public static void email(Context context, String email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Saarang 2016");
        intent.putExtra(Intent.EXTRA_TEXT, "- Sent from Saarang ERP mobile app");
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, "Send Email"));
    }

}
