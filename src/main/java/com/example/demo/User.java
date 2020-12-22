package com.example.demo;

public class User {
    String name;
    String address;
    String number;
    String web;
    String mail;
    String szer;
    String dlug;
    String vcard;


    public String getSzer() {
        return szer;
    }

    public void setSzer(String szer) {
        this.szer = szer;
    }

    public String getDlug() {
        return dlug;
    }

    public void setDlug(String dlug) {
        this.dlug = dlug;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
    }

    public String getVcard() {
        return vcard;
    }

    public void setVcard(String vcard) {
        this.vcard = vcard;
    }

    @Override
    public String toString() {
        return vcard;
    }
}
