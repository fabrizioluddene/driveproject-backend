package it.diriveprojectbe.commons.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class DriveProjectCommonsUtil {
    public static  String ecodeString(String stringToEncode){
        String saltString = "asghfdlkjghljhszòjdfghasopòuirhpauhdfjhbaslòkjdfagpeiuwapòuifasdui";

        SecretKeyFactory factory = null;
        KeySpec spec = new PBEKeySpec(stringToEncode.toCharArray(), saltString.getBytes(), 65536, 128);
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println( Base64.getEncoder().encodeToString(hash));
        return Base64.getEncoder().encodeToString(hash);
    }

}
