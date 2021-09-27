package org.osca.controller.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class GenerateToken {



    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    public static String createJWT(int userType, String fname,String lname, String email) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Instant now = Instant.now();

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        String builder = Jwts.builder()
                .setId(String.valueOf(userType))
                .setSubject(fname)
                .setIssuer(lname)
                .claim("email",email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(60, ChronoUnit.MINUTES)))
                .signWith(signatureAlgorithm, signingKey)
                .compact();

        return builder;


    }

    public static String decodeJWT(String token) {
//        System.out.println(jwt);

        //This line will throw an exception if it is not a signed JWS (as expected)
        String claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getSignature();
//

        return claims;
    }

    public static void main(String[] args) {

        System.out.println(createJWT(1,"isuru","nish","123@abc@g.com"));
        System.out.println(decodeJWT(createJWT(1,"isuru","nish","123@abc@g.com")));

    }


}



