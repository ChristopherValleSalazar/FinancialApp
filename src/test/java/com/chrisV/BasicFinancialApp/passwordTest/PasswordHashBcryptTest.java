package com.chrisV.BasicFinancialApp.passwordTest;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordHashBcryptTest {

    private String password = "MySecurePassword123!";

    private String hashPassword(String password) {
        // using strength 12 for bcrypt
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    private String hashPasswordSpring(String password) {
        return new BCryptPasswordEncoder(12).encode(password);
    }

    private boolean verifyPasswordHash(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    @Test
    public void testBcryptPassword() {
        String hashedPassword = hashPassword(password);
        boolean isMath = verifyPasswordHash(password, hashedPassword);
        assertTrue(isMath);
    }



}
