package com.fanyafeng.testnew.testnavigation;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;

import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testFloatingActionButton.SnackbarUtil;
import com.fanyafeng.testnew.testnavigation.refreshAndLoad.OnRcvScrollListener;
import com.fanyafeng.testnew.testnavigation.swipe2refresh.SwipeRefreshLayoutBottom;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecyclerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MyRecyclerViewAdapter.OnItemClickListener, MyStaggeredViewAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayoutBottom swipeRefreshLayoutBottom;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecyclerViewHolder myRecyclerViewHolder;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MyStaggeredViewAdapter myStaggeredViewAdapter;

    private static final int VERTICAL_LIST = 0;
    private static final int HORIZONTAL_LIST = 1;
    private static final int VERTICAL_GRID = 2;
    private static final int HORIZONTAL_GRID = 3;
    private static final int STAGGERED_GRID = 4;

    private static final int SPAN_COUNT = 2;
    private int flag = 0;
    private int lastVisibleItem;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerFragment() {
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
        view = inflater.inflate(R.layout.fragment_recycler, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayoutBottom=(SwipeRefreshLayoutBottom)view.findViewById(R.id.swiperefreshlayoutbottom);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        flag = getArguments().getInt("flag");
        configRecyclerView();
        swipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
//        是否允许用户进行下拉刷新操作
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayoutBottom.setColorSchemeResources(R.color.main_blue_light,R.color.main_blue_dark);
        swipeRefreshLayoutBottom.setEnabled(true);
        swipeRefreshLayoutBottom.setOnRefreshListener(new SwipeRefreshLayoutBottom.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新时模拟数据的变化
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int temp = (int) (Math.random() * 10);
                        if (flag != STAGGERED_GRID) {
                            myRecyclerViewAdapter.stringList.add(0, "new" + temp);
                            myRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            myStaggeredViewAdapter.mDates.add(0, "new" + temp);
                            myStaggeredViewAdapter.mHeights.add(0, (int) (Math.random() * 300) + 200);
                            myStaggeredViewAdapter.notifyDataSetChanged();
                        }
                        swipeRefreshLayoutBottom.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void scrollToBottom() {
//        版本比较高，暂时不支持
//        if (Build.VERSION.SDK_INT > 22) {
//            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                }
//            });
//        } else {
//            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                }
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                }
//            });
//        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        recyclerView.setOnScrollListener(new OnRcvScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int temp = (int) (Math.random() * 10);
                        if (flag != STAGGERED_GRID) {
                            myRecyclerViewAdapter.stringList.add(0, "new" + temp);
                            myRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            myStaggeredViewAdapter.mDates.add(0, "new" + temp);
                            myStaggeredViewAdapter.mHeights.add(0, (int) (Math.random() * 300) + 200);
                            myStaggeredViewAdapter.notifyDataSetChanged();
                        }
                    }
                }, 1000);
            }
        });
    }

    private void configRecyclerView() {
        switch (flag) {
            case VERTICAL_LIST:
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                break;
            case HORIZONTAL_LIST:
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case VERTICAL_GRID:
                layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
                break;
            case HORIZONTAL_GRID:
                layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, GridLayoutManager.HORIZONTAL, false);
                break;
            case STAGGERED_GRID:
                layoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
                break;
        }

        if (flag != STAGGERED_GRID) {
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity());
            myRecyclerViewAdapter.setOnItemClickListener(this);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(myRecyclerViewAdapter);
        } else {
            myStaggeredViewAdapter = new MyStaggeredViewAdapter(getActivity());
            myStaggeredViewAdapter.setOnItemClickListener(this);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(myStaggeredViewAdapter);
        }

        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onRefresh() {
        // 刷新时模拟数据的变化
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int temp = (int) (Math.random() * 10);
                if (flag != STAGGERED_GRID) {
                    myRecyclerViewAdapter.stringList.add(0, "new" + temp);
                    myRecyclerViewAdapter.notifyDataSetChanged();
                } else {
                    myStaggeredViewAdapter.mDates.add(0, "new" + temp);
                    myStaggeredViewAdapter.mHeights.add(0, (int) (Math.random() * 300) + 200);
                    myStaggeredViewAdapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }



    @Override
    public void onItemClick(View view, int position) {
        SnackbarUtil.show(view, position + "", 0);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        SnackbarUtil.show(view, position + "", 0);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

//    private void scrollToBottom() {
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
//                        Animation alphaanim = AnimationUtils.loadAnimation(ShangXiangHistoryActivity.this, R.anim.alphaanim);
//                        if (!footerView.isShown()) {
//                            footerView.setVisibility(View.VISIBLE);
//                            footerView.setAnimation(alphaanim);
//                            alphaanim.start();
//                        }
//                    }
////                    if (view.getFirstVisiblePosition() == 0) {
////                        Toast.makeText(ShangXiangHistoryActivity.this, "已经上滑到顶部", Toast.LENGTH_SHORT).show();
////                    }
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//            }
//        });
//    }

}
