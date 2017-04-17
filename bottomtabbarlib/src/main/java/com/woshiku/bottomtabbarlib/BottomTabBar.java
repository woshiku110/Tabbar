package com.woshiku.bottomtabbarlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/4/7.
 */
public class BottomTabBar extends RelativeLayout{
    ViewPager viewPager;
    LinearLayout bottomBar;
    LinearLayout innerLineBar;
    int tabBarHeight,tabBarBg,tabIconSize,tabMargin,tabTextSize,tabRemindMargin;
    boolean isRemindVisible = false;
    int []tabIcons = {R.drawable.tabbar_bespeak_drawble,R.drawable.tabbar_head_drawble,R.drawable.tabbar_right_drawble};
    int []tabSelectedColors = {R.color.selector_txt_selected,R.color.selector_txt_selected,R.color.selector_txt_selected};
    int []tabUnselectedColors= {R.color.selector_txt_unselect,R.color.selector_txt_unselect,R.color.selector_txt_unselect};
    String []titles = {"预约列表","候诊列表","个人中心"};
    ManageMenu manageMenu;
    private TabbarClickListener tabbarClickListener;
    private NewsClearRemindListener newsClearRemindListener;
    /**
     * @desc 用于用户点击click回调返回用户点击索引
     * */
    public interface TabbarClickListener{
        void tabbarClick(int index);
    }
    /**
     * @desc 用于消息清除回调
     * */
    public interface NewsClearRemindListener{
        void newsClearRemind(int index,boolean isRemindText,int amount);
    }
    public BottomTabBar(Context context) {
        super(context);
    }

    public BottomTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabBar(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initChildren();
    }

    /**
     * @desc 用于获取配置底部栏目的一些设置
     * */
    private void initTabBar(Context context,AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.BottomTabBar);
        tabBarHeight = (int)a.getDimension(R.styleable.BottomTabBar_tabBarHeight,(int)context.getResources().getDimension(R.dimen.bottom_tabbar_height));
        tabIconSize = (int)a.getDimension(R.styleable.BottomTabBar_tabIconSize,(int)context.getResources().getDimension(R.dimen.bottom_tabbar_icon_size));
        tabMargin = (int)a.getDimension(R.styleable.BottomTabBar_tabMargin,(int)context.getResources().getDimension(R.dimen.bottom_tabbar_margin));
        tabRemindMargin = (int)a.getDimension(R.styleable.BottomTabBar_tabRemindMargin,(int)context.getResources().getDimension(R.dimen.bottom_tabbar_remind_margin));
        tabBarBg = a.getColor(R.styleable.BottomTabBar_tabBarBg, ContextCompat.getColor(context,R.color.tabbar_bg));
        tabTextSize = a.getInteger(R.styleable.BottomTabBar_tabTextSize,12);
        isRemindVisible = a.getBoolean(R.styleable.BottomTabBar_tab_remind_visible,false);
        bottomBar = new LinearLayout(context);
        bottomBar.setBackgroundColor(tabBarBg);
        bottomBar.setOrientation(LinearLayout.HORIZONTAL);
        bottomBar.setId(R.id.bottom_bar_line);
        innerLineBar = new LinearLayout(context);
        innerLineBar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.tab_line_color));
        LinearLayout.LayoutParams innerParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        innerParam.topMargin = (int)getResources().getDimension(R.dimen.bottom_tabbar_line);
        bottomBar.addView(innerLineBar, innerParam);
    }
    /**
     * @desc 用于布局viewpage和底部栏
     * */
    private void initChildren(){
        if(getChildAt(0) instanceof ViewPager){
            viewPager = (ViewPager)getChildAt(0);
        }
        RelativeLayout.LayoutParams tabbarLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,tabBarHeight);//给tabbar的布局位于布局的左下角
        tabbarLp.addRule(ALIGN_PARENT_LEFT);
        tabbarLp.addRule(ALIGN_PARENT_BOTTOM);
        addView(bottomBar, tabbarLp);//添加底边栏到布局
        RelativeLayout.LayoutParams viewPageLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);//获取viewpage的布局并设置viewpage在底边栏上面
        viewPageLp.addRule(ABOVE, R.id.bottom_bar_line);
        if(viewPager != null){
            viewPager.setLayoutParams(viewPageLp);//给viewpage设置布局参数
        }
        configItems();
    }
    private void configItems(){
        innerLineBar.removeAllViews();
        manageMenu = new ManageMenu(getContext(),innerLineBar);
        manageMenu.setTabIconSize(tabIconSize);
        manageMenu.setUnSelectedColors(tabUnselectedColors);
        manageMenu.setSelectedColor(tabSelectedColors);
        manageMenu.setTabIcons(tabIcons);
        manageMenu.setTitles(titles);
        manageMenu.setTopMargin(tabMargin);
        manageMenu.setTextSize(tabTextSize);
        manageMenu.setTabbarRemindMargin(tabRemindMargin);
        manageMenu.setRemindTextVisible(isRemindVisible);
        manageMenu.gengerMenus();
        manageMenu.setTabbarClickListener(new ManageMenu.TabbarClickListener() {
            @Override
            public void tabbarClick(int pos) {
                if(viewPager != null){
                    viewPager.setCurrentItem(pos,false);
                }
                if(tabbarClickListener != null){
                    tabbarClickListener.tabbarClick(pos);
                }
            }
        });
        manageMenu.setNewsClearRemindListener(new ManageMenu.NewsClearRemindListener() {
            @Override
            public void newsClearRemind(int index, boolean isRemindText, int amount) {
                if(newsClearRemindListener != null){
                    newsClearRemindListener.newsClearRemind(index,isRemindText,amount);
                }
            }
        });
    }
    /**
     * @param tabIcons tabbar时显示用的图标
     * */
    public void setTabIcons(int[] tabIcons) {
        this.tabIcons = tabIcons;
    }

    /**
     * @param tabUnselectedColors tabbar里面文本被选中时的颜色
     * */
    public void setTabUnselectedColors(int[] tabUnselectedColors) {
        this.tabUnselectedColors = tabUnselectedColors;
    }
    /**
     * @param  tabSelectedColors tabbar里面未选中文本的颜色
     * */
    public void setTabSelectedColors(int[] tabSelectedColors) {
        this.tabSelectedColors = tabSelectedColors;
    }
    /**
     * 用于代码生成子选项的提交
     * */
    public void commit(){
        isRemindVisible = false;
        configItems();
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    /**
     * @param index 第几个选项
     * @param isRemindText 是不是提醒文本
     * @param amount 如果是提醒文本写入数量
     * */
    public void setTabbarNews(int index,boolean isRemindText,int amount){
        manageMenu.setTabbarNews(index,isRemindText,amount);
    }

    public void setTabbarClickListener(TabbarClickListener tabbarClickListener) {
        this.tabbarClickListener = tabbarClickListener;
    }

    public void setNewsClearRemindListener(NewsClearRemindListener newsClearRemindListener) {
        this.newsClearRemindListener = newsClearRemindListener;
    }

}
