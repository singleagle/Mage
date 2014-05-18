package com.enjoy.mage.entity;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.IModifier;

import android.graphics.Color;

import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.graphics.SceneTextGrh;

public class AttackNumber {
	static SceneTextGrh mMonstNum,mRoleNum,mMonstDouble,mMonstDodge,mRoleDouble,mRoleDodge
	,mLevelUpInfo,mCommonInfo;
	public static void onLoadResources()
	{
		mMonstNum=new SceneTextGrh(20,Color.YELLOW);
		mMonstDouble=new SceneTextGrh(30,Color.YELLOW);  //��������   
		mMonstDodge=new SceneTextGrh(30,Color.YELLOW);   //��������
		
		mRoleNum=new SceneTextGrh(20,Color.RED);
		mRoleDouble=new SceneTextGrh(30,Color.RED);
		mRoleDodge=new SceneTextGrh(30,Color.RED);
		
		//����ʾ
		mLevelUpInfo=new SceneTextGrh(40,Color.YELLOW);
		
		//ͨ����ʾ
		mCommonInfo=new SceneTextGrh(20,Color.YELLOW);
		
		mMonstDouble.onLoadResource();
		mMonstDodge.onLoadResource();
		mMonstNum.onLoadResource();
		mRoleNum.onLoadResource();
		mRoleDouble.onLoadResource();
		mRoleDodge.onLoadResource();
		mLevelUpInfo.onLoadResource();
		mCommonInfo.onLoadResource();
	}
	
	//���뵽���г���
	public static void loadOnAllScenes(Scene scene)
	{
		mLevelUpInfo.showChangeableText("��ϲ��ȼ�����", 0, 0, UIConfig.UI_LEVEL-1,scene);
		mLevelUpInfo.setVisible(false);	
		mCommonInfo.showChangeableText("xxxxxxxxx", 0, 0, UIConfig.UI_LEVEL-1, scene);
		mCommonInfo.setVisible(false);
	}
	
	//ֻ���뵽�й���ĳ���
	public static void loadOnScene(Scene scene)
	{
		mMonstNum.showChangeableText("-11111",300,200,UIConfig.UI_LEVEL-1,scene);
		mMonstDouble.showChangeableText("����", 0, 0, UIConfig.UI_LEVEL-1,scene);
		mMonstDodge.showChangeableText("����",0,0,UIConfig.UI_LEVEL-1,scene);
		mRoleNum.showChangeableText("-11111",300,200,UIConfig.UI_LEVEL-1,scene);
		mRoleDouble.showChangeableText("����", 0, 0, UIConfig.UI_LEVEL-1,scene);
		mRoleDodge.showChangeableText("����", 0, 0, UIConfig.UI_LEVEL-1,scene);
	
		
		mMonstDouble.setVisible(false);
		mMonstDodge.setVisible(false);
		mMonstNum.setVisible(false);
		mRoleNum.setVisible(false);		
		mRoleDouble.setVisible(false);
		mRoleDodge.setVisible(false);
		
	}
	
	//��ʾͨ����ʾ��Ϣ
	public static void showCommonInfo(String text)
	{
		update(mCommonInfo,text,SceneConfig.LEVELUP_TEXT_X+40,SceneConfig.LEVELUP_TEXT_Y+80);
	}
	
	//��ʾ�Թ�����˺�
	public static void updateMonstNum(String text,int x,int y)
	{
		update(mMonstNum,text,x,y);
	}
	
	//��ʾ�Թ���ı�����ʾ
	public static void updateMonstDouble(String text,int x,int y)
	{
		update(mMonstDouble,text,x,y);
	}
	
	//��ʾ�Թ����������ʾ
	public static void updateMonstDodge(String text,int x,int y)
	{
		update(mMonstDodge,text,x,y);
	}
	
	//��ʾ�Խ�ɫ���˺�
	public static void updateRoleNum(String text,int x,int y)
	{
		update(mRoleNum,text,x,y);
	}
	
	//��ʾ�Խ�ɫ�ı�����ʾ
	public static void updateRoleDouble(String text,int x,int y)
	{
		update(mRoleDouble,text,x,y);
	}
	
	//��ʾ�Խ�ɫ��������ʾ
	public static void updateRoleDodge(String text,int x,int y)
	{
		update(mRoleDodge,text,x,y);
	}
	
	public static void showLevelUp()
	{
		mLevelUpInfo.setVisible(true);
		ScaleModifier sm=new ScaleModifier(0.7f,0.8f,1.3f,new MoveEnd(mLevelUpInfo));
		mLevelUpInfo.getmAniText().setPosition(SceneConfig.LEVELUP_TEXT_X,SceneConfig.LEVELUP_TEXT_Y);
		mLevelUpInfo.getmAniText().clearEntityModifiers();
		mLevelUpInfo.getmAniText().registerEntityModifier(sm);
	}
	
	private static void update(SceneTextGrh pGrh,String text,int x,int y)
	{
		pGrh.setVisible(true);
		pGrh.updateText(text,x,y);
		MoveModifier mm=new MoveModifier(0.7f,x,x,y,y-80,new MoveEnd(pGrh));		
		Text textSprite=pGrh.getmAniText();
		textSprite.clearEntityModifiers();
		textSprite.registerEntityModifier(mm);	
	}
	
	static class MoveEnd implements IEntityModifierListener
	{
		SceneTextGrh mTextGrh;
		public MoveEnd(SceneTextGrh pTextGrh)
		{
			mTextGrh=pTextGrh;
		}

		@Override
		public void onModifierStarted(IModifier<IEntity> pModifier,
				IEntity pItem) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onModifierFinished(IModifier<IEntity> pModifier,
				IEntity pItem) {
			// TODO Auto-generated method stub
			mTextGrh.setVisible(false);
		}	
	}	
}
