package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.group.MyApplication;
import com.example.group.R;
import com.example.group.SearchViewActivity;
import com.example.group.ShoppingChannelActivity;
import com.example.group.TabLayoutActivity;
import com.example.group.TabLayoutActivity1;
import com.example.group.TabLayoutActivity2;
import com.example.group.TabLayoutActivity3;
import com.example.group.TabLayoutActivity4;
import com.example.group.adapter.GoodsAdapter;
import com.example.group.adapter.RecyclerGridAdapter;
import com.example.group.adapter.RecyclerStaggeredAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.bean.GoodsInfo1;
import com.example.group.constant.ImageList;
import com.example.group.database.GoodsDBHelper;
import com.example.group.util.Utils;
import com.example.group.widget.BannerPager;
import com.example.group.widget.SpacesItemDecoration;

import java.util.ArrayList;

public class TabFirstFragment extends Fragment implements BannerPager.BannerClickListener,View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "TabFirstFragment";
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象
    private TextView tv_pager;
    private GestureDetector mGestureDetector;
    private ArrayList<GoodsInfo1> mGoodsArray; // 声明一个商品信息队列

    private GridView gv_channel; // 声明一个网格视图对象
    private GoodsDBHelper mGoodsHelper; // 声明一个商品数据库的帮助器对象
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_tab_first.xml生成视图对象

        mView = inflater.inflate(R.layout.activity_banner_pager, container, false);
        // 根据碎片标签栏传来的参数拼接文本字符串
        Intent intent = new Intent(mContext, SearchViewActivity.class);
        TextView tv_first = mView.findViewById(R.id.tv_first);

        tv_pager = mView.findViewById(R.id.tv_pager);
        // 从布局文件中获取名叫banner_pager的横幅轮播条
        BannerPager banner = mView.findViewById(R.id.banner_pager);
        // 获取横幅轮播条的布局参数
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) banner.getLayoutParams();
        params.height = (int) (Utils.getScreenWidth(mContext) * 250f / 640f);
        // 设置横幅轮播条的布局参数
        banner.setLayoutParams(params);
        // 设置横幅轮播条的广告图片队列
        banner.setImage(ImageList.getDefault());
        // 设置横幅轮播条的广告点击监听器
        banner.setOnBannerListener(this);
        // 开始广告图片的轮播滚动
        banner.start();
        initRecyclerStaggered(); // 初始化瀑布流布局的循环视图


        initRecyclerGrid(); // 初始化网格布局的循环视图
       // showGoods();

        return mView;
    }
    // 获取列表项的个数
    public int getCount() {
        return mGoodsArray.size();
    }

    // 获取列表项的数据
    public Object getItem(int arg0) {
        return mGoodsArray.get(arg0);
    }

    // 获取列表项的编号
    public long getItemId(int arg0) {
        return arg0;
    }

    // 获取指定位置的列表项视图
    public View getView(final int position, View convertView, ViewGroup parent) {
        GoodsAdapter.ViewHolder holder;
        if (convertView == null) { // 转换视图为空
            holder = new GoodsAdapter.ViewHolder(); // 创建一个新的视图持有者
            // 根据布局文件item_goods.xml生成转换视图对象
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods, null);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.iv_thumb = convertView.findViewById(R.id.iv_thumb);
            holder.tv_price = convertView.findViewById(R.id.tv_price);
            holder.btn_add = convertView.findViewById(R.id.btn_add);
            // 将视图持有者保存到转换视图当中
            convertView.setTag(holder);
        } else { // 转换视图非空
            // 从转换视图中获取之前保存的视图持有者
            holder = (GoodsAdapter.ViewHolder) convertView.getTag();
        }
        final GoodsInfo1 info = mGoodsArray.get(position);
        Log.d(TAG, "info.name:"+info.name);
        holder.tv_name.setText(info.name); // 显示商品的名称
        holder.iv_thumb.setImageBitmap(MyApplication.getInstance().mIconMap.get(info.rowid)); // 显示商品的图片
        holder.tv_price.setText("" + (int) info.price); // 显示商品的价格
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 触发加入购物车监听器的添加动作
                mAddCartListener.addToCart(info.rowid);
                Toast.makeText(mContext,
                        "已添加一部" + info.name + "到购物车", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    // 定义一个视图持有者，以便重用列表项的视图资源
    public final class ViewHolder {
        public TextView tv_name; // 声明商品名称的文本视图对象
        public ImageView iv_thumb; // 声明商品图片的图像视图对象
        public TextView tv_price; // 声明商品价格的文本视图对象
        public Button btn_add; // 声明加入购物车的按钮对象
    }
    public void GoodsAdapter(Context context, ArrayList<GoodsInfo1> goods_list, GoodsAdapter.addCartListener listener) {
        mContext = context;
        mGoodsArray = goods_list;
        mAddCartListener = listener;
    }
//    private void showGoods() {
//        // 查询商品数据库中的所有商品记录
//        ArrayList<GoodsInfo1> goodsArray = mGoodsHelper.query("1=1");
//        // 构建商场中商品网格的适配器对象
//        GoodsAdapter adapter = new GoodsAdapter(mContext, goodsArray, (GoodsAdapter.addCartListener) this);
//        // 给gv_channel设置商品网格适配器
//        gv_channel.setAdapter(adapter);
//        // 给gv_channel设置网格项点击监听器
//        gv_channel.setOnItemClickListener(adapter);
//    }
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
    private void initRecyclerStaggered() {
        // 从布局文件中获取名叫rv_staggered的循环视图
        RecyclerView rv_staggered = mView.findViewById(R.id.rv_staggered);
        // 创建一个垂直方向的瀑布流布局管理器
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                3, RecyclerView.VERTICAL);
        // 设置循环视图的布局管理器
        rv_staggered.setLayoutManager(manager);
        // 构建一个服装列表的瀑布流适配器
        RecyclerStaggeredAdapter adapter = new RecyclerStaggeredAdapter(mContext, GoodsInfo.getDefaultStag());
        // 设置瀑布流列表的点击监听器
        adapter.setOnItemClickListener(adapter);
        // 设置瀑布流列表的长按监听器
        adapter.setOnItemLongClickListener(adapter);
        // 给rv_staggered设置服装瀑布流适配器
        rv_staggered.setAdapter(adapter);
        // 设置rv_staggered的默认动画效果
        rv_staggered.setItemAnimator(new DefaultItemAnimator());
        // 给rv_staggered添加列表项之间的空白装饰
        rv_staggered.addItemDecoration(new SpacesItemDecoration(3));
    }
    // 一旦点击了广告图，就回调监听器的onBannerClick方法
    public void onBannerClick(int position) {

//        Log.d(TAG,mGoodsArray==null?"nullaaaa":"nonulaaal");
//       // GoodsInfo1 info = mGoodsArray.get(position);
//        if(position==0){
//        // 携带商品编号跳转到商品详情页面
//        Intent intent = new Intent(mContext, TabLayoutActivity.class);
//        startActivity(intent);
//     //   intent.putExtra("goods_id", info.rowid);
//        mContext.startActivity(intent);}
//        else  if(position==1){
//            // 携带商品编号跳转到商品详情页面
//            Intent intent = new Intent(mContext, TabLayoutActivity2.class);
//            startActivity(intent);
//            //   intent.putExtra("goods_id", info.rowid);
//            mContext.startActivity(intent);}
//        else  if(position==2){
//            // 携带商品编号跳转到商品详情页面
//            Intent intent = new Intent(mContext, TabLayoutActivity4.class);
//            startActivity(intent);
//            //   intent.putExtra("goods_id", info.rowid);
//            mContext.startActivity(intent);}
//        else  if(position==3){
//            // 携带商品编号跳转到商品详情页面
//            Intent intent = new Intent(mContext, TabLayoutActivity3.class);
//            startActivity(intent);
//            //   intent.putExtra("goods_id", info.rowid);
//            mContext.startActivity(intent);}
//        else  if(position==4){
//            // 携带商品编号跳转到商品详情页面
//            Intent intent = new Intent(mContext, TabLayoutActivity1.class);
//            startActivity(intent);
//            //   intent.putExtra("goods_id", info.rowid);
//            mContext.startActivity(intent);}
//        //String desc = String.format("您点击了第%d张图片", position + 1);
//        //tv_pager.setText(desc);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsInfo1 info = mGoodsArray.get(position);
        // 携带商品编号跳转到商品详情页面
        Intent intent = new Intent(mContext, ShoppingChannelActivity.class);
        intent.putExtra("goods_id", info.rowid);
        mContext.startActivity(intent);
    }
    private GoodsAdapter.addCartListener mAddCartListener;

    // 定义一个加入购物车的监听器接口
    public interface addCartListener {
        void addToCart(long goods_id);  // 在商品加入购物车时触发
    }
    public void onClick(View v) {



    }
}
