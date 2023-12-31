package org.example.Models.Entities;

import org.example.Rules.RulesUser;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public class UserEntity implements Serializable {
    private RulesUser user;
    private String userName;
    private String numberPhone;
    private String password;
    private String hashPassword;
    private String email;
    private Date lastModified;
    private CardEntity cardBank;


    public UserEntity(String userName, String numberPhone, String password, String email, CardEntity cardBank, RulesUser user) throws NoSuchAlgorithmException {
        this.user = user;
        this.cardBank = cardBank;
        this.userName = userName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.hashPassword = hashPassword(password);
        this.email = email;
        this.lastModified = new Date();
    }



    public UserEntity(String userName, String password) {
        this.userName = userName;
        try {
            setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
        return lastModified;
    }

    public RulesUser getUserRule() {
        return user;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", password='" + password + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", email='" + email + '\'' +
                ", created=" + lastModified +
                '}';
    }
    public String toStringFile() {
        return userName + '\t' +
                numberPhone + '\t' +
                password + '\t' +
                hashPassword + '\t' +
                email + '\t' +
                lastModified + + '\t';
    }
    public static String[] getFeilds() {
        Field[] fields = FieldsEntity.getAllFields(UserEntity.class);
        return Arrays.stream(fields)
                .map(Field::getName)
                .map(item -> item.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2"))
                .toArray(String[]::new);
    }
}
