package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.prm_bookingfield.dtos.MySingleton;
import com.example.prm_bookingfield.dtos.User;
import com.example.prm_bookingfield.service.ManagePrefConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";
    private boolean isLogin = false;

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.actLogin_txtUsername);
        txtPassword = findViewById(R.id.actLogin_txtPwd);
        btnLogin = findViewById(R.id.actRegister_btnLogin);
        btnSignup = findViewById(R.id.actLogin_btnSignUp);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                if(intent != null){
                    onBackPressed();
                }
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkEmpty = true;
                String u = txtUsername.getText().toString();
                String p = txtPassword.getText().toString();
                if (TextUtils.isEmpty(u)) {
                    txtUsername.setError("Enter your phone number!");
                    checkEmpty = false;
                }
                if (TextUtils.isEmpty(p)) {
                    txtPassword.setError("Enter your password!");
                    checkEmpty = false;
                }
                if(checkEmpty) {
                    checkLogin(u, p, false);
                }
            }
        });
    }
    public void checkLogin(String username, String password, boolean rememberMe){
        String url = URL + "Users/authenticate";
        StringRequest request = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    User user = new User(obj.getString("token"));
                    if(user != null){
                        isLogin = true;
                    }
                    ManagePrefConfig.getInstance(getApplicationContext()).userLogin(user);
                    finish();
                    if(isLogin) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                  Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("UserName", username);
                params.put("Password", password);
                params.put("RememberMe", String.valueOf(rememberMe));
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}