package com.fruitsalesplatform.entity;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 20:07
 */
public class Accessory {
    private String accessoryId;
    private String fruitId;
    private String name;
    private String createTime;
    private Double price;

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getFruitId() {
        return fruitId;
    }

    public void setFruitId(String fruitId) {
        this.fruitId = fruitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "accessoryId='" + accessoryId + '\'' +
                ", fruitId='" + fruitId + '\'' +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", price=" + price +
                '}';
    }
}
