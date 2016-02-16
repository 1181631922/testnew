package com.fanyafeng.testnew.PermissionTest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.MyApplication.PermissionControl;
import com.fanyafeng.testnew.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PermissionInActivity extends BaseActivity {
    private Button btn_permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisson_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试activity中的权限管理";
        isShowEmail=true;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "重新给予应用权限？", Snackbar.LENGTH_LONG)
                        .setAction("获取权限", onClickListener).show();
            }
        });
        initView();
        requestPermission();
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestPermission();
        }
    };

    private void initView() {
        btn_permission = (Button) findViewById(R.id.btn_permission);
    }

    private void requestPermission() {
        if (PermissionControl.isGetPermissionFor(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initData();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    private void initData() {
        btn_permission.setOnClickListener(this);
    }

    private void newText() {
        File sdCarePath = Environment.getExternalStorageDirectory();
        if (sdCarePath.exists()) {
            File file = new File(sdCarePath.getPath() + File.separator + "permission");
            if (!file.exists()) {
                file.mkdirs();
            }
            String filePath = file.getPath() + File.separator + "permission.txt";
            File file1 = new File(filePath);
            if (!file1.exists())
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File(filePath));
                String name = "樊亚风";
                fileOutputStream.write(name.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null)
                        fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_permission:
                newText();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                } else {
                    Toast.makeText(this, "请求被拒绝，应用无法进行相应操作", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
