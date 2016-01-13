package com.example.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.kakalauncher.R;

/**
 * 打赏页面
 * @author ansen
 * @create time 2015-08-07
 */
public class RewardLauncherFragment extends LauncherBaseFragment{
	private ImageView ivReward;
	private ImageView ivGold;
	
	private Bitmap goldBitmap;
	private boolean started;//是否开启动画(ViewPage滑动时候给这个变量赋值)
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rooView=inflater.inflate(R.layout.fragment_reward_launcher, null);
		ivGold=(ImageView) rooView.findViewById(R.id.iv_gold);
		ivReward=(ImageView) rooView.findViewById(R.id.iv_reward);
		
		//获取硬币的高度
		goldBitmap=BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.icon_gold);
		startAnimation();
		return rooView;
	}
	
	public void startAnimation(){
		started=true;
		
		//向下移动动画 硬币的高度*2+80   
		TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,goldBitmap.getHeight()*2+80);
		translateAnimation.setDuration(500);
		translateAnimation.setFillAfter(true);
		
		ivGold.startAnimation(translateAnimation);
		translateAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation){
				if(started){
					ivReward.setVisibility(View.VISIBLE);
					//硬币移动动画结束开启缩放动画
		            Animation anim=AnimationUtils.loadAnimation(getActivity(),R.anim.reward_launcher);  
		            ivReward.startAnimation(anim);
		            anim.setAnimationListener(new AnimationListener(){
		                @Override  
		                public void onAnimationStart(Animation animation) {}  
		                @Override  
		                public void onAnimationRepeat(Animation animation) {}  
		                @Override  
		                public void onAnimationEnd(Animation animation) {
		                		//缩放动画结束 开启改变透明度动画
		                		AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
		                		alphaAnimation.setDuration(1000);
		                		ivReward.startAnimation(alphaAnimation);
		                		alphaAnimation.setAnimationListener(new AnimationListener() {
									@Override
									public void onAnimationStart(Animation animation) {}
									@Override
									public void onAnimationRepeat(Animation animation) {}
									@Override
									public void onAnimationEnd(Animation animation) {
										//透明度动画结束隐藏图片
										ivReward.setVisibility(View.GONE);
									}
							});
		                }
		            });
				}
			}
			@Override
			public void onAnimationRepeat(Animation animation) {}
		});
	}
	
	@Override
	public void stopAnimation(){
		started=false;//结束动画时标示符设置为false
		ivGold.clearAnimation();//清空view上的动画
	}
}
