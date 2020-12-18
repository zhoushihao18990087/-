package com.example.group.fragment;

import com.example.group.R;
import com.example.group.SearchViewActivity;
import com.example.group.adapter.RecyclerCombineAdapter;
import com.example.group.adapter.RecyclerGridAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.shoppingcart;
import com.example.group.widget.SpacesItemDecoration;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabSecondFragment extends Fragment {
    private static final String TAG = "TabSecondFragment";
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_tab_second.xml生成视图对象
        mView = inflater.inflate(R.layout.activity_recycler_combine, container, false);
        // 根据碎片标签栏传来的参数拼接文本字符串
        initRecyclerCombine(); // 初始化合并网格布局的循环视图
        initRecyclerGrid(); // 初始化网格布局的循环视图
        return mView;
    }
    private void initRecyclerGrid() {
        // 从布局文件中获取名叫rv_grid的循环视图
        RecyclerView rv_grid = mView.findViewById(R.id.rv_grid);
        // 创建一个垂直方向的网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(mContext, 5);
        // 设置循环视图的布局管理器
        rv_grid.setLayoutManager(manager);
        // 构建一个市场列表的网格适配器
        RecyclerGridAdapter adapter = new RecyclerGridAdapter(mContext, GoodsInfo.getDefaultGrid());
        // 设置网格列表的点击监听器
        adapter.setOnItemClickListener(adapter);
        // 设置网格列表的长按监听器
        adapter.setOnItemLongClickListener(adapter);
        // 给rv_grid设置市场网格适配器
        rv_grid.setAdapter(adapter);
        // 设置rv_grid的默认动画效果
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        // 给rv_grid添加列表项之间的空白装饰
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }
    // 初始化合并网格布局的循环视图
    private void initRecyclerCombine() {
        // 从布局文件中获取名叫rv_combine的循环视图

        // 创建一个四列的网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        // 设置网格布局管理器的占位规则
        // 以下占位规则的意思是：第一项和第二项占两列，其它项占一列；
        // 如果网格的列数为四，那么第一项和第二项平分第一行，第二行开始每行有四项。
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 1) { // 为第一项或者第二项
                    return 2; // 占据两列
                } else { // 为其它项
                    return 1; // 占据一列
                }
            }
        });


    }
}
