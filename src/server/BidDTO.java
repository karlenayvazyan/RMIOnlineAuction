package server;

import java.io.Serializable;
import java.util.Objects;

public class BidDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int bidID;
    private int userID;
    private double bidAmount;
    private int itemDtoId;

    public BidDTO() {
    }

    public BidDTO(int bidID, int userID, double bidAmount, int itemDtoId) {
        this.bidID = bidID;
        this.userID = userID;
        this.bidAmount = bidAmount;
        this.itemDtoId = itemDtoId;
    }

    public int getBidID() {
        return bidID;
    }

    public void setBidID(int bidID) {
        this.bidID = bidID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getItemDtoId() {
        return itemDtoId;
    }

    public void setItemDtoId(int itemDtoId) {
        this.itemDtoId = itemDtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidDTO bidDTO = (BidDTO) o;
        return bidID == bidDTO.bidID &&
                userID == bidDTO.userID &&
                Double.compare(bidDTO.bidAmount, bidAmount) == 0 &&
                itemDtoId == bidDTO.itemDtoId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(bidID, userID, bidAmount, itemDtoId);
    }

    @Override
    public String toString() {
        return "BidDTO{" +
                "bidID=" + bidID +
                ", userID=" + userID +
                ", bidAmount=" + bidAmount +
                ", itemDtoId=" + itemDtoId +
                '}';
    }
}
