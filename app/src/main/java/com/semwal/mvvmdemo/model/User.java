package com.semwal.mvvmdemo.model;

import android.databinding.BaseObservable;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class User{

    String msg;
    String email;
    String password;

    public String uid;
    public String fcm_token;
    public User(){}
    public User(String uid,String email,String token){
        this.uid=uid;
        this.email=email;
        this.fcm_token=token;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isInputDataValid(){
        boolean status=true;

        if (TextUtils.isEmpty(getEmail())){
            status=false;
            setMsg("Enter Email");
            return status;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
            status=false;
            setMsg("Incorrect email");
            return status;
        }else if (TextUtils.isEmpty(getPassword())){
            status=false;
            setMsg("Enter Password");
            return status;
        }else if (getPassword().length()<5){
            status =false;
            setMsg("Password is to short");
            return status;

        }

    return status;
    }

}
