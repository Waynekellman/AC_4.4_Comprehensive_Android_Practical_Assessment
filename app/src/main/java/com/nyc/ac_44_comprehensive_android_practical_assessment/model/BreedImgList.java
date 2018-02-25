package com.nyc.ac_44_comprehensive_android_practical_assessment.model;

import java.util.ArrayList;

/**
 * Created by Wayne Kellman on 2/25/18.
 */

public class BreedImgList {
    private String status;
    private ArrayList<String> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }
}
