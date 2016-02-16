package com.fanyafeng.testnew.toolar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextPaint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.BottomSheets.BottomSheetsActivity;
import com.fanyafeng.testnew.DataBaseTest.SQLOperateActivity;
import com.fanyafeng.testnew.FragmentStackTest.StackActivity;
import com.fanyafeng.testnew.FrameTest.TestFrameActivity;
import com.fanyafeng.testnew.FrascoOkhttp.FrescoSampleActivity;
import com.fanyafeng.testnew.IOStream.IOStreamActivity;
import com.fanyafeng.testnew.MatrixTest.MatrixActivity;
import com.fanyafeng.testnew.MatrixTest.MovementActivity;
import com.fanyafeng.testnew.PermissionTest.FragmentNestActivity;
import com.fanyafeng.testnew.PermissionTest.PermissionInActivity;
import com.fanyafeng.testnew.QQBrowser.QQBrowserActivity;
import com.fanyafeng.testnew.QQBrowser.QQBrowserImageActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.ViewDragHelperTest.LinearDragActivity;
import com.fanyafeng.testnew.ViewDragHelperTest.ViewDrawHelperActivity;
import com.fanyafeng.testnew.ViewPagerTest.ViewPagerActivity;
import com.fanyafeng.testnew.blurImage.BlurActivity;
import com.fanyafeng.testnew.okhttp.OkHttpActivity;
import com.fanyafeng.testnew.playvideo.PlayLocalActivity;
import com.fanyafeng.testnew.result.OneActivity;
import com.fanyafeng.testnew.testactivitytranslate.FromActivity;
import com.fanyafeng.testnew.testdraw.TestCircleActivity;
import com.fanyafeng.testnew.testfragmentdialog.FloatDialogActivity;
import com.fanyafeng.testnew.testfragmentdialog.FragmentDialogActivity;
import com.fanyafeng.testnew.testnotification.TestNotificationActivity;
import com.fanyafeng.testnew.testobjectanimator.TestObjectAnimatorActivity;
import com.fanyafeng.testnew.testswitch.TestSwitchActivity;
import com.fanyafeng.testnew.TweenTest.TweenTestActivity;
import com.fanyafeng.testnew.picselector.PictureSelectorActivity;
import com.fanyafeng.testnew.recycleview.RecycleViewActivity;
import com.fanyafeng.testnew.testFloatingActionButton.TestFloatingActionButtonActivity;
import com.fanyafeng.testnew.testdamp.TestDampActivity;
import com.fanyafeng.testnew.testfragmenttranslate.MainFragmentActivity;
import com.fanyafeng.testnew.testlayout.TestLayoutActivity;
import com.fanyafeng.testnew.testnavigation.TestNavicationActivity;
import com.fanyafeng.testnew.testslide.TestSlideActivity;
import com.fanyafeng.testnew.testtaskActivity.TaskMainActivity;
import com.fanyafeng.testnew.testvalueanimator.TestValueAnimatorActivity;
import com.fanyafeng.testnew.testviewpagertablayout.TabMainActivity;
import com.fanyafeng.testnew.testwheel.WheelUpActivity;
import com.fanyafeng.testnew.util.MyUtils;
import com.fanyafeng.testnew.webjs.WebviewActivity;
import com.fanyafeng.testnew.wechatrotate.WechatRotateActivity;
import com.fanyafeng.testnew.zhongkuohao.LeftActivity;

public class TestToolbarActivity extends ABaseActivity {
    private Button test_toobar_text;
    private Button btn_fragment_nest;
    private Button btn_float_dialog;
    private Button btn_okhttp;
    private Button btn_bottom_sheets,btn_webview;

    private TextView test_html;
    private TextView tv_stroke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_test_toolbar);
        super.initView();
        isSetNavigationIcon = false;
        title = "测试Html";
//        centertitle="标题";
//        subtitle = "测试Toolbar";
        test_toobar_text = (Button) findViewById(R.id.test_toobar_text);
        test_toobar_text.setOnClickListener(this);
        btn_fragment_nest = (Button) findViewById(R.id.btn_fragment_nest);
        btn_fragment_nest.setOnClickListener(this);
        btn_float_dialog = (Button) findViewById(R.id.btn_float_dialog);
        btn_float_dialog.setOnClickListener(this);
        btn_okhttp = (Button) findViewById(R.id.btn_okhttp);
        btn_okhttp.setOnClickListener(this);
        btn_bottom_sheets = (Button) findViewById(R.id.btn_bottom_sheets);
        btn_bottom_sheets.setOnClickListener(this);
        btn_webview = (Button) findViewById(R.id.btn_webview);
        btn_webview.setOnClickListener(this);







        findViewById(R.id.btn_autopager).setOnClickListener(this);
findViewById(R.id.btn_database).setOnClickListener(this);

        test_html = (TextView) findViewById(R.id.test_html);
//        test_html.setCompoundDrawables();
        test_html.setText(
                Html.fromHtml(500 + "<big><font size=\"600\">"
                        + "元" + "</font></big>")
        );
        tv_stroke = (TextView) findViewById(R.id.tv_stroke);
    }

    @Override
    protected void initData() {
        addLayout();
        TextPaint textPaint = tv_stroke.getPaint();
        textPaint.setStrokeWidth(3);
        textPaint.setStyle(Paint.Style.STROKE);
        tv_stroke.setTextColor(getResources().getColor(R.color.main_blue_light));
        tv_stroke.setGravity(tv_stroke.getGravity());

//        Message

//        test_toobar_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG", "onClickListener方法周期");
//            }
//        });

        test_toobar_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouchListener方法周期");
                return false;
            }
        });

        test_toobar_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("TAG", "onLongClickeListener方法周期");
                return false;
            }
        });

        test_toobar_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("TAG", "OnKeyLisenerListener方法周期");
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.test_toobar_text:
                startActivity(new Intent(this, PermissionInActivity.class));
                Log.d("TAG", "onClickListener方法周期");
                break;
            case R.id.btn_fragment_nest:
                startActivity(new Intent(this, FragmentNestActivity.class));
                break;
            case R.id.btn_float_dialog:
                startActivity(new Intent(this, FloatDialogActivity.class));
                break;
            case R.id.btn_okhttp:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
            case R.id.btn_bottom_sheets:
                startActivity(new Intent(this, BottomSheetsActivity.class));

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClass(this, TestToolbarActivity.class);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                this.getApplication().startActivity(intent);


                break;
            case R.id.btn_autopager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.btn_webview:
                startActivity(new Intent(this, WebviewActivity.class));
                break;
            case R.id.btn_database:
                startActivity(new Intent(this, SQLOperateActivity.class));
                break;

        }
    }

    private void addLayout() {
        try {
            LinearLayout test_layout = (LinearLayout) findViewById(R.id.test_layout);
            for (int i = 0; i < 4; i++) {
                final int myId = i;
                View view = LayoutInflater.from(this).inflate(R.layout.item_add_layout, null);
                TextView textVie = (TextView) view.findViewById(R.id.id_add_text1);
                textVie.setText(i + "");
                ImageView iv_test_click = (ImageView) view.findViewById(R.id.iv_test_click);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.first_back);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                iv_test_click.setImageBitmap(MyUtils.getRoundAngleImage(bitmap, 60, false));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TestToolbarActivity.this, "现在点击的这是第" + myId, Toast.LENGTH_SHORT).show();
                    }
                });
                LinearLayout layout = new LinearLayout(this);
                layout.setWeightSum(0.9f);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                test_layout.addView(view, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abase, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.test_01:
                Intent intent = new Intent(this, TestLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.test_02:
                Intent intent2 = new Intent(this, RecycleViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.test_03:
                Intent intent3 = new Intent(this, TaskMainActivity.class);
                startActivity(intent3);
                break;
            case R.id.test_04:
                Intent intent4 = new Intent(this, TabMainActivity.class);
                startActivity(intent4);
                break;
            case R.id.test_05:
                Intent intent5 = new Intent(this, TweenTestActivity.class);
                startActivity(intent5);
                break;
            case R.id.test_06:
                Intent intent6 = new Intent(this, TestFrameActivity.class);
                startActivity(intent6);
                break;
            case R.id.test_07:
                Intent intent7 = new Intent(this, TestDampActivity.class);
                startActivity(intent7);
                break;
            case R.id.test_08:
                Intent intent8 = new Intent(this, TestSlideActivity.class);
                startActivity(intent8);
                break;
            case R.id.test_09:
                Intent intent9 = new Intent(this, WheelUpActivity.class);
                startActivity(intent9);
                break;
            case R.id.test_10:
                Intent intent10 = new Intent(this, TestFloatingActionButtonActivity.class);
                startActivity(intent10);
                break;
            case R.id.test_11:
                Intent intent11 = new Intent(this, PictureSelectorActivity.class);
                startActivity(intent11);
                break;
            case R.id.test_12:
                Intent intent12 = new Intent(this, MainFragmentActivity.class);
                startActivity(intent12);
                break;
            case R.id.test_001:
                Intent intent001 = new Intent(this, TestNavicationActivity.class);
                startActivity(intent001);
                break;
            case R.id.test_002:
                Intent intent002 = new Intent(this, TestSwitchActivity.class);
                startActivity(intent002);
                break;
            case R.id.test_003:
                Intent intent003 = new Intent(this, FragmentDialogActivity.class);
                startActivity(intent003);
                break;
            case R.id.test_004:
                Intent intent004 = new Intent(this, TestValueAnimatorActivity.class);
                startActivity(intent004);
                break;
            case R.id.test_005:
                Intent intent005 = new Intent(this, FromActivity.class);
                startActivity(intent005);
                break;
            case R.id.test_006:
                Intent intent006 = new Intent(this, TestObjectAnimatorActivity.class);
                startActivity(intent006);
                break;
            case R.id.test_007:
                Intent intent007 = new Intent(this, TestNotificationActivity.class);
                startActivity(intent007);
                break;
            case R.id.test_008:
                Intent intent008 = new Intent(this, TestCircleActivity.class);
                startActivity(intent008);
                break;
            case R.id.test_009:
                Intent intent009 = new Intent(this, OneActivity.class);
                startActivity(intent009);
                break;
            case R.id.test_010:
                Intent intent010 = new Intent(this, LeftActivity.class);
                startActivity(intent010);
                break;
            case R.id.test_011:
                Intent intent011 = new Intent(this, PlayLocalActivity.class);
                startActivity(intent011);
                break;
            case R.id.test_1001:
                Intent intent012 = new Intent(this, BlurActivity.class);
                startActivity(intent012);
                break;
            case R.id.test_1002:
                Intent intent013 = new Intent(this, MatrixActivity.class);
                startActivity(intent013);
                break;
            case R.id.test_1003:
                Intent intent014 = new Intent(this, MovementActivity.class);
                startActivity(intent014);
                break;
            case R.id.test_1004:
                startActivity(new Intent(this, StackActivity.class));
                break;
            case R.id.test_1005:
                startActivity(new Intent(this, QQBrowserActivity.class));
                break;
            case R.id.test_1006:
                startActivity(new Intent(this, QQBrowserImageActivity.class));
                break;
            case R.id.test_1007:
                startActivity(new Intent(this, WechatRotateActivity.class));
                break;
            case R.id.test_1008:
                startActivity(new Intent(this, IOStreamActivity.class));
                break;
            case R.id.test_1009:
                startActivity(new Intent(this, ViewDrawHelperActivity.class));
                break;
            case R.id.test_1010:
                startActivity(new Intent(this, LinearDragActivity.class));
                break;
            case R.id.test_1011:
                startActivity(new Intent(this, FrescoSampleActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
