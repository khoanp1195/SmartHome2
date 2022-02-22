package com.example.firebasetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;




public class admin extends Activity {
    public static int cauhoi;
    EditText socauhoi;
    Button bt;
    Button tieptheo;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosenumber);
        bt = (Button) findViewById(R.id.BtnBack);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        socauhoi = (EditText) findViewById(R.id.Edtsocau);
        tieptheo=(Button)findViewById(R.id.BtnNext);
        socauhoi.addTextChangedListener(numberTextWathcer);
        tieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cauhoi = Integer.parseInt(socauhoi.getText().toString());

                if  (cauhoi == 552)
                {
                    Intent intent = new Intent(admin.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }

                else {
                    socauhoi.setError("Bạn đã nhập sai mật khẩu vui lòng nhập lại, hoặc liên hệ quản trị viên để được cấp mật khẩu");
                    socauhoi.requestFocus();
                    return;
                }




            }
        });


    }
    private TextWatcher numberTextWathcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String socauhoiInput = socauhoi.getText().toString().trim();
            tieptheo.setEnabled(!socauhoiInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}