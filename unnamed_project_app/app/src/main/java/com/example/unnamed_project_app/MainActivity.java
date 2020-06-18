package com.example.unnamed_project_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unnamed_project_app.base.contant.HttpRequestUrl;
import com.example.unnamed_project_app.base.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        Button homePageButton = findViewById(R.id.homePageButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, String> map = new HashMap<>();
                        EditText usernameText = findViewById(R.id.usernameText);
                        EditText passwordText = findViewById(R.id.passwordText);
                        map.put("username", usernameText.getText().toString());
                        map.put("password", passwordText.getText().toString());
                        result = HttpUtil.sendPostHttpRequest(map, HttpRequestUrl.LOGIN_URL);
                    }
                }).start();
                while(true){
                    if(result != null){
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });
        homePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = HttpUtil.sendPostHttpRequest(null, HttpRequestUrl.HOME_PAGE);
                    }
                }).start();
                while(true){
                    if(result != null){
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });
    }
}
