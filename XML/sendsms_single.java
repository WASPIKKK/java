//sms club fix
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.*;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "https://gate.smsclub.mobi/xml/";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "text/xml");
        con.setRequestProperty("Accept", "texl/xml");
        con.setRequestMethod("POST");

        String login = "login";  //string User ID (phone number)
        String password = "password";  //string Password
        String alphaName = "Shop Zakaz";    //string, sender id (alpha-name) (as long as your alpha-name is not spelled out, it is necessary to use it)
        String abonent = "380635661329,380636599594";
        String text = "Sending SMS from SMS CLUB via java";


        String xml = "<?xml version='1.0' encoding='utf-8'?><request_sendsms><username><![CDATA["+login+"]]></username><password><![CDATA["+password+"]]></password><from><![CDATA["+alphaName+"]]></from><to><![CDATA["+abonent+"]]></to><text><![CDATA["+text+"]]></text></request_sendsms>";
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        wr.write(xml.toString());
        wr.flush();

        //display what returns the POST request
        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            System.out.println("" + sb.toString());
        } else {
            System.out.println(con.getResponseMessage());
        }
    }
}
