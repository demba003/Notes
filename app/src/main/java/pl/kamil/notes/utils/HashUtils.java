package pl.kamil.notes.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    public static String getSHA1Hash(String input) throws NoSuchAlgorithmException {
        return new String(MessageDigest
                .getInstance("SHA-1")
                .digest(input.getBytes()));
    }
}
