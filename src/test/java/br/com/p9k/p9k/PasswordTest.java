package br.com.p9k.p9k;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String encodedPassword = "$2y$10$1z2.yyb.uKQQCaR3GooRRe2mLEp33mDEy40lMlQIcCrjh4VUAFe6m";

        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Matches? " + matches); // Deve imprimir: Matches? true
    }
}
