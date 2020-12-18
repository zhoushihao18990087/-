package com.example.group;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group.adapter.RecyclerCombineAdapter;
import com.example.group.bean.GoodsInfo;

public class RecyclerCombineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_combine);
        initRecyclerCombine(); // 初始化合并网格布局的循环视图
    }

    // 初始化合并网格布局的循环视图
    private void initRecyclerCombine() {
        // 从布局文件中获取名叫rv_combine的循环视图

        // 创建一个四列的网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(this, 4);
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
        // 设置循环视图的布局管理器

    }
}