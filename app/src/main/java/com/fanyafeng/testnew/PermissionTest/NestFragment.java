package com.fanyafeng.testnew.PermissionTest;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.BaseFragment;
import com.fanyafeng.testnew.MyApplication.PermissionControl;
import com.fanyafeng.testnew.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_new_text_nest;
    private Button btn_add_nestin;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NestFragment newInstance(String param1, String param2) {
        NestFragment fragment = new NestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NestFragment() {
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
        return inflater.inflate(R.layout.fragment_nest, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        btn_new_text_nest = (Button) getActivity().findViewById(R.id.btn_new_text_nest);
        btn_add_nestin=(Button)getActivity().findViewById(R.id.btn_add_nestin);
        btn_add_nestin.setOnClickListener(this);
        requestpermission();
    }


    private void initData() {
        btn_new_text_nest.setOnClickListener(this);
    }

    private void newText() {
        File sdCardPath= Environment.getExternalStorageDirectory();
        if (sdCardPath.exists()){
            File file=new File(sdCardPath.getPath()+File.separator+"fragmentPer");
            if (!file.exists()){
                file.mkdirs();
            }
            String filePath=file.getPath()+File.separator+"fragmentPer.txt";
            File file1=new File(filePath);
            if (!file1.exists()){
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream=null;
            try {
                fileOutputStream=new FileOutputStream(new File(filePath));
                String name="activity中的fragment请求权限";
                fileOutputStream.write(name.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (fileOutputStream!=null)
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
            case R.id.btn_new_text_nest:
                newText();
                break;
            case R.id.btn_add_nestin:
                addNewFragment();
                break;
        }
    }

    private void addNewFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        NestInFragment nestInFragment=new NestInFragment();
        fragmentTransaction.setCustomAnimations(R.anim.push_left_in,R.anim.push_right_out,R.anim.push_left_in,R.anim.push_right_out);
        fragmentTransaction.add(R.id.nestin_conatiner,nestInFragment,"NestInFragment");
        fragmentTransaction.addToBackStack("NestInFragment");
        fragmentTransaction.commit();
    }

    private void requestpermission(){
        if (PermissionControl.isGetPermissionFor(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            initData();
        }else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        List<Fragment> fragmentList=getChildFragmentManager().getFragments();
//        if (fragmentList!=null){
//            for (Fragment fragment:fragmentList){
//                if (fragment!=null){
//                    fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
//                }
//            }
//        }
        switch (requestCode){
            case PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    initData();
                }else {
                    Toast.makeText(getActivity(),"请求被拒绝，应用无法进行相应操作",Toast.LENGTH_SHORT).show();
                }
        }
    }
}
