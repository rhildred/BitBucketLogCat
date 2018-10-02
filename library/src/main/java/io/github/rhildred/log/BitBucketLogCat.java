package io.github.rhildred.log;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.ContentValues.TAG;

public class BitBucketLogCat {
    static public boolean eSynchronous(final String sBbAccount, final String sComponent, final String sTitle, final String sUser){
        HttpURLConnection connection = null;
        try {
            //Your code goes here
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line + "\n\n");
            }
            URL url;
            String targetURL = "https://bitbucket.org/api/1.0/repositories/" + sBbAccount + "/" + sComponent +"/issues/";
            String urlParameters =
                    "title=" + URLEncoder.encode(sTitle, "UTF-8") +
                            "&user=" + URLEncoder.encode(sUser, "UTF-8") +
                            "&content=" + URLEncoder.encode(log.toString(), "UTF-8");
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // ysaasissues/Secret55 (this user will need to be given read access to whichever repository is to receive logcat info)
            connection.setRequestProperty("Authorization", "Basic eXNhYXNpc3N1ZXM6U2VjcmV0NTU=");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String sLine;
            final StringBuffer response = new StringBuffer();
            while ((sLine = rd.readLine()) != null) {
                response.append(sLine);
                response.append('\r');
            }
            rd.close();
            Log.d(TAG, "Sent LogCat\n" + response.toString());
            return true;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

    }
    static public void e(final String sBbAccount, final String sComponent, final String sTitle, final String sUser){
        try {

            //Send request not in UI thread
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    BitBucketLogCat.eSynchronous(sBbAccount, sComponent, sTitle, sUser);
                }
            });
            thread.start();


        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }

    }

}
