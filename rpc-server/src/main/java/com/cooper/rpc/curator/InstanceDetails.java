package com.cooper.rpc.curator;

/**
 * Created by emirbobo on 2016/10/5.
 */
public class InstanceDetails {

    private String description;

    public InstanceDetails() {
        this("");
    }

    public InstanceDetails(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
