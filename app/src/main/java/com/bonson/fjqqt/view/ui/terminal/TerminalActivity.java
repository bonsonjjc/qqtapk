package com.bonson.fjqqt.view.ui.terminal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.resource.view.MToolbar;


public class TerminalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);
        MToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("终端设置");
        toolbar.getTvLeft().setOnClickListener(v -> finish());
    }

    public void itemClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_limit:
                intent.setClassName(this, Route.limit);
                break;
            case R.id.tv_mode:
                intent.setClassName(this, Route.mode);
                break;
            case R.id.tv_alarm:
                intent.setClassName(this, Route.alarm);
                break;
            case R.id.tv_lesson:
                intent.setClassName(this, Route.lesson);
                break;
            case R.id.tv_timer:
                intent.setClassName(this, Route.timer);
                break;
            default:
                intent.setClassName(this, Route.ring);

        }
        startActivity(intent);
    }
}
