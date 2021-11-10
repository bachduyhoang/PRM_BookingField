package com.example.prm_bookingfield;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.prm_bookingfield.dtos.GroupFieldAdapter;
import com.example.prm_bookingfield.dtos.User;
import com.example.prm_bookingfield.service.ManagePrefConfig;
import com.example.prm_bookingfield.ui.cart.CartFragment;
import com.example.prm_bookingfield.ui.history.HistoryFragment;
import com.example.prm_bookingfield.ui.history.TabFragment;

import com.example.prm_bookingfield.ui.home.HomeFragment;
import com.example.prm_bookingfield.ui.home.ProfileFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;


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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                ManagePrefConfig mng = new ManagePrefConfig(MainActivity.this);
                User user = mng.getToken();
                switch (item.getItemId()) {
                    case R.id.pageHome:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.pageAccount:
                        if (user != null) {
                            selectedFragment = new ProfileFragment();
                        } else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            return true;
                        }
                        break;
                    case R.id.page_2:
                        selectedFragment = new CartFragment();
                        break;
                    case R.id.page_3:
                        if (user != null) {
                            selectedFragment = new HistoryFragment();
                        } else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
                return true;
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        Intent intent = this.getIntent();
        String action = intent.getStringExtra("action");
        if (action != null) {
            Log.d("action", action);
            if (action.equals("add to cart")) {
                Fragment cartFragment = new CartFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, cartFragment).commit();
                bottomNavigationView.setSelectedItemId(R.id.page_2);
            } else if (action.equals("view_history")) {
                Fragment tabFragment = new TabFragment();
                Bundle bundle=new Bundle();
                bundle.putString("action","view_history");
                tabFragment.setArguments(bundle);
                bottomNavigationView.setSelectedItemId(R.id.page_3);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, tabFragment).commit();
            }
        } else {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new UserHomeFragment()).commit();

        }
    }

    public void clickToViewDetalFootballField(View view) {
        Intent intent = new Intent(MainActivity.this, BookingActivity.class);
        startActivity(intent);
    }

}