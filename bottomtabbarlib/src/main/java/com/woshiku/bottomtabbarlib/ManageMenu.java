package com.woshiku.bottomtabbarlib;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017-04-14.
 */

public class ManageMenu {
    LinearLayout parentLine;
    Context context;
    int []tabIcons = {};
    String []titles = {};
    int selectedPos = 0;
    int []selectedColor;
    int []unSelectedColors;
    int tabIconSize,topMargin,textSize,tabbarRemindMargin;
    boolean isRemindText = false;
    private TabbarClickListener tabbarClickListener;
    private NewsClearRemindListener newsClearRemindListener;
    interface TabbarClickListener{
        void tabbarClick(int pos);
    }
    interface NewsClearRemindListener{
        void newsClearRemind(int index,boolean isRemindText,int amount);
    }

    public void setNewsClearRemindListener(NewsClearRemindListener newsClearRemindListener) {
        this.newsClearRemindListener = newsClearRemindListener;
    }

    public void setTabbarClickListener(TabbarClickListener tabbarClickListener) {
        this.tabbarClickListener = tabbarClickListener;
    }

    public ManageMenu(Context context, LinearLayout parentLine) {
        this.context = context;
        this.parentLine = parentLine;
    }

    public void gengerMenus(){
        parentLine.setWeightSum(titles.length);
        parentLine.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        for(int i=0;i<titles.length;i++){
            MenuItemView menuItemView = new MenuItemView(context);
            menuItemView.setOrientation(LinearLayout.HORIZONTAL);
            menuItemView.generView();
            menuItemView.setUnSelectedColor(unSelectedColors[i]);
            menuItemView.setSelectedColor(selectedColor[i]);
            menuItemView.setText(titles[i]);
            menuItemView.setIcon(tabIcons[i]);
            menuItemView.setTabIconSize(tabIconSize);
            menuItemView.setMarginSize(topMargin);
            menuItemView.setTextSize(textSize);
            menuItemView.setTabMargin(tabbarRemindMargin);
            if(!isRemindText){
                menuItemView.hideRemind();
                menuItemView.hidePoint();
            }
            menuItemView.setLayoutParams(params);
            final int posIndex = i;
            menuItemView.setMenuItemClickListener(new MenuItemView.MenuItemClickListener() {
                @Override
                public void menuItemClick() {
                    selectedPos = posIndex;
                    setChooseState(posIndex);
                    if (tabbarClickListener != null) {
                        tabbarClickListener.tabbarClick(posIndex);
                    }
                }
            });
            parentLine.addView(menuItemView);
        }
        setChooseState(selectedPos);
    }

    private void setChooseState(int index){
        for(int i=0;i<titles.length;i++){
            MenuItemView menuItemView = (MenuItemView)parentLine.getChildAt(i);
            if(i==index){
                menuItemView.setChecked(true);
                if(isRemindText(menuItemView)){//关闭消息 并提醒提示
                    menuItemView.getRemindText().setVisibility(View.INVISIBLE);
                    if(newsClearRemindListener != null){
                        int amount = 0;
                        try {
                            amount = Integer.parseInt(menuItemView.getRemindText().getText().toString());
                        }catch (Exception e){
                        }
                        newsClearRemindListener.newsClearRemind(i,true,amount);
                    }
                }
                if(isRemindPoint(menuItemView)){//关闭消息 并提醒提示
                    menuItemView.getPoint().setVisibility(View.INVISIBLE);
                    if(newsClearRemindListener != null){
                        newsClearRemindListener.newsClearRemind(i,false,-1);
                    }
                }
            }else{
                menuItemView.setChecked(false);
            }
        }
    }
    public void setTabIcons(int[] tabIcons) {
        this.tabIcons = tabIcons;
    }


    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setSelectedColor(int[] selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setUnSelectedColors(int[] unSelectedColors) {
        this.unSelectedColors = unSelectedColors;
    }

    public void setTabIconSize(int tabIconSize) {
        this.tabIconSize = tabIconSize;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTabbarRemindMargin(int tabbarRemindMargin) {
        this.tabbarRemindMargin = tabbarRemindMargin;
    }
    private boolean isRemindText(MenuItemView menuItemView){
        return menuItemView.getRemindText().getVisibility()== View.VISIBLE?true:false;
    }
    private boolean isRemindPoint(MenuItemView menuItemView){
        return menuItemView.getPoint().getVisibility()== View.VISIBLE?true:false;
    }
    public void setTabbarNews(int index,boolean isRemindText,int amount){
        if(index != selectedPos){//索引值不是当前显示
            MenuItemView menuItemView = (MenuItemView) parentLine.getChildAt(index);
            if(isRemindText){
                menuItemView.getRemindText().setVisibility(View.VISIBLE);
                menuItemView.getRemindText().setText(amount+"");
            }else{
                menuItemView.getPoint().setVisibility(View.VISIBLE);
            }
        }
    }

    public void setRemindTextVisible(boolean isVisible){
        this.isRemindText = isVisible;
    }
}
