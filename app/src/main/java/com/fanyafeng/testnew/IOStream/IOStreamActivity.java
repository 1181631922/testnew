package com.fanyafeng.testnew.IOStream;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IOStreamActivity extends BaseActivity {
    private TextView btn_new_text, btn_write_text, btn_read_text, btn_del_text;
    private File sdFatherPath = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iostream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "io流";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        btn_new_text = (Button) findViewById(R.id.btn_new_text);
        btn_new_text.setOnClickListener(this);
        btn_write_text = (Button) findViewById(R.id.btn_write_text);
        btn_write_text.setOnClickListener(this);
        btn_read_text = (Button) findViewById(R.id.btn_read_text);
        btn_read_text.setOnClickListener(this);
        btn_del_text = (Button) findViewById(R.id.btn_del_text);
        btn_del_text.setOnClickListener(this);
    }

    private void initData() {

    }

    private boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? true : false;
    }

    /**
     * 新建文件并写入数据
     */
    private void newText() {
        if (sdFatherPath.exists()) {
            File file = new File(sdFatherPath.getPath() + File.separator + "fanyafeng");
            if (!file.exists()) {
//                创建file目录
                file.mkdirs();
            }
//                File.separator多系统下路径分隔符
            String filePaht = file.getPath() + File.separator + "first.txt";
            File file1 = new File(filePaht);
            if (!file1.exists())
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File(filePaht));
                String name = "fanyafeng";
                fileOutputStream.write(name.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                   if (fileOutputStream!=null)
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件并写入数据
     */

    private void writeText() {
        if (sdFatherPath.exists()) {
            byte Buffer[] = new byte[1024];
            String filePaht = sdFatherPath.getPath() + File.separator + "fanyafeng" + File.separator + "first.txt";
            FileOutputStream fileOutputStream = null;
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                fileInputStream = new FileInputStream(filePaht);
                byteArrayOutputStream = new ByteArrayOutputStream();
                int dataLength = fileInputStream.read(Buffer);
                byteArrayOutputStream.write(Buffer, 0, dataLength);
                fileOutputStream = new FileOutputStream(new File(filePaht));
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                String name = "\n";
                fileOutputStream.write(name.getBytes("UTF-8"));
                String phone = "18010479518";
                fileOutputStream.write(phone.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    /**
     * 读取相应目录下的文件
     *
     * @param path
     * @return
     */
    private String readText(String path) {
        byte Buffer[] = new byte[1024];
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                byteArrayOutputStream = new ByteArrayOutputStream();
                int dataLength = fileInputStream.read(Buffer);
                byteArrayOutputStream.write(Buffer, 0, dataLength);
                return new String(byteArrayOutputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_new_text:
                newText();
                break;
            case R.id.btn_write_text:
                writeText();
                break;
            case R.id.btn_read_text:
                Toast.makeText(this, readText(sdFatherPath.getPath() + File.separator + "fanyafeng" + File.separator + "first.txt"), Toast.LENGTH_SHORT).show();
//                readText(sdFatherPath.getPath() + File.separator + "fanyafeng" + File.separator + "first.txt");
                break;
            case R.id.btn_del_text:
                break;
        }
    }
}
