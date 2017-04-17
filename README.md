# Tabbar
自定义底部导航栏和其它布局，以及底部导航栏和viewpager相结合使用。
# 主要用的BottomTabBar
bottomtabbar里面可以放布局文件，如RelativeLayout,用户如果放入把RelativeLayout换成ViewPager，点击Item时会进行自动的切换。
```javascript
  <com.woshiku.bottomtabbarlib.BottomTabBar
        android:id="@+id/bottom_bar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:tabBarBg="#f0f0f0"//设置底部栏的颜色
        app:tabIconSize="22dp"//设置icon的大小
        app:tabMargin="2dp"//设置icon和txt的间距
        app:tabBarHeight="55dp"//设置底部栏的高度
        app:tabTextSize="12"//设置txt的大小
        app:tab_remind_visible="true"//设置是否要显示提醒文本
        app:tabRemindMargin="-10dp"//设置icon和消息提醒的位置 >
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center">
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="123"
                />
        </RelativeLayout>
    </com.woshiku.bottomtabbarlib.BottomTabBar>
```
```javascript
        以下是通过代码来设置布局的方法，具体请参考MainActivity.java里面的用法
        bottomTabBar.setTabIcons(tabIcons);//设置tab icons
        bottomTabBar.setTabUnselectedColors(tabUnselectedColors); //设置tab 被选中的文本
        bottomTabBar.setTabSelectedColors(tabSelectedColors);  //设置tab 未被选中的文本
        bottomTabBar.setTitles(titles);//设置tab的文本
        bottomTabBar.commit();//提交视图
        bottomTabBar.setTabbarNews(1,true,6);//表示提醒的文本
        bottomTabBar.setTabbarNews(2, false,-1);//表示提醒的点击
        bottomTabBar.setTabbarNews(3,true,8); //设置第三个提醒文本
        bottomTabBar.setTabbarNews(3,false,-1);//设置第三个提醒点
```
##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件(849383208@qq.com)
* QQ: 849383208
