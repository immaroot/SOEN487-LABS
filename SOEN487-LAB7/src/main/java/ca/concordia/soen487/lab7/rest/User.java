package ca.concordia.soen487.lab7.rest;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {
    private String username;
    private String password;
    private String token;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.token = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void generateToken() {
        String charsForToken = "ABCDEFGHJKMNPQRSTUVWXYZ1234567890";
        this.token = IntStream.range(0, 10)
                .map(i -> new SecureRandom().nextInt(charsForToken.length()))
                .mapToObj(randomInt -> String.valueOf(charsForToken.charAt(randomInt)))
                .collect(Collectors.joining());
    }

    public void destroyToken() {
        this.token = "";
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
