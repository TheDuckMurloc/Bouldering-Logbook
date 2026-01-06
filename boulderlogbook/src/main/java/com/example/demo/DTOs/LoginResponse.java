package main.java.com.example.demo.DTOs;

public class LoginResponse {
    private String token;
    private Long userId;

    public LoginResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() { return token; }
    public Long getUserId() { return userId; }
}
