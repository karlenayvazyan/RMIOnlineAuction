package server;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userID;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userAddress;
    private boolean makeBid;
    private double budget;
    private double bidAmount;

    public UserDTO() {

    }

    public UserDTO(int userID, String userName, String userPassword, String userEmail, String userAddress,
                   boolean makeBid, double budget, double bidAmount) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.makeBid = makeBid;
        this.budget = budget;
        this.bidAmount = bidAmount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean makeBid() {
        return makeBid;
    }

    public void setMakeBid(boolean makeBid) {
        this.makeBid = makeBid;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", makeBid=" + makeBid +
                ", budget=" + budget +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
