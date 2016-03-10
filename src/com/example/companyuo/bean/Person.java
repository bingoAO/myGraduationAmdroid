package com.example.companyuo.bean;

import java.util.List;

/**
 * Created by sheshihao385 on 16/1/12.
 * 闇�瑕佸府鍔╃殑浜�
 */
public class Person {
    private String nike_name;
    private List<String> collection;
    private String headViewUrl;

    public String getNike_name() {
        return nike_name;
    }

    public void setNike_name(String nike_name) {
        this.nike_name = nike_name;
    }

    public List<String> getCollection() {
        return collection;
    }

    public void setCollection(List<String> collection) {
        this.collection = collection;
    }

    public String getHeadViewUrl() {
        return headViewUrl;
    }

    public void setHeadViewUrl(String headViewUrl) {
        this.headViewUrl = headViewUrl;
    }
}
