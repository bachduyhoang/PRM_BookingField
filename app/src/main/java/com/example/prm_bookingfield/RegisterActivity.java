package com.example.prm_bookingfield;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.prm_bookingfield.dtos.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";

    private EditText txtName;
    private EditText txtDob;
    private EditText txtEmail;
    private EditText txtPhone;
    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtConfirmPwd;
    private Button btnSignup;
    private Button btnLogin;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName =  findViewById(R.id.actRegister_txtName);
        txtDob = findViewById(R.id.actRegister_txtDob);
        txtEmail =  findViewById(R.id.actRegister_txtEmail);
        txtPhone =  findViewById(R.id.actRegister_txtPhone);
        txtUsername = findViewById(R.id.actRegister_txtUsername);
        txtPassword =  findViewById(R.id.actRegister_txtPassword);
        txtConfirmPwd =  findViewById(R.id.actRegister_txtConfirm);

        btnSignup = findViewById(R.id.actRegister_btnSignUp);
        btnLogin = findViewById(R.id.actRegister_btnLogin);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.actRegister_txtName,
                RegexTemplate.NOT_EMPTY, R.string.invalid_Name);

        awesomeValidation.addValidation(this, R.id.actRegister_txtDob,
                RegexTemplate.NOT_EMPTY, R.string.invalid_Dob);

        awesomeValidation.addValidation(this, R.id.actRegister_txtEmail,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);

        awesomeValidation.addValidation(this, R.id.actRegister_txtPhone,
                "[0-9]{1}[0-9]{9}$", R.string.invalid_Phone);

        awesomeValidation.addValidation(this, R.id.actRegister_txtUsername,
                RegexTemplate.NOT_EMPTY, R.string.invalid_username);

        awesomeValidation.addValidation(this, R.id.actRegister_txtPassword,
               ".{6,}", R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.actRegister_txtConfirm,
                R.id.actRegister_txtPassword, R.string.invalid_RePwd);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                if(intent != null){
                    onBackPressed();
                }
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    String fn = txtName.getText().toString().trim();
                    String ln = txtName.getText().toString().trim();
                    String dob = txtDob.getText().toString().trim();
                    String email = txtEmail.getText().toString().trim();
                    String phone = txtPhone.getText().toString().trim();
                    String username = txtPhone.getText().toString().trim();
                    String pwd = txtPassword.getText().toString().trim();
                    String rePwd = txtConfirmPwd.getText().toString().trim();
                    try {
                        registerAccount(fn, ln, dob, email, phone, username, pwd, rePwd);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void registerAccount(String fn, String ln, String dob, String email, String phone, String username, String pwd, String rePwd) throws JSONException {
        String url = URL + "Users/register";

        final StringRequest request = new StringRequest (Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("FirstName", fn);
                params.put("LastName", ln);
                params.put("DoB", String.valueOf(dob));
                params.put("Email", email);
                params.put("PhoneNumber", String.valueOf(phone));
                params.put("UserName", String.valueOf(username));
                params.put("Password", pwd);
                params.put("ConfirmPassword", rePwd);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}