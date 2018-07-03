package com.semwal.mvvmdemo.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.oob.SignUp;
import com.semwal.mvvmdemo.Common;
import com.semwal.mvvmdemo.R;
import com.semwal.mvvmdemo.databinding.ActivitySignupBinding;
import com.semwal.mvvmdemo.interfaces.InterfaceLogin;
import com.semwal.mvvmdemo.model.User;
import com.semwal.mvvmdemo.viewmodel.SignupViewModel;

public class Signup extends AppCompatActivity implements InterfaceLogin {
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_signup);
        User user=new User();
        binding.setUserSignup(user);
        SignupViewModel viewModel=new SignupViewModel(this,user);
        binding.setSignupViewModel(viewModel);
    }

    @Override
    public void sucess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Common.hideProgressDialog();
        Intent i=new Intent(Signup.this,Login.class);
        startActivity(i);
    }

    @Override
    public void failiuer(String msg) {

        Common.hideProgressDialog();

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
