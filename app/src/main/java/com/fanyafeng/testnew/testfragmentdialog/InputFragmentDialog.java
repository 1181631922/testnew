package com.fanyafeng.testnew.testfragmentdialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.fanyafeng.testnew.R;

/**
 * Created by fanyafeng on 2015/10/30,0030.
 */
public class InputFragmentDialog extends DialogFragment implements View.OnClickListener {
    private EditText et_input_name;
    private EditText et_input_password;
    private Button btn_input_cancle;
    private Button btn_input_enter;

    public interface InputListenr {
        void getNameAndPassword(String username, String password);
    }

    public InputFragmentDialog() {
        InputFragmentDialog.this.setStyle(2, R.style.mystyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.layout_dialog_input, container);

        et_input_name = (EditText) view.findViewById(R.id.et_input_name);
        et_input_name.setText("从activity中拿到的tag标签："+getTag());
        et_input_password = (EditText) view.findViewById(R.id.et_input_password);
        btn_input_cancle = (Button) view.findViewById(R.id.btn_input_cancle);
        btn_input_cancle.setOnClickListener(this);
        btn_input_enter = (Button) view.findViewById(R.id.btn_input_enter);
        btn_input_enter.setOnClickListener(this);
        return view;
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
            case R.id.btn_input_cancle:
                InputFragmentDialog.this.dismiss();
                break;
            case R.id.btn_input_enter:
                InputListenr inputListenr = (InputListenr) getActivity();
                inputListenr.getNameAndPassword(et_input_name.getText().toString(), et_input_password.getText().toString());
                InputFragmentDialog.this.dismiss();
                break;
        }
    }
}
