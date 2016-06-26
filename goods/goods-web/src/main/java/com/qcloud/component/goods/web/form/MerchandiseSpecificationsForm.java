package com.qcloud.component.goods.web.form;

import java.util.HashMap;
import java.util.List;

public class MerchandiseSpecificationsForm {
    private List<MsForm> list;
    
    private HashMap<Long, Integer> specifications;

    private HashMap<Long, String> images;
    
    private List<ImageForm> imagesForm;

    public MerchandiseSpecificationsForm() {
    }

    public HashMap<Long, Integer> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(HashMap<Long, Integer> specifications) {
        this.specifications = specifications;
    }

    public HashMap<Long, String> getImages() {
        return images;
    }

    public void setImages(HashMap<Long, String> images) {
        this.images = images;
    }

    
    public List<MsForm> getList() {
    
        return list;
    }

    
    public void setList(List<MsForm> list) {
    
        this.list = list;
    }

    
    public List<ImageForm> getImagesForm() {
    
        return imagesForm;
    }

    
    public void setImagesForm(List<ImageForm> imagesForm) {
    
        this.imagesForm = imagesForm;
    }
}
