package com.example.mobapps_kennylib;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class WebService extends AsyncTask<String, Void, String> {
    private final String serviceuri = "http://10.0.2.2/mini-project/service/"; //Sesuaikan

    private Exception exception;

    protected String doInBackground(String... uri) {
        try {
            URL url = new URL(serviceuri+uri[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod(uri[1]);
            con.setRequestProperty("Accept", "application/json");
            if(uri[1].equals("GET")){

            }
            else if (uri[1].equals("POST")){
                con.setRequestProperty("content-type", "application/json");
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(uri[2]);
                out.flush();
                out.close();
            }

            //int status = con.getResponseCode();
            System.out.println(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            //System.out.println("Response status: " + status);
            System.out.println(content.toString());
            return content.toString();
        } catch (Exception e) {
            this.exception = e;

            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}