/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.meetup;

/**
 *
 * @author faramir
 */
public enum Configuration {

    INSTANCE;
    private String groupName = "Torun-JUG";
    private String key = "1e413cad6b174f1a4475362e1d22";

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
