package com.fanyafeng.testnew.PermissionTest;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseFragment;
import com.fanyafeng.testnew.MyApplication.PermissionControl;
import com.fanyafeng.testnew.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NestInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestInFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_new_text_nestin;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NestInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NestInFragment newInstance(String param1, String param2) {
        NestInFragment fragment = new NestInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NestInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nest_in, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        requestPermission();
    }

    private void requestPermission() {
        if (PermissionControl.isGetPermissionFor(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initData();
        } else {
//            此请求是配合此fragment内的权限请求周期使用
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }


    private void initView() {
        btn_new_text_nestin = (Button) getActivity().findViewById(R.id.btn_new_text_nestin);
    }

    private void initData() {
        btn_new_text_nestin.setOnClickListener(this);
    }

    private void newText() {
        File sdCardPath = Environment.getExternalStorageDirectory();
        if (sdCardPath.exists()) {
            File file = new File(sdCardPath.getPath() + File.separator + "fragmentInPer");
            if (!file.exists()) {
                file.mkdirs();
            }
            String filePath = file.getPath() + File.separator + "nestin.txt";
            File file1 = new File(filePath);
            if (file1.exists()) {
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File(filePath));
                String name = "fragment中的fragment请求权限";
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
            case R.id.btn_new_text_nestin:
                newText();
                Log.d("TAG","NestInFragment执行创建文件操作");
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                } else {
                    Toast.makeText(getActivity(), "未获取到权限，该应用不能进行相应的操作", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
