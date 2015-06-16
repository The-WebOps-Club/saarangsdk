package org.saarang.saarangsdk.Network;

import java.io.IOException;

/**
 * Created by Ahammad on 16/06/15.
 */
public class Connectivity {

    public static boolean isConnected() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
