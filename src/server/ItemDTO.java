package server;

import java.io.Serializable;

public class ItemDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private int itemID;
    private String itemName;
    private String itemDescription;
    private double itemInitialPrice;
    private int timeToFinish;

    public ItemDTO() {
    }

    public ItemDTO(int itemID, String itemName, String itemDescription, double itemInitialPrice, int timeToFinish) {
        super();
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemInitialPrice = itemInitialPrice;
        this.timeToFinish = timeToFinish;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemInitialPrice() {
        return itemInitialPrice;
    }

    public void setItemInitialPrice(double itemInitialPrice) {
        this.itemInitialPrice = itemInitialPrice;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(int timeToFinish) {
        this.timeToFinish = timeToFinish;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemInitialPrice=" + itemInitialPrice +
                ", timeToFinish=" + timeToFinish +
                '}';
    }
}
