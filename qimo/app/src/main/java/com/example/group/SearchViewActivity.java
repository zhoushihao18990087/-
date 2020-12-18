package com.example.group;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.group.adapter.RecyclerGridAdapter;
import com.example.group.adapter.RecyclerStaggeredAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.constant.ImageList;
import com.example.group.database.CartDBHelper;
import com.example.group.database.GoodsDBHelper;
import com.example.group.fragment.TabFirstFragment;
import com.example.group.fragment.TabSecondFragment;
import com.example.group.fragment.TabThirdFragment;
import com.example.group.util.DateUtil;
import com.example.group.util.MenuUtil;
import com.example.group.util.SharedUtil;
import com.example.group.util.Utils;
import com.example.group.widget.BannerPager;
import com.example.group.widget.SpacesItemDecoration;

import java.util.ArrayList;

public class SearchViewActivity extends AppCompatActivity  implements View.OnClickListener{
    private TabHost mTabHost;
    //main
   private TextView tv_count;
    private LinearLayout ll_channel;
    private int mCount; // 购物车中的商品数量
    private GoodsDBHelper mGoodsHelper; // 声明一个商品数据库的帮助器对象
    private CartDBHelper mCartHelper; // 声明一个购物车数据库的帮助器对象

    //main//
    private final static String TAG = "SearchViewActivity";
    private TextView tv_desc;
    private SearchView.SearchAutoComplete sac_key; // 声明一个搜索自动完成的编辑框对象
    private String[] hintArray = {"iphone", "iphone8", "iphone8 plus", "iphone7", "iphone7 plus"};
    private FragmentTabHost tabHost; // 声明一个碎片标签栏对象
    private Toolbar mToolBar;
    private TextView tv_pager;
    private ImageButton one, two,three;
    @SuppressLint("WrongViewCast")
    //
    private Bundle mBundle = new Bundle(); // 声明一个包裹对象
    private TabHost tab_host; // 声明一个标签栏对象
    private LinearLayout ll_first, ll_second, ll_third;
    private String FIRST_TAG = "first"; // 第一个标签的标识串
    private String SECOND_TAG = "second"; // 第二个标签的标识串
    private String THIRD_TAG = "third"; // 第三个标签的标识串
    private TextView tv_total_price;
    private Group gp_content;
    private Group gp_empty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        // 从布局文件中获取名叫tl_head的工具栏
        Toolbar tl_head = findViewById(R.id.tl_head);
        // 设置工具栏的标题文字
        tl_head.setTitle("手机商城");
        // 使用tl_head替换系统自带的ActionBar
        setSupportActionBar(tl_head);
        tv_desc = findViewById(R.id.tv_desc);


        Bundle bundle = new Bundle(); // 创建一个包裹对象
        bundle.putString("tag", TAG); // 往包裹中存入名叫tag的标记
        // 从布局文件中获取名叫tabhost的碎片标签栏
        tabHost = findViewById(android.R.id.tabhost);
        // 把实际的内容框架安装到碎片标签栏
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 往标签栏添加第一个标签，其中内容视图展示TabFirstFragment
        tabHost.addTab(getTabView(R.string.menu_first, R.drawable.tab_first_selector),
                TabFirstFragment.class, bundle);
        // 往标签栏添加第二个标签，其中内容视图展示TabSecondFragment
        tabHost.addTab(getTabView(R.string.menu_second, R.drawable.tab_second_selector),
                TabSecondFragment.class, bundle);
        // 往标签栏添加第三个标签，其中内容视图展示TabThirdFragment
        tabHost.addTab(getTabView(R.string.menu_third, R.drawable.tab_third_selector),
                TabThirdFragment.class, bundle);

