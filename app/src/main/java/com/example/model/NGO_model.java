package com.example.model;


public class NGO_model {
    public NGO_model() {
    }

    private String ngo_name;
    private String ngo_reg;
    private String aadhar_img_url;
    private String govt_ltr_img_url;
    private String joing_ltr_img_ltr;

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getNgo_reg() {
        return ngo_reg;
    }

    public void setNgo_reg(String ngo_reg) {
        this.ngo_reg = ngo_reg;
    }

    public String getAadhar_img_url() {
        return aadhar_img_url;
    }

    public void setAadhar_img_url(String aadhar_img_url) {
        this.aadhar_img_url = aadhar_img_url;
    }

    public String getGovt_ltr_img_url() {
        return govt_ltr_img_url;
    }

    public void setGovt_ltr_img_url(String govt_ltr_img_url) {
        this.govt_ltr_img_url = govt_ltr_img_url;
    }

    public String getJoing_ltr_img_ltr() {
        return joing_ltr_img_ltr;
    }

    public void setJoing_ltr_img_ltr(String joing_ltr_img_ltr) {
        this.joing_ltr_img_ltr = joing_ltr_img_ltr;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String phone_no;
    private String email;
}
