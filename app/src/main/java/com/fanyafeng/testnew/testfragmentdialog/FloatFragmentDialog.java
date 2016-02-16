package com.fanyafeng.testnew.testfragmentdialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.fanyafeng.testnew.R;

/**
 * Created by fanyafeng on 2015/12/28,0028.
 */
public class FloatFragmentDialog extends DialogFragment implements View.OnClickListener{
    public FloatFragmentDialog() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.mystyle);
        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88838B8B")));
        super.onActivityCreated(savedInstanceState);
        View view=inflater.inflate(R.layout.float_dialog_layout,container);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
