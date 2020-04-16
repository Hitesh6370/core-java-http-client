package corejava;

import corejava.httpclient.RestClient;

public class Main {

    public static void main(String[] args)
    {
        RestClient client = new RestClient();
        client.getRequest();
        client.postRequest();;
    }
}
