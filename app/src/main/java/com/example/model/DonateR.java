package com.example.model;

public class DonateR {


    public DonateR() {

    }

    public String getRName() {
        return RName;
    }

    public String getRlicenc_No() {
        return Rlicenc_No;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public void setRlicenc_No(String rlicenc_No) {
        Rlicenc_No = rlicenc_No;
    }

    public void setN_Railway(String n_Railway) {
        N_Railway = n_Railway;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }

    public String getN_Railway() {
        return N_Railway;
    }

    public String getAddr() {
        return Addr;
    }

    public String getWebsite() {
        return Website;
    }

    public String getDesc() {
        return Desc;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public String getEmail() {
        return Email;
    }

    public boolean isVeg() {
        return veg;
    }

    private String RName,Rlicenc_No,N_Railway,Addr,Website,Desc,Phone_no,Email;
    private String UID;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    private boolean veg;
    private String lal,lon;

    public String getLal() {
        return lal;
    }

    public void setLal(String lal) {
        this.lal = lal;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
