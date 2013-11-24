package com.enjoy.mage.common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.enjoy.mage.config.GameConfig;

import android.content.Context;

public class Global {
	private static Engine mEngine;
	private static BaseGameActivity mGameActivity;
	public static int []mExpList=new int[GameConfig.LEVEL_MAX+1];
	
	public static void Init(Engine pEngine)
	{
		mEngine=pEngine;
		makeExpList();		
	}
	
	public static void destory()
	{
		
	}
	
	//����һ��������Ҫ����ֵ�б�
	private static void makeExpList()
	{
		float pow=2.4f+GameConfig.EXP_INFLATION/100.0f;
		mExpList[0]=0;
		mExpList[1]=0;
		for(int i=2;i<GameConfig.LEVEL_MAX+1;i++)
		{
			double n=GameConfig.EXP_BASE*Math.pow(i+3,pow)/Math.pow(5,pow);
			int exp=(int) n;
			mExpList[i]=exp;
		}
		
	}
	
	public static Context GetContext()
	{
		return MyApplication.getAppContext();
	}
	
	public static Engine GetEngine()
	{
		return mEngine;
	}

	public static BaseGameActivity GetGameActivity() {
		return mGameActivity;
	}

		
}
