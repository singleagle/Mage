package com.enjoy.mage.ui;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.graphics.Color;

import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.graphics.SceneTextGrh;
import com.enjoy.mage.manager.MultipleManager;

//�˳���Ϸ�Ի���
public class ModelDlg extends Scene {
	
	SceneTextGrh mText; 
	public void onLoadResources()
	{
		mText=new SceneTextGrh(13,Color.YELLOW);
		mText.onLoadResource();
				
	}
	
	public void onLoadScene(String text,final IClickListener pCancle,final IClickListener pOk)
	{
		Sprite border=MultipleManager.FACE.getSprite(MultipleGrhCfg.DIALOGBG_ID);
		
		Sprite btnOk=new Sprite(0,0,MultipleManager.FACE.getRegion(MultipleGrhCfg.BTNOK_ID)){
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
			{
			   pOk.onClick();
			   return true;	
			}
		};
		
		Sprite btnCancle=new Sprite(0,0,MultipleManager.FACE.getRegion(MultipleGrhCfg.BTNCANCLE_ID)){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				pCancle.onClick();
				return true;
			}
		};
		
		border.attachChild(btnCancle);
		btnCancle.setPosition(32,86);
		border.attachChild(btnOk);
		btnOk.setPosition(130,86);
		
		mText.showChangeableText(text,22,27,1,border);//��ʾ����
		this.attachChild(border);
		border.setPosition((SceneConfig.CAMERA_WIDTH-border.getWidth())/2,(SceneConfig.CAMERA_HEIGHT-border.getHeight())/2);
		
		this.setBackgroundEnabled(false);
		this.registerTouchArea(btnOk);
		this.registerTouchArea(btnCancle);
		this.setTouchAreaBindingEnabled(true);
		
	}
}
