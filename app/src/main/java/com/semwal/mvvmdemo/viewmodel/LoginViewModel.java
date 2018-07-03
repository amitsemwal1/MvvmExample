package com.semwal.mvvmdemo.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.semwal.mvvmdemo.R;
import com.semwal.mvvmdemo.Common;
import com.semwal.mvvmdemo.fcm_database.DBOperation;
import com.semwal.mvvmdemo.model.User;
import com.semwal.mvvmdemo.view.Login;
import com.semwal.mvvmdemo.view.Signup;

public class LoginViewModel {

    User user;
    Activity activity;

    public LoginViewModel(Activity activity, User user){
        this.activity=activity;
        this.user=user;
    }

    public void onLoginBtnClick(View view){
        switch (view.getId()){
            case R.id.buttonLogin:
                checkDataValidity();
                break;
            case R.id.textViewSignup:
                Intent i=new Intent(activity,Signup.class);
                activity.startActivity(i);
                break;
        }
    }

    private void checkDataValidity(){

        if (user.isInputDataValid()){
            Common common =Common.getInstance();
            common.ShowProgressDialog(activity);
            DBOperation dbOperation=new DBOperation();
            dbOperation.loginUser(activity,user.getEmail(),user.getPassword());


        }else{
            ((Login)activity).failiuer(user.getMsg());
        }
    }
}
