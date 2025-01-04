package org.gly.tiny_url.service;

import org.gly.tiny_url.constants.Constants;
import org.springframework.stereotype.Service;

import java.lang.constant.Constable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class MD5Hash implements HashService{


    @Override
    public String getHash(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return getCustomHashValue(hashtext);
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String getCustomHashValue(String hash){
        String s = hash;
        byte[] bytes = s.getBytes();
     //   StringBuilder binary = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (byte b : bytes)
        {
            StringBuilder binary = new StringBuilder();
            int val = b;
            for (int i = 0; i < 6; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
           String binaryString = binary.toString();
            Integer idx = Integer.parseInt(binaryString,2);
            res.append(Constants.DICTIONARY[idx]);
            if(res.length()==Constants.SHORT_URL_LENGTH)
                break;
        }
        return res.toString();
    }
}
