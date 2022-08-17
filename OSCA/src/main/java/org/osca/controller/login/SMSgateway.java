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

        String textForSMS = "Your+PIN+for+OSCA+signup+is+"+pin;

        URL textit = new URL("http://textit.biz/sendmsg/index.php?id=94766699117&pw=8265&to="+number+"&text="+textForSMS);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(textit.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

        return Integer.parseInt(pin);
    }
}
