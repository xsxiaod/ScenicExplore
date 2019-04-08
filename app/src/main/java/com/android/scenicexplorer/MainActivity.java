package com.android.scenicexplorer;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private RouteFragment routeFragment;
    private MyFragment myFragment;

    private View homeLayout;
    private View findLayout;
    private View routeLayout;
    private View myLayout;

    private ImageView homeImage;
    private ImageView findImage;
    private ImageView routeImage;
    private ImageView myImage;

    private TextView homeText;
    private TextView findText;
    private TextView routeText;
    private TextView myText;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    private void initView() {
        homeLayout = findViewById(R.id.home_layout);
        findLayout = findViewById(R.id.find_layout);
        routeLayout = findViewById(R.id.route_layout);
        myLayout = findViewById(R.id.my_layout);
        homeImage = (ImageView) findViewById(R.id.home_image);
        findImage = (ImageView) findViewById(R.id.find_image);
        routeImage = (ImageView) findViewById(R.id.route_image);
        myImage = (ImageView) findViewById(R.id.my_image);
        homeText = (TextView) findViewById(R.id.home_text);
        findText = (TextView) findViewById(R.id.find_text);
        routeText = (TextView) findViewById(R.id.route_text);
        myText = (TextView) findViewById(R.id.my_text);
        homeLayout.setOnClickListener(this);
        findLayout.setOnClickListener(this);
        routeLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_layout:
                // 当点击了首页tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.find_layout:
                // 当点击了发现tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.route_layout:
                // 当点击了行程tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.my_layout:
                // 当点击了我的tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }

    }
    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     * 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了我的tab时，改变控件的图片和文字颜色
                homeImage.setImageResource(R.drawable.home1);
                //messageText.setTextColor(Color.WHITE);
                if (homeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                // 当点击了发现tab时，改变控件的图片和文字颜色
                findImage.setImageResource(R.drawable.find1);
                //findText.setTextColor(Color.WHITE);
                if (findFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    findFragment = new FindFragment();
                    transaction.add(R.id.content, findFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(findFragment);
                }
                break;
            case 2:
                // 当点击了行程tab时，改变控件的图片和文字颜色
                routeImage.setImageResource(R.drawable.route1);
                //newsText.setTextColor(Color.WHITE);
                if (routeFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    routeFragment = new RouteFragment();
                    transaction.add(R.id.content, routeFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(routeFragment);
                }
                break;
            case 3:
            default:
                // 当点击了我的tab时，改变控件的图片和文字颜色
                myImage.setImageResource(R.drawable.my1);
                //myText.setTextColor(Color.WHITE);
                if (myFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection() {
        homeImage.setImageResource(R.drawable.home);
        homeText.setTextColor(Color.parseColor("#82858b"));
        findImage.setImageResource(R.drawable.find);
        findText.setTextColor(Color.parseColor("#82858b"));
        routeImage.setImageResource(R.drawable.route);
        routeText.setTextColor(Color.parseColor("#82858b"));
        myImage.setImageResource(R.drawable.my);
        myText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (routeFragment != null) {
            transaction.hide(routeFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

}
