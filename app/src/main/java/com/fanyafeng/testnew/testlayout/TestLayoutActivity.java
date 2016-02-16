package com.fanyafeng.testnew.testlayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.R;

public class TestLayoutActivity extends ABaseActivity {
    private TextView id_test_shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_test_layout);
        super.initView();
        title = "测试shape";
        isShowEmail=true;
//        id_test_shape = (TextView) findViewById(R.id.id_test_shape);
//        Drawable drawable = getResources().getDrawable(R.drawable.gradient_box);
//        id_test_shape.setBackgroundDrawable(drawable);
    }

    @Override
    protected void initData() {
        super.initData();

        addFashiEvent();
    }

    private void addFashiEvent() {
        for (int i = 0; i < 2; i++) {
            LinearLayout test_hor = (LinearLayout) findViewById(R.id.test_hor);
            LinearLayout id_item_fashi = (LinearLayout) findViewById(R.id.test_ver);
            View view = LayoutInflater.from(this).inflate(R.layout.item_add_layout, null);
            TextView textVie = (TextView) view.findViewById(R.id.id_add_text1);
            textVie.setText(i + "i");
            for (int j = 0; j < 3; j++) {
                View view1 = LayoutInflater.from(this).inflate(R.layout.item_add_layout, null);
                TextView textVi = (TextView) view1.findViewById(R.id.id_add_text1);
                textVi.setText(j + "j");
                if (i == 0) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    test_hor.addView(view1, params);
                } else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    id_item_fashi.addView(view1, params);
                }
            }
        }
    }
}
