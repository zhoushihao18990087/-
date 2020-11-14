package com.example.cart.bean;

import com.example.cart.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "dog", "cat", "cloud", "flower", "yanzi", "beloved","moon","river"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "狗狗 一只20",
            "猫猫 一只2 ",
            "我在天边贩卖云彩",
            "花儿为你开",
            "小燕子 穿花衣",
            "我的挚爱",
            "只属于你的月亮",
            "大千世界 唯一河流"
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {20, 2, 999999, 222, 50, 1,888998,999999};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e, R.drawable.f,R.drawable.g
            ,R.drawable.h
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e, R.drawable.f,R.drawable.g,R.drawable.h
    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
