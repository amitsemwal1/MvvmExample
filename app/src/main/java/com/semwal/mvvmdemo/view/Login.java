package com.semwal.mvvmdemo.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.semwal.mvvmdemo.Common;
import com.semwal.mvvmdemo.R;
import com.semwal.mvvmdemo.databinding.ActivityLogin1Binding;
import com.semwal.mvvmdemo.interfaces.InterfaceLogin;
import com.semwal.mvvmdemo.model.User;
import com.semwal.mvvmdemo.viewmodel.LoginViewModel;

public class Login extends AppCompatActivity implements InterfaceLogin{

    ActivityLogin1Binding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(Login.this, R.layout.activity_login1);
        final User user=new User();
        binding.setUserLogin(user);
        LoginViewModel loginViewModel=new LoginViewModel(Login.this,user);
        binding.setViewModel(loginViewModel);


    }

    @Override
    public void sucess(String msg) {
        Toast.makeText(this, msg , Toast.LENGTH_SHORT).show();
        

        Common.hideProgressDialog();
    }

    @Override
    public void failiuer(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Common.hideProgressDialog();
    }
}
