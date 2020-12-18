package com.example.eighthapplication.bean;

import com.example.eighthapplication.R;

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
            "Mate30", "爱国者", "红米9A", "索尼摄像机", "nova7", "欧达","打印机","iphone12"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "HuaweiMate30 5G手机官网官方旗舰店 正品旗舰店",
            "爱国者 aigo EROS H06便携无损音乐播放器 母带发烧硬解mp3",
            "红米9A 全网通4G手机 学生智能备用老年机 小米官方旗舰店官网",
            "sony/索尼 HDR-CX680 高清数码摄像机 5轴防抖30倍光学变焦 cx680",
            "Huawei/华为nova7 SE 5G手机官方旗舰店正品华为手机",
            "台湾欧达摄像机4K超高清12倍光变专业淘宝直播摄影DV数码家用旅游",
            "佳能黑白复印机a3激光打印机商用大型办公高速数码打印复印一体机",
            "【现货速发】Apple/苹果 iphone12 pro 全网通5G智能手机 官方授权苹果12pro "
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {6888, 3999, 2999, 2899, 2698, 2098,1099,2099};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.huawei_s, R.drawable.aigo_s, R.drawable.redmi_s,
            R.drawable.sony_s, R.drawable.nova_s, R.drawable.ordro_s,
            R.drawable.printer_s,R.drawable.iphone_s
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.huawei, R.drawable.aigo_s, R.drawable.redmi_s,
            R.drawable.sony, R.drawable.nova, R.drawable.ordro,
            R.drawable.printer,R.drawable.iphone
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
