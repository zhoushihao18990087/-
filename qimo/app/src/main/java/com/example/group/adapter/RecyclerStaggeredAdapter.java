package com.example.group.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.group.R;
import com.example.group.TabLayoutActivity;
import com.example.group.TabLayoutActivity1;
import com.example.group.TabLayoutActivity2;
import com.example.group.TabLayoutActivity3;
import com.example.group.TabLayoutActivity4;
import com.example.group.TabLayoutActivity5;
import com.example.group.TabLayoutActivity6;
import com.example.group.TabLayoutActivity7;
import com.example.group.TabLayoutActivity8;
import com.example.group.TabLayoutActivity9;
import com.example.group.bean.GoodsInfo;
import com.example.group.widget.RecyclerExtras.OnItemClickListener;
import com.example.group.widget.RecyclerExtras.OnItemLongClickListener;

public class RecyclerStaggeredAdapter extends RecyclerView.Adapter<ViewHolder> implements OnItemClickListener, OnItemLongClickListener{
    private final static String TAG = "RecyclerStaggeredAdapter";
    private Context mContext; // 声明一个上下文对象
    private ArrayList<GoodsInfo> mGoodsArray;

    public RecyclerStaggeredAdapter(Context context, ArrayList<GoodsInfo> goodsArray) {
        mContext = context;
        mGoodsArray = goodsArray;
    }

    // 获取列表项的个数
    public int getItemCount() {
        return mGoodsArray.size();
    }

    // 创建列表项的视图持有者
    public ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
        // 根据布局文件item_staggered.xml生成视图对象
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_staggered, vg, false);
        return new ItemHolder(v);
    }

    // 绑定列表项的视图持有者
    public void onBindViewHolder(ViewHolder vh, final int position) {
        ItemHolder holder = (ItemHolder) vh;
        holder.iv_pic.setImageResource(mGoodsArray.get(position).pic_id);
        holder.tv_title.setText(mGoodsArray.get(position).title);
//        ViewGroup.LayoutParams params = holder.ll_item.getLayoutParams();
//        params.height = (int) Math.round(300 * Math.random());
//        if (params.height < 60) {
//            params.height = 60;
//        }
//        // 很奇怪，setLayoutParams对瀑布流网格不起作用，只能用setHeight
//        holder.tv_title.setHeight(params.height);

        // 列表项的点击事件需要自己实现
        holder.ll_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        // 列表项的长按事件需要自己实现
        holder.ll_item.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(v, position);
                }
                return true;
            }
        });
    }

    // 获取列表项的类型
    public int getItemViewType(int position) {
        return 0;
    }

    // 获取列表项的编号
    public long getItemId(int position) {
        return position;
    }

    // 定义列表项的视图持有者
    public class ItemHolder extends RecyclerView.ViewHolder {
        public LinearLayout ll_item; // 声明列表项的线性布局
        public ImageView iv_pic; // 声明列表项图标的图像视图
        public TextView tv_title; // 声明列表项标题的文本视图

        public ItemHolder(View v) {
            super(v);
            ll_item = v.findViewById(R.id.ll_item);
            iv_pic = v.findViewById(R.id.iv_pic);
            tv_title = v.findViewById(R.id.tv_title);
        }
    }

    // 声明列表项的点击监听器对象
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 声明列表项的长按监听器对象
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    // 处理列表项的点击事件
    public void onItemClick(View view, int position) {

//            if(position ==0)
//            { Intent intent = new Intent(mContext, TabLayoutActivity.class);
//                mContext.startActivity(intent);
//            }
//            else if(position ==1)
//            { Intent intent1 = new Intent(mContext, TabLayoutActivity1.class);
//                mContext.startActivity(intent1);
//
//            }
//            else if(position ==2)
//            { Intent intent = new Intent(mContext, TabLayoutActivity2.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==3)
//            { Intent intent = new Intent(mContext, TabLayoutActivity3.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==4)
//            { Intent intent = new Intent(mContext, TabLayoutActivity4.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==5)
//            { Intent intent = new Intent(mContext, TabLayoutActivity5.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==6)
//            { Intent intent = new Intent(mContext, TabLayoutActivity6.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==7)
//            { Intent intent = new Intent(mContext, TabLayoutActivity7.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==8)
//            { Intent intent = new Intent(mContext, TabLayoutActivity8.class);
//                mContext.startActivity(intent);
//
//            }
//            else if(position ==9)
//            { Intent intent = new Intent(mContext, TabLayoutActivity9.class);
//                mContext.startActivity(intent);
//
//            }
        }


    // 处理列表项的长按事件
    public void onItemLongClick(View view, int position) {
        String desc = String.format("您长按了第%d项，商品名称是%s", position + 1,
                mGoodsArray.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }
}
