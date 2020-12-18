package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import com.example.group.MainActivity;
import com.example.group.MyApplication;
import com.example.group.R;
import com.example.group.SearchViewActivity;
import com.example.group.ShoppingCartProActivity;
import com.example.group.ShoppingDetailActivity;
import com.example.group.adapter.CartAdapter;
import com.example.group.bean.CartInfo;
import com.example.group.bean.GoodsInfo1;
import com.example.group.database.CartDBHelper;
import com.example.group.database.GoodsDBHelper;
import com.example.group.util.SharedUtil;

import java.util.ArrayList;

public class TabThirdFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "TabThirdFragment";
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    private TextView tv_total_price;
    private Group gp_content;
    private Group gp_empty;
    private int mCount; // 购物车中的商品数量
    private GoodsDBHelper mGoodsHelper; // 声明一个商品数据库的帮助器对象
    private CartDBHelper mCartHelper; // 声明一个购物车数据库的帮助器对象

    private ListView lv_cart; // 声明一个列表视图对象
    private CartInfo mCurrentGood;  // 声明当前的商品对象
    private View mCurrentView;  // 声明一个当前视图的对象
    private Handler mHandler = new Handler();  // 声明一个处理器对象，用于长按菜单

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_tab_third.xml生成视图对象
        mView = inflater.inflate(R.layout.shoppingcartpro, container, false);
        // 根据碎片标签栏传来的参数拼接文本字符串

        tv_total_price = mView.findViewById(R.id.tv_total_price);
        gp_content = mView.findViewById(R.id.gp_content);
        gp_empty = mView.findViewById(R.id.gp_empty);
        mView.findViewById(R.id.btn_shopping_channel).setOnClickListener(this);


        // 从布局视图中获取名叫lv_cart的列表视图


        // 从布局视图中获取名叫lv_cart的列表视图
        lv_cart = mView.findViewById(R.id.lv_cart);
        return mView;
    }




    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_shopping_channel) { // 点击了“商场”按钮
            // 跳转到手机商场页面
            Intent intent = new Intent(mContext, ShoppingCartProActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_settle) { // 点击了“结算”按钮
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("结算商品");
            builder.setMessage("客官抱歉，支付功能尚未开通，请下次再来");
            builder.setPositiveButton("我知道了", null);
            builder.create().show();
        }
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_cart.xml中构建菜单界面布局
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_shopping) { // 点击了菜单项“去商场购物”
            // 跳转到商场页面
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_clear) { // 点击了菜单项“清空购物车”
            // 清空购物车数据库
            mCartHelper.deleteAll();
            // 把最新的商品数量写入共享参数
            SharedUtil.getIntance(mContext).writeShared("count", "0");
            // 显示最新的商品数量

          //  Toast.makeText(this, "购物车已清空", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_return) { // 点击了菜单项“返回”
            ((AppCompatActivity)getActivity()).finish();
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 从menu_goods.xml中构建菜单界面布局
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.menu_goods, menu);
    }




    // 跳转到商品详情页面
    private void goDetail(long rowid) {
        Intent intent = new Intent(mContext, ShoppingDetailActivity.class);
        intent.putExtra("goods_id", rowid);
        startActivity(intent);
    }











}