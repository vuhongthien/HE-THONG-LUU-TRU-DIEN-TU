package vn.fis.logfile.vinasoy.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Utils {

    public static String sha256hex( String value) {
        return  DigestUtils.sha256Hex(value);
    }

    public static byte[] hash( String sha256Value) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(
                sha256Value.getBytes(StandardCharsets.UTF_8));
        return hash;
    }

    public static String byteToHex( byte[] hashValue) throws NoSuchAlgorithmException {
        return new String(Hex.encode(hashValue));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String value = "Hello";
        String sha256hex = sha256hex(value);
        System.out.println("encryptSHA256 \"Hello\": " + sha256hex);

        byte[] hash = hash(sha256hex);
        System.out.println(" byte[] of shaValue: " + hash);

        sha256hex = byteToHex(hash);
        System.out.println(" sha of hashValue: " + sha256hex );
    }

}
