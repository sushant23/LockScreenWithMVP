package com.sushant.pinlock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sushant.pinlock.service.LockerService;

/**
 * Created by braindigit on 3/14/16.
 */
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LockerService.class);
        startService(intent);
    }
}
