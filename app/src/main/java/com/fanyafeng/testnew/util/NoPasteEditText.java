package com.fanyafeng.testnew.util;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by fanyafeng on 2015/11/4,0004.
 */
public class NoPasteEditText extends EditText {
    /**
     * 粘贴id
     */
    private static final int ID_PASTE = android.R.id.paste;

    public NoPasteEditText(Context context) {
        super(context);
    }

    public NoPasteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoPasteEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        //粘帖板
        ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        System.out.println("id:" + id);
        // 粘贴板内容
        CharSequence paste = clip.getText();
        System.out.println("paste = " + paste);
        if (id == ID_PASTE) {
            Toast toast = Toast.makeText(getContext(), "请手动进行输入！", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return super.onTextContextMenuItem(id);
    }
}
