package com.fanyafeng.testnew.testfragmentdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testslide.ScreenUtils;

/**
 * Created by fanyafeng on 2015/10/29,0029.
 */
public class MyFragmentDialog extends DialogFragment implements View.OnClickListener {
    private String dialog_title = null;
    private String dialog_desc = null;
    private String btn_cancle = null;
    private String btn_enter = null;
    private TextView text_dialog_title;
    private TextView text_dialog_desc;
    private Button btn_dialog_cancle;
    private Button btn_dialog_enter;

    public MyFragmentDialog() {
    }


    public MyFragmentDialog(String dialog_title, String dialog_desc) {
        this.dialog_title = dialog_title;
        this.dialog_desc = dialog_desc;
        MyFragmentDialog.this.setStyle(2, R.style.mystyle);
    }

    public MyFragmentDialog(String dialog_title, String dialog_desc, String btn_cancle, String btn_enter) {
        this.dialog_title = dialog_title;
        this.dialog_desc = dialog_desc;
        this.btn_cancle = btn_cancle;
        this.btn_enter = btn_enter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.layout_fragment_dialog, container);
        text_dialog_title = (TextView) view.findViewById(R.id.text_dialog_title);
        if (dialog_title != null)
            text_dialog_title.setText(dialog_title);
        text_dialog_desc = (TextView) view.findViewById(R.id.text_dialog_desc);
        if (dialog_desc != null)
            text_dialog_desc.setText(dialog_desc);
        btn_dialog_cancle = (Button) view.findViewById(R.id.btn_dialog_cancle);
        if (btn_cancle != null)
            btn_dialog_cancle.setText(btn_cancle);
        btn_dialog_cancle.setOnClickListener(this);
        btn_dialog_enter = (Button) view.findViewById(R.id.btn_dialog_enter);
        if (btn_enter != null)
            btn_dialog_enter.setText(btn_enter);
        btn_dialog_enter.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels - (int) DpPxConvert.dip2px(getActivity(), 30f), getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_cancle:
                MyFragmentDialog.this.dismiss();
                break;
            case R.id.btn_dialog_enter:

                break;
        }
    }
}
