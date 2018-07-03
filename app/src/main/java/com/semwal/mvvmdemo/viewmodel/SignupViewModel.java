package com.semwal.mvvmdemo.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.semwal.mvvmdemo.Common;
import com.semwal.mvvmdemo.R;
import com.semwal.mvvmdemo.fcm_database.DBOperation;
import com.semwal.mvvmdemo.interfaces.InterfaceLogin;
import com.semwal.mvvmdemo.model.User;
import com.semwal.mvvmdemo.view.Login;
import com.semwal.mvvmdemo.view.Signup;

public class SignupViewModel {

    User user;
    Activity activity;
    public SignupViewModel(Activity activity, User user){
        this.activity=activity;
        this.user=user;
    }
    public void onSignupbtnclick(View view){
        switch (view.getId()) {
            case R.id.buttonSignup:
                checkDataValidity();
                break;
            case R.id.textViewLogin:
                Intent i=new Intent(activity,Login.class);
                activity.startActivity(i);
                break;
        }
    }


    private void checkDataValidity(){

        if (user.isInputDataValid()){

            Common common =Common.getInstance();
            common.ShowProgressDialog(activity);
            DBOperation dbOperation=new DBOperation();
            dbOperation.registerUser(activity,user.getEmail(),user.getPassword());

          //  ((Signup)activity).sucess(user.getMsg());

        }else{
            ((Signup)activity).failiuer(user.getMsg());
        }
    }
}
