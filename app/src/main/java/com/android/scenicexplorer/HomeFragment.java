package com.android.scenicexplorer;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sxd on 2019/3/28.
 */
public class HomeFragment extends Fragment {
    private View mView;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;

    private ImageView strategy;
    private ImageView ticket;
    private ImageView surround;
    private ImageView answer;
    private ImageView more;

    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.timg,
            R.drawable.timg1,
            R.drawable.timg2,
            R.drawable.timg3,
            R.drawable.timg4
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "轮播1",
            "轮播2",
            "轮播3",
            "轮播4",
            "轮播5"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         mView =  inflater.inflate(R.layout.home_layout,container,false);
        more = (ImageView)mView.findViewById(R.id.more);
        setView();
        initView();
        return mView;

    }

    private void initView() {
        strategy = (ImageView)mView.findViewById(R.id.strategy);
        ticket = (ImageView)mView.findViewById(R.id.ticket);
        surround = (ImageView)mView.findViewById(R.id.surround);
        answer = (ImageView)mView.findViewById(R.id.answer);
        more = (ImageView)mView.findViewById(R.id.more);

        strategy.setOnClickListener(mylistener);
        ticket.setOnClickListener(mylistener);
        surround.setOnClickListener(mylistener);
        answer.setOnClickListener(mylistener);
        more.setOnClickListener(mylistener);

    }
    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.strategy:
                    Toast.makeText(getContext(),"You Click 1:" + toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ticket:
                    Toast.makeText(getContext(),"You Click 2:" + toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.surround:
                    Toast.makeText(getContext(),"You Click 3:" + toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.answer:
                    Toast.makeText(getContext(),"You Click 4:" + toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.more:
                    Toast.makeText(getContext(),"You Click 5:" + toString(),Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private void setView(){
        mViewPaper = (ViewPager)mView.findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));

        title = (TextView) mView.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return images.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }
    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    /**
     * 图片轮播任务
     * @author sxd
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }
}