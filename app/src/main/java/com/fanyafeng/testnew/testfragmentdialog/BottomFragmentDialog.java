package com.fanyafeng.testnew.testfragmentdialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.fanyafeng.testnew.R;

/**
 * Created by fanyafeng on 2015/10/30,0030.
 */
public class BottomFragmentDialog extends DialogFragment implements View.OnClickListener {
    private Button btn_top_shang;
    private Button btn_top_zhong;
    private Button btn_top_xia;
    private Button btn_bottom;
    private String top_shang = null;
    private String top_zhong = null;
    private String top_xia = null;
    private String bottom = null;

    public BottomFragmentDialog() {
        BottomFragmentDialog.this.setStyle(2, R.style.transparentFrameWindowStyle);
    }

    public BottomFragmentDialog(String top_shang, String top_zhong, String top_xia, String bottom) {
        this.top_shang = top_shang;
        this.top_zhong = top_zhong;
        this.top_xia = top_xia;
        this.bottom = bottom;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.layout_dialog_bottom, container);
        btn_top_shang = (Button) view.findViewById(R.id.btn_top_shang);
        if (top_shang != null)
            btn_top_shang.setText(top_shang);
        btn_top_shang.setOnClickListener(this);
        btn_top_zhong = (Button) view.findViewById(R.id.btn_top_zhong);
        if (top_zhong != null)
            btn_top_zhong.setText(top_shang);
        btn_top_zhong.setOnClickListener(this);
        btn_top_xia = (Button) view.findViewById(R.id.btn_top_xia);
        if (top_xia != null)
            btn_top_xia.setText(top_shang);
        btn_top_xia.setOnClickListener(this);
        btn_bottom = (Button) view.findViewById(R.id.btn_bottom);
        if (bottom != null)
            btn_bottom.setText(top_shang);
        btn_bottom.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setWindowAnimations(R.style.main_menu_animstyle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_top_shang:
                break;
            case R.id.btn_top_zhong:
                break;
            case R.id.btn_top_xia:
                break;
            case R.id.btn_bottom:
                BottomFragmentDialog.this.dismiss();
                break;
        }
    }
}
