package razgriz.self.appexercise1202.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pass implements Serializable {

    @SerializedName("type")
    private final String type;

    @SerializedName("duration")
    private final int duration;

    @SerializedName("price")
    private final int price;

    @SerializedName("price-unit")
    private final String priceUnit;

    @SerializedName("isActivated")
    private boolean isActivated;

    public Pass(String type, int duration, int price, String priceUnit) {
        this.type = type;
        this.duration = duration;
        this.price = price;
        this.priceUnit = priceUnit;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public boolean isActivated() {
        return isActivated;
    }
}
