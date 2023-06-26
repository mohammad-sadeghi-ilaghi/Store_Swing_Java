package org.example.Models.Entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class UserEntity {
    private String userName;
    private String numberPhone;
    private String password;
    private String hashPassword;
    private String email;
    private Date created;
    private CardEntity cardBank;


    public UserEntity(String userName, String numberPhone, String password, String email, CardEntity cardBank) throws NoSuchAlgorithmException {
        this.cardBank = cardBank;
        this.userName = userName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.hashPassword = hashPassword(password);
        this.email = email;
        this.created = new Date();
    }



    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        String hashedPassword = hexString.toString();
        return hashedPassword;
    }
    public CardEntity getCardBank() {
        return cardBank;
    }

    public void setCardBank(CardEntity cardBank) {
        this.cardBank = cardBank;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = password;
        setHashPassword(hashPassword(password));
    }

    public String getHashPassword() {
        return hashPassword;
    }

    private String setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", password='" + password + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                '}';
    }
}
