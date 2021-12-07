package org.osca.controller.login;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

public class SMSgateway {

    public int sendSMSPin() throws Exception {
        SecureRandom rand = new SecureRandom();
        String pin=""+rand.nextInt(1000000);

        URL textit = new URL("http://textit.biz/sendmsg/index.php?id=94766699117&pw=8265&to=94766699117&text="+pin);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(textit.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

        return Integer.parseInt(pin);
    }

    public void storePinInDatabase(int pin, int uid){

    }
}