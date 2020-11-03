package com.thao.appmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    CheckBox iconShowPass;
    EditText password;
    ConstraintLayout constraintLayout;
    private String password1 = "221020";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        iconShowPass = findViewById(R.id.show_pass);
        password = (EditText) findViewById(R.id.password);
        constraintLayout = findViewById(R.id.mhLogIn);

        iconShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(LoginActivity.this);
            }

        });

        upDate();
    }


    private void upDate() {
        final Handler handler = new Handler();
        final List<Integer> images = new ArrayList<>();
        images.add(R.drawable.background1);
        images.add(R.drawable.bg2);
        images.add(R.drawable.bg3);
        images.add(R.drawable.bg4);
        images.add(R.drawable.bg5);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int ra = r.nextInt((4 - 0) + 1) + 0;
                constraintLayout.setBackgroundResource(images.get(ra));
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        Boolean flag = imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        if (flag && password.getText().toString().trim().length() == 6) {
            if (password.getText().toString().equals(password1)) {
                System.out.println(password.getText().toString());
                Intent intent = new Intent(LoginActivity.this, MusicActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Hãy nhập ngày mình yêu nhau nhé bồ(6 số)!", Toast.LENGTH_SHORT).show();
            }
        }
        if (!flag) {
            password.setText("");
        }


    }
}