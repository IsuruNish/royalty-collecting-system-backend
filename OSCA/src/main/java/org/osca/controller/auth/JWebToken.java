package org.osca.controller.auth;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JWebToken {

    private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private JSONObject payload = new JSONObject();
    private String signature;
    private String encodedHeader;


    private JWebToken() {
        encodedHeader = encode(new JSONObject(JWT_HEADER));
    }

//    public JWebToken(String fname, String lname, String email, int userType){
//        this();
//
//        Long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
//
//        payload.put("first name", fname);
//        payload.put("last name", lname);
//        payload.put("email", email);
//        payload.put("user type", userType);
//        payload.put("exp", now + 3600);
//        payload.put("iat", now );
//        payload.put("jti", UUID.randomUUID().toString());
//        signature = hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY);
//    }

    public JWebToken(int uid, int userType){
        this();

        Long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        payload.put("user ID", uid);
        payload.put("user type", userType);
        payload.put("iat", now );
        payload.put("exp", now + 3600);
        payload.put("jti", UUID.randomUUID().toString());
        signature = hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY);
    }


    public JWebToken(String token) throws NoSuchAlgorithmException {
        this();
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Token format");
        }
        if (encodedHeader.equals(parts[0])) {
            encodedHeader = parts[0];
        } else {
            throw new NoSuchAlgorithmException("JWT Header is Incorrect: " + parts[0]);
        }

        payload = new JSONObject(decode(parts[1]));
        if (payload.length() == 0) {
            throw new JSONException("Payload is Empty: ");
        }
        if (!payload.has("exp")) {
            throw new JSONException("Payload doesn't contain expiry " + payload);
        }
        signature = parts[2];
    }

    public int getUserID(String token){
        String[] parts = token.split("\\.");
//        System.out.println(decode(parts[1]));

        String tempPayload = decode(parts[1]);
        parts = tempPayload.split("\\,");

        parts = parts[0].split("\\:");
        return Integer.parseInt(parts[1]);
    }


    public int getUserType(String token){
        String[] parts = token.split("\\.");

        String tempPayload = decode(parts[1]);
        parts = tempPayload.split("\\,");

        parts = parts[1].split("\\:");
        return Integer.parseInt(parts[1]);
    }

//    public String getFirstName(String token){
//        String[] parts = token.split("\\.");
//
//        String tempPayload = decode(parts[1]);
//        parts = tempPayload.split("\\,");
//        parts = parts[1].split("\\:");
//        return (parts[1].substring(1,parts[1].length()-1));
//    }
//
//    public String getLastName(String token){
//        String[] parts = token.split("\\.");
//
//        String tempPayload = decode(parts[1]);
//        parts = tempPayload.split("\\,");
//        parts = parts[0].split("\\:");
//        return (parts[1].substring(1,parts[1].length()-1));
//    }
//
//    public String getEmail(String token){
//        String[] parts = token.split("\\.");
//
//        String tempPayload = decode(parts[1]);
//        parts = tempPayload.split("\\,");
////        System.out.println(Arrays.toString(parts));
//        parts = parts[5].split("\\:");
//        return (parts[1].substring(1,parts[1].length()-1));
//    }

    @Override
    public String toString() {
        return encodedHeader + "." + encode(payload) + "." + signature;
    }

    public boolean isValid() {
        return payload.getLong("exp") > (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))//token not expired
                && signature.equals(hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY)); //signature matched
    }

//    public boolean isValid() {
//        return signature.equals(hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY)); //signature matched
//    }

    private static String encode(JSONObject obj) {
        return encode(obj.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }


    private String hmacSha256(String data, String secret) {
        try {

            //MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);//digest.digest(secret.getBytes(StandardCharsets.UTF_8));

            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(JWebToken.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }



    public static void main(String[] args) throws NoSuchAlgorithmException {
        JWebToken k = new JWebToken(1000,1);

        System.out.println(k.getUserType(k.toString()));
        System.out.println(k.getUserID(k.toString()));
//        System.out.println(new JWebToken("isuru","nish","123@abc@g.com",1).toString());
//        System.out.println(new JWebToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsYXN0IG5hbWUiOiJuaXNoIiwiZmlyc3QgbmFtZSI6ImlzdXJ1IiwidXNlciB0eXBlIjoxLCJleHAiOjE2MzI1ODY2NzcsImlhdCI6MTYzMjU4NjY3NSwiZW1haWwiOiIxMjNAYWJjQGcuY29tIiwianRpIjoiMmI5NzFiNzQtY2VjZC00NjUwLWEyMDctNjYwZTNmMmNlYTlhIn0.PJU-gBnu_ImVAcOA_wm7sFJ6I-gwVB2ik6OdKV2Azqg").isBothValid());
    }

}