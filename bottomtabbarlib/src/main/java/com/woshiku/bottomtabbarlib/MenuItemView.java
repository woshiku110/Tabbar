package com.woshiku.bottomtabbarlib;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by admin on 2017-04-13.
 */

public class MenuItemView extends LinearLayout{
    ImageView icon;
    TextView text,remindText,point;
    int unSelectedColor,selectedColor,marginSize;
    CardView cardView;
    private MenuItemClickListener menuItemClickListener;

    interface MenuItemClickListener{
        void menuItemClick();
    }

    public void setMenuItemClickListener(MenuItemClickListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }

    public MenuItemView(Context context) {
        super(context);
    }
    public void generView(){
        initViews();
    }
    private void initViews(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_bar,this);
        cardView = (CardView)findViewById(R.id.item_bottom_bar_cardview);
        icon = (ImageView) findViewById(R.id.item_bottom_bar_image);
        text = (TextView) findViewById(R.id.item_bottom_bar_txt);
        remindText = (TextView)findViewById(R.id.item_bottom_bar_txt_remind);
        point = (TextView)findViewById(R.id.item_bottom_bar_point);
        cardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuItemClickListener != null){
                    menuItemClickListener.menuItemClick();
                }
            }
        });
    }


    public void setText(String str){
        if(text != null){
            text.setText(str);
        }
    }

    public void setIcon(int drawable){
        if(icon != null){
            icon.setBackground(ContextCompat.getDrawable(getContext(), drawable));
        }
    }

    public void setGap(int gap){
        LinearLayout.LayoutParams lineParam = (LinearLayout.LayoutParams)text.getLayoutParams();
        lineParam.topMargin = gap;
    }

    public void hideRemind(){
        remindText.setVisibility(INVISIBLE);
    }

    public void hidePoint(){
        point.setVisibility(INVISIBLE);
    }

    public void showPoint(){
        remindText.setVisibility(GONE);
        point.setVisibility(VISIBLE);
    }
    public void showText(String amount){
        remindText.setText(amount);
        remindText.setVisibility(VISIBLE);
        point.setVisibility(GONE);
    }

    public void setTabIconSize(int tabIconSize) {
        LinearLayout.LayoutParams iconParam = (LinearLayout.LayoutParams)icon.getLayoutParams();
        iconParam.width = iconParam.height = tabIconSize;
    }

    public void setUnSelectedColor(int unSelectedColor) {
        this.unSelectedColor = unSelectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setChecked(boolean isChecked){
        if(isChecked){
            icon.setEnabled(false);
            text.setTextColor(ContextCompat.getColor(getContext(),selectedColor));
        }else{
            icon.setEnabled(true);
            text.setTextColor(ContextCompat.getColor(getContext(),unSelectedColor));
        }
    }

    public void setMarginSize(int marginSize) {
        this.marginSize = marginSize;
        setGap(marginSize);
    }

    public void setTextSize(int textSize){
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
    }

    public void setTabMargin(int tabMargin){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)remindText.getLayoutParams();
        lp.leftMargin = tabMargin;
        RelativeLayout.LayoutParams lpOne = (RelativeLayout.LayoutParams)point.getLayoutParams();
        lpOne.leftMargin = tabMargin;
    }
    public TextView getRemindText(){
        return remindText;
    }
    public TextView getPoint(){
        return point;
    }
}