        // 不显示各标签之间的分隔线
        tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mToolBar = (Toolbar) this.findViewById(R.id.tl_head);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Toast.makeText(SearchViewActivity.this,"Navogation Clicked",Toast.LENGTH_LONG).show();
            }
        });


        mToolBar.inflateMenu(R.menu.menu_search);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id =item.getItemId();
                if(id==R.id.action_settings){
                    Toast.makeText(SearchViewActivity.this,"action_settings Clicked",Toast.LENGTH_LONG).show();
                }


                return false;


            }
        });




        //main
        TextView tv_title = findViewById(R.id.tv_title);
        tv_count = findViewById(R.id.tv_count);
        ll_channel = findViewById(R.id.ll_channel);
        tv_total_price = findViewById(R.id.tv_total_price);
        gp_content = findViewById(R.id.gp_content);
        gp_empty = findViewById(R.id.gp_empty);


        //main//

    }
   //cart

    //cart

    private void initRecyclerStaggered() {
        // 从布局文件中获取名叫rv_staggered的循环视图
        RecyclerView rv_staggered = findViewById(R.id.rv_staggered);
        // 创建一个垂直方向的瀑布流布局管理器
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                3, RecyclerView.VERTICAL);
        // 设置循环视图的布局管理器
        rv_staggered.setLayoutManager(manager);
        // 构建一个服装列表的瀑布流适配器
        RecyclerStaggeredAdapter adapter = new RecyclerStaggeredAdapter(this, GoodsInfo.getDefaultStag());
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
    private TabHost.TabSpec getTabView(int textId, int imgId) {
        // 根据资源编号获得字符串对象
        String text = getResources().getString(textId);
        // 根据资源编号获得图形对象
        Drawable drawable = getResources().getDrawable(imgId);
        // 设置图形的四周边界。这里必须设置图片大小，否则无法显示图标
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        // 根据布局文件item_tabbar.xml生成标签按钮对象
        View item_tabbar = getLayoutInflater().inflate(R.layout.item_tabbar, null);
        TextView tv_item = item_tabbar.findViewById(R.id.tv_item_tabbar);
        tv_item.setText(text);
        // 在文字上方显示标签的图标
        tv_item.setCompoundDrawables(null, drawable, null, null);
        // 生成并返回该标签按钮对应的标签规格
        return tabHost.newTabSpec(text).setIndicator(item_tabbar);


    }
    private TabHost.TabSpec getNewTab(String spec, int label, int icon, Class<?> cls) {
        // 创建一个意图，并存入指定包裹
        Intent intent = new Intent(this, cls).putExtras(mBundle);
        // 生成并返回新的标签规格（包括内容意图、标签文字和标签图标）
        return tab_host.newTabSpec(spec).setContent(intent)
                .setIndicator(getString(label), getResources().getDrawable(icon));
    }
    private void changeContainerView(View v) {
        ll_first.setSelected(false); // 取消选中第一个标签
        ll_second.setSelected(false); // 取消选中第二个标签
        ll_third.setSelected(false); // 取消选中第三个标签
        v.setSelected(true); // 选中指定标签
        if (v == ll_first) {
            tab_host.setCurrentTabByTag(FIRST_TAG); // 设置当前标签为第一个标签
        } else if (v == ll_second) {
            tab_host.setCurrentTabByTag(SECOND_TAG); // 设置当前标签为第二个标签
        } else if (v == ll_third) {
            tab_host.setCurrentTabByTag(THIRD_TAG); // 设置当前标签为第三个标签
        }
    }
    // 根据菜单项初始化搜索框
    @SuppressLint("RestrictedApi")
    private void initSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        // 从菜单项中获取搜索框对象
        SearchView searchView = (SearchView) menuItem.getActionView();
        // 设置搜索框默认自动缩小为图标
        searchView.setIconifiedByDefault(getIntent().getBooleanExtra("collapse", true));
        // 设置是否显示搜索按钮。搜索按钮只显示一个箭头图标，Android暂不支持显示文本。
        // 查看Android源码，搜索按钮用的控件是ImageView，所以只能显示图标不能显示文字。
        searchView.setSubmitButtonEnabled(true);
        // 从系统服务中获取搜索管理器
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // 创建搜索结果页面的组件名称对象
        ComponentName cn = new ComponentName(this, SearchResultActvity.class);
        // 从结果页面注册的activity节点获取相关搜索信息，即searchable.xml定义的搜索控件
        SearchableInfo info = sm.getSearchableInfo(cn);
        if (info == null) {
            Log.d(TAG, "Fail to get SearchResultActvity.");
            return;
        }
        // 设置搜索框的可搜索信息
        searchView.setSearchableInfo(info);
        // 从搜索框中获取名叫search_src_text的自动完成编辑框
        sac_key = searchView.findViewById(R.id.search_src_text);
        // 设置自动完成编辑框的文本颜色
        sac_key.setTextColor(Color.BLACK);
        // 设置自动完成编辑框的提示文本颜色
        sac_key.setHintTextColor(Color.BLACK);
        // 给搜索框设置文本变化监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 搜索关键词完成输入
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 搜索关键词发生变化
            public boolean onQueryTextChange(String newText) {
                doSearch(newText);
                return true;
            }
        });
        Bundle bundle = new Bundle(); // 创建一个新包裹
        bundle.putString("hi", "hello"); // 往包裹中存放名叫hi的字符串
        // 设置搜索框的额外搜索数据
        searchView.setAppSearchData(bundle);
    }

    // 自动匹配相关的关键词列表
    private void doSearch(String text) {
        if (text.indexOf("i") == 0) {
            // 根据提示词数组构建一个数组适配器
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.search_list_auto, hintArray);
            // 设置自动完成编辑框的数组适配器
            sac_key.setAdapter(adapter);
            // 给自动完成编辑框设置列表项的点击监听器
            sac_key.setOnItemClickListener(new OnItemClickListener() {
                // 一旦点击关键词匹配列表中的某一项，就触发点击监听器的onItemClick方法
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    sac_key.setText(((TextView) view).getText());
                }
            });
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        MenuUtil.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_search.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // 初始化搜索框
        initSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // 点击了工具栏左边的返回箭头
            finish();
        } else if (id == R.id.menu_refresh) { // 点击了刷新图标
            tv_desc.setText("当前刷新时间: " + DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
            return true;

        }
        else if (id == R.id.menu_clear) { // 点击了退出菜单项
            mCartHelper.deleteAll();
            // 把最新的商品数量写入共享参数
            SharedUtil.getIntance(this).writeShared("count", "0");
            // 显示最新的商品数量
            showCount(0);
            Toast.makeText(this, "购物车已清空", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.menu_about) { // 点击了关于菜单项
            Toast.makeText(this, "这个是工具栏的演示demo", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_quit) { // 点击了退出菜单项
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
    // 显示购物车图标中的商品数量
    private void showCount(int count) {
        mCount = count;
        if (mCount == 0) {
            gp_content.setVisibility(View.GONE);
            gp_empty.setVisibility(View.VISIBLE);
        } else {
            gp_content.setVisibility(View.VISIBLE);
            gp_empty.setVisibility(View.GONE);
        }
    }
   // @Override
    // 一旦点击了广告图，就回调监听器的onBannerClick方法
//    public void onBannerClick(int position) {
//        String desc = String.format("您点击了第%d张图片", position + 1);
//        tv_pager.setText(desc);
//    }
    private void jumpFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
       // transaction.replace(R.id.realtabcontent, fragment);
        transaction.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_first:
                jumpFragment(new TabFirstFragment());
                break;
            case R.id.ll_second:
                jumpFragment(new TabSecondFragment());
                break;
            case R.id.ll_third:
                jumpFragment(new TabThirdFragment());
                break;
        }
    }
}
