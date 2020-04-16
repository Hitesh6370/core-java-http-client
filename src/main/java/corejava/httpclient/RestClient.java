package corejava.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClient {

    public void getRequest()
    {
        try
        {
            URL url = new URL("http://localhost:8083/api/test");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            String readLine = null;
            int status = con.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                // print result
                System.out.println("JSON String Result :" + response.toString());
                System.out.println("GET Method Executed successfully");
            }
            else if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM) {
                String location = con.getHeaderField("Location");
                URL newUrl = new URL(location);
                con = (HttpURLConnection) newUrl.openConnection();
            }
            else {
                System.out.println("GET NOT WORKED");
            }
            con.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void postRequest()
    {
        try
        {
            URL url = new URL("http://localhost:8083/api/produce/user");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setInstanceFollowRedirects(false);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            String input = "{\n" +
                    "    \"supplierId\": \"110\",\n" +
                    "    \"supplierName\":\"Vickky\"\n" +
                    "}";
            OutputStream os = con.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            os.close();
            String readLine = null;
            int status = con.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                }
                in .close();
                // print result
                System.out.println("JSON String Result :" + response.toString());
                System.out.println("POST Method Executed successfully");
            }
            else if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM) {
                String location = con.getHeaderField("Location");
                URL newUrl = new URL(location);
                con = (HttpURLConnection) newUrl.openConnection();
            }
            else {
                System.out.println("GET NOT WORKED");
            }
            con.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
