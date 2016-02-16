package com.fanyafeng.testnew.testfragmentdialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class FragmentDialogActivity extends BaseActivity implements InputFragmentDialog.InputListenr {
    private Button btn_open_dialog, btn_bottom_dialog, btn_input_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试fragmentdialog";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        isShowEmail = true;
        initView();
        initData();
    }


    private void initView() {
        btn_open_dialog = (Button) findViewById(R.id.btn_open_dialog);
        btn_open_dialog.setOnClickListener(this);
        btn_bottom_dialog = (Button) findViewById(R.id.btn_bottom_dialog);
        btn_bottom_dialog.setOnClickListener(this);
        btn_input_dialog = (Button) findViewById(R.id.btn_input_dialog);
        btn_input_dialog.setOnClickListener(this);

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
//            注意dialogfragment继承的是那个包，传入的参数不同
            case R.id.btn_open_dialog:
                MyFragmentDialog myFragmentDialog = new MyFragmentDialog("我是标题", "我是内容描述");

                myFragmentDialog.show(getSupportFragmentManager(), "myfragmentdialog");
                break;
            case R.id.btn_bottom_dialog:
                BottomFragmentDialog bottomFragmentDialog = new BottomFragmentDialog();
                bottomFragmentDialog.show(getFragmentManager(), "bottomfragmentdialog");
                break;
            case R.id.btn_input_dialog:
                InputFragmentDialog inputFragmentDialog = new InputFragmentDialog();
                inputFragmentDialog.show(getFragmentManager(), "inputfragmentdialog");
                break;
        }
    }

    @Override
    public void getNameAndPassword(String username, String password) {
        Toast.makeText(this, username + password, Toast.LENGTH_SHORT).show();
    }
}
