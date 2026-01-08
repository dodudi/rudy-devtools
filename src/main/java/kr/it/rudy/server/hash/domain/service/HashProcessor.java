package kr.it.rudy.server.hash.domain.service;

import kr.it.rudy.server.hash.domain.model.HashAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashProcessor {

    public String hash(String text, HashAlgorithm algorithm) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("text must not be empty or null");
        }

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm.getAlgorithm());
            byte[] hashBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unsupported algorithm: " + algorithm.getAlgorithm(), e);
        }
    }

    public boolean verify(String text, HashAlgorithm algorithm, String expectedHash) {
        if (expectedHash == null || expectedHash.isEmpty()) {
            throw new IllegalArgumentException("expectedHash must not be empty or null");
        }

        String actualHash = hash(text, algorithm);
        return actualHash.equalsIgnoreCase(expectedHash);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
