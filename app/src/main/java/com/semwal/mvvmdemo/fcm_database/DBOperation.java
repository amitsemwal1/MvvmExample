package com.semwal.mvvmdemo.fcm_database;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.semwal.mvvmdemo.Constants;
import com.semwal.mvvmdemo.model.User;
import com.semwal.mvvmdemo.view.Login;
import com.semwal.mvvmdemo.view.Signup;

import static android.content.ContentValues.TAG;

public class DBOperation {
    public void registerUser(final Activity activity,String email,String password){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("LoginActivity", String.valueOf(task.isSuccessful()));

                        if (!task.isSuccessful()) {
                            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
                        } else {
                            addUserToDatabase(activity, task.getResult().getUser());
                            Toast.makeText(activity, "Signup Successfully", Toast.LENGTH_SHORT).show();
                          //  ((Signup) activity).sucess("Signup Successfully");

                        }
                    }
                }).addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ((Signup) activity).failiuer(e.getMessage());
            }
        });

    }
    public void addUserToDatabase(final Activity activity, FirebaseUser firebaseUser) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail(),Constants.FCM_TOKEN);
        database.child(Constants.DB_USER).child(firebaseUser.getUid())
                .setValue(user)
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Success", "Success");
                            ((Signup) activity).sucess("User Successfully added");
                        } else {
                            Log.d("Failure", "Failure");
                            ((Signup) activity).failiuer("User Failed to added");
                        }
                    }
                });
    }


    public void loginUser(final Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "performFirebaseLogin:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            ((Login)activity).sucess("Login Success");
                        } else {
                            ((Login)activity).failiuer(task.getException().getMessage());
                        }
                    }
                });

    }
}
