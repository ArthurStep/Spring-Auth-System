package main.artfix.passtimenote.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomCodeService {
    public String getRandom() {
        String RandomGeneratedCode = "GenerationError";
        int length = 6;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = rnd.nextInt(chars.length());
            char randomChar = chars.charAt(index);
            sb.append(randomChar);
        }
        RandomGeneratedCode = sb.toString();
        return RandomGeneratedCode;
    }
}
