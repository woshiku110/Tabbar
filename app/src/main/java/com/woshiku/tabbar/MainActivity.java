package com.woshiku.tabbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.woshiku.bottomtabbarlib.BottomTabBar;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.bottom_bar)
    BottomTabBar bottomTabBar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context = MainActivity.this;
        initBottomBar();
    }

    private void initBottomBar(){
        //代码设置布局
        int []tabIcons = {com.woshiku.bottomtabbarlib.R.drawable.tabbar_bespeak_drawble, com.woshiku.bottomtabbarlib.R.drawable.tabbar_head_drawble, com.woshiku.bottomtabbarlib.R.drawable.tabbar_right_drawble,com.woshiku.bottomtabbarlib.R.drawable.tabbar_head_drawble};//根据项目需求自行添加selector 可以参考示列
        int []tabSelectedColors = {com.woshiku.bottomtabbarlib.R.color.selector_txt_selected, com.woshiku.bottomtabbarlib.R.color.selector_txt_selected, com.woshiku.bottomtabbarlib.R.color.selector_txt_selected,com.woshiku.bottomtabbarlib.R.color.selector_txt_selected};//根据项目需求自行添加color 可以参考示列
        int []tabUnselectedColors= {com.woshiku.bottomtabbarlib.R.color.selector_txt_unselect, com.woshiku.bottomtabbarlib.R.color.selector_txt_unselect, com.woshiku.bottomtabbarlib.R.color.selector_txt_unselect,com.woshiku.bottomtabbarlib.R.color.selector_txt_unselect};//根据项目需求自行添加color 可以参考示列
        String []titles = {"测试一","测试2","测试三","测试四"};
        bottomTabBar.setTabIcons(tabIcons);//设置tab icons
        bottomTabBar.setTabUnselectedColors(tabUnselectedColors); //设置tab 被选中的文本
        bottomTabBar.setTabSelectedColors(tabSelectedColors);  //设置tab 未被选中的文本
        bottomTabBar.setTitles(titles);//设置tab的文本
        bottomTabBar.commit();//提交视图
        bottomTabBar.setTabbarNews(1,true,6);//表示提醒的文本
        bottomTabBar.setTabbarNews(2, false,-1);//表示提醒的点击
        bottomTabBar.setTabbarNews(3,true,8); //设置第三个提醒文本
        bottomTabBar.setTabbarNews(3,false,-1);//设置第三个提醒点
        /**
         * @param index 用户点击的第几个
         * @desc 用户点击tabbar回调
         * */
        bottomTabBar.setTabbarClickListener(new BottomTabBar.TabbarClickListener() {
            @Override
            public void tabbarClick(int index) {
                Toast.makeText(context, "点击了" +index,Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * @param index 用户点击的第几个
         * @param isRemindText true 表示文本提醒 false表示点提醒
         * @param amount 文本提醒并且显示文本里面的数量
         * @desc 有消息提醒时 消除提醒回调给用户
         * */
        bottomTabBar.setNewsClearRemindListener(new BottomTabBar.NewsClearRemindListener() {
            @Override
            public void newsClearRemind(int index, boolean isRemindText, int amount) {
                if(isRemindText){
                    Toast.makeText(context,"消除文本"+amount,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"消除点",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
