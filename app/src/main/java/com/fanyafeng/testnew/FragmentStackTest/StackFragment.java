package com.fanyafeng.testnew.FragmentStackTest;

import android.app.Activity;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fanyafeng.testnew.BaseFragment;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.MyUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link StackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StackFragment extends BaseFragment implements GestureDetector.OnGestureListener, View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_exit_stack;
    private LinearLayout layout_father_stack;
    private GestureDetector gestureDetector;
    private View view;
    private float moveLength;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StackFragment newInstance(String param1, String param2) {
        StackFragment fragment = new StackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StackFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gestureDetector = new GestureDetector(getActivity(), this);
        view = inflater.inflate(R.layout.fragment_stack, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        btn_exit_stack = (Button) getActivity().findViewById(R.id.btn_exit_stack);
        btn_exit_stack.setOnClickListener(this);
        layout_father_stack = (LinearLayout) getActivity().findViewById(R.id.layout_father_stack);
        layout_father_stack.setOnTouchListener(this);
    }

    private void initData() {
//        调用另一个fragment中的相应方法
//        StackOneFragment stackOneFragment = (StackOneFragment) getFragmentManager().findFragmentByTag("one");
//        stackOneFragment.
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_exit_stack:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP && moveLength < MyUtils.getScreenWidth(getActivity()) / 2) {
            ViewCompat.setTranslationX(view, 0);
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        System.out.println("用户滑动的距离length：" + (e2.getX() - e1.getX()));
        if (e1.getAction() == MotionEvent.ACTION_UP)
            System.out.println("用户抬起手指e1");
        if (e2.getAction() == MotionEvent.ACTION_UP)
            System.out.println("用户抬起手指e2");
        moveLength = e2.getX() - e1.getX();

        ViewCompat.setTranslationX(view, moveLength / 2);
        if (moveLength > MyUtils.getScreenWidth(getActivity()) / 2) {
            getFragmentManager().popBackStack();
        }
//        else if (moveLength < MyUtils.getScreenWidth(getActivity()) / 2 && e2.getAction() == MotionEvent.ACTION_UP) {
//            ViewCompat.setTranslationX(view, 0);
//        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        ViewCompat.setTranslationX(view, (e2.getX() - e1.getX()) / 2);
        if (moveLength > MyUtils.getScreenWidth(getActivity()) / 4) {
            getFragmentManager().popBackStack();
        }
        return false;
    }


}
