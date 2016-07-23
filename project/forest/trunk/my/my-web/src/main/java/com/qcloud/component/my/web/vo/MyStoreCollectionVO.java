package com.qcloud.component.my.web.vo;

import java.util.List;
import java.util.Map;

public class MyStoreCollectionVO {

    private long                      id;

    private String                    address;

    private double                    distance;

    private long                      storeId;

    private List<Map<String, Object>> images;

    private double                    latitude;  // 经度

    private double                    longitude; // 纬度

    private String                    name;

    private String                    phone;

    private String                    shopHour;

    private List<String>              activities;

    public MyStoreCollectionVO() {

        super();
    }

    public MyStoreCollectionVO(long id, String address, double distance, long storeId, List<Map<String, Object>> images, double latitude, double longitude, String name, String phone, String shopHour, List<String> activities) {

        super();
        this.id = id;
        this.address = address;
        this.distance = distance;
        this.storeId = storeId;
        this.images = images;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.shopHour = shopHour;
        this.activities = activities;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public double getDistance() {

        return distance;
    }

    public void setDistance(double distance) {

        this.distance = distance;
    }

    public long getStoreId() {

        return storeId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public List<Map<String, Object>> getImages() {

        return images;
    }

    public void setImages(List<Map<String, Object>> images) {

        this.images = images;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getShopHour() {

        return shopHour;
    }

    public void setShopHour(String shopHour) {

        this.shopHour = shopHour;
    }

    public List<String> getActivities() {

        return activities;
    }

    public void setActivities(List<String> activities) {

        this.activities = activities;
    }
}
