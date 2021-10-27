package com.example.prm_bookingfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.prm_bookingfield.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setTitleTextAppearance(this, R.style.FontLogo);
        View logo = topAppBar.getChildAt(0);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =" https://10.0.2.2:5001/api/field/1";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(response + " aaaaaaaaaa");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                  System.out.println(error + " aaaaaaaaaa");

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new UserHomeFragment()).commit();
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment selectedFragment = null;
//                switch (item.getItemId()) {
//                    case R.id.pageHome:
//                        selectedFragment = new UserHomeFragment();
//                        break;
//                    case R.id.pageAccount:
//                        if (validation.isUser()) {
//                            selectedFragment = new ProfileFragment();
//                        } else {
//                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                            return true;
//                        }
//                        break;
//                    case R.id.page_2:
//                        selectedFragment = new CartFragment();
//                        break;
//                    case R.id.page_3:
//                        selectedFragment = new TabFragment();
//                        break;
//                    default:
//                        return false;
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
//                return true;
//            }
//        });
//
//        logo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });
//
//        Intent intent = this.getIntent();
//        if (intent.getStringExtra("action") != null){
//            if (intent.getStringExtra("action").equals("add to cart")) {
//                Fragment selectedFragment = new CartFragment();
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
//            }
//        }
    }

//    public void clickToViewMorePromotion(View view) {
//        Intent intent = new Intent(MainActivity.this, PromotionActivity.class);
//        startActivity(intent);
//    }
//
//    public void clickToViewDetalFootballField(View view) {
//        Intent intent = new Intent(MainActivity.this, UserFootballFieldDetailActivity.class);
//        startActivity(intent);
//    }



}