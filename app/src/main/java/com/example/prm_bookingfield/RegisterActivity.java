package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.prm_bookingfield.dtos.MySingleton;
import com.google.android.material.textfield.TextInputLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";
    private Validator validator;
    @NotEmpty()
    @Length(min = 5, max = 30)
    private TextInputLayout txtName;

    @NotEmpty()
    private TextInputLayout txtDob;

    @NotEmpty()
    @Email
    private TextInputLayout txtEmail;

    @NotEmpty()
    private TextInputLayout txtPhone;

    @NotEmpty()
    private TextInputLayout txtUsername;

    @NotEmpty()
    private TextInputLayout txtPassword;

    @NotEmpty()
    private TextInputLayout txtConfirmPwd;
    private Button btnSignup;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        validator = new Validator(this);
        validator.setValidationListener(this);

        txtName =  findViewById(R.id.actRegister_txtName);
        txtDob = findViewById(R.id.actRegister_txtDob);
        txtEmail =  findViewById(R.id.actRegister_txtEmail);
        txtPhone =  findViewById(R.id.actRegister_txtPhone);
        txtUsername = findViewById(R.id.actRegister_txtUsername);
        txtPassword =  findViewById(R.id.actRegister_txtPassword);
        txtConfirmPwd =  findViewById(R.id.actRegister_txtConfirm);

        btnSignup = findViewById(R.id.actRegister_btnSignUp);
        btnLogin = findViewById(R.id.actRegister_btnLogin);


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
                validator.validate();
                String fn = txtName.getEditText().getText().toString().trim();
                String ln = txtName.getEditText().getText().toString().trim();
                String dob = txtDob.getEditText().getText().toString().trim();
                String email = txtEmail.getEditText().getText().toString().trim();
                String phone = txtPhone.getEditText().getText().toString().trim();
                String username = txtPhone.getEditText().getText().toString().trim();
                String pwd = txtPassword.getEditText().getText().toString().trim();
                String rePwd = txtConfirmPwd.getEditText().getText().toString().trim();

//                if (TextUtils.isEmpty(fn)) {
//                    txtName.setHint("Name cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (TextUtils.isEmpty(dob)) {
//                    txtDob.setHint("Day of birth cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (TextUtils.isEmpty(email)) {
//                    txtEmail.setHint("Email cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (TextUtils.isEmpty(phone)) {
//                    txtPhone.setHint("Phone Number cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (TextUtils.isEmpty(username)) {
//                    txtUsername.setHint("Username cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (TextUtils.isEmpty(pwd)) {
//                    txtPassword.setHint("Password cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (TextUtils.isEmpty(rePwd)) {
//                    txtConfirmPwd.setHint("Password cannot be blank!");
//                    checkEmpty = false;
//                }
//                if (checkEmpty) {
                    if (!pwd.equals(rePwd)) {
                        Toast.makeText(RegisterActivity.this, "Confirm password not match!", Toast.LENGTH_SHORT).show();
                    } else {
                        registerAccount(fn, ln, dob, email, phone, username, pwd, rePwd);
                    }
//                }
            }
        });
    }

    public void registerAccount(String fn, String ln, String dob, String email, String phone, String username, String pwd, String rePwd){
        String url = URL + "Users/register";
        StringRequest request = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){
                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
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

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors){
            View view = error.getView();
            String msg = error.getCollatedErrorMessage(this);
            if(view instanceof TextInputLayout){
                ((TextInputLayout) view).setError(msg);
            }else {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            }
        }
    }
}