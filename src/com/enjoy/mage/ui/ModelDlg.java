package com.enjoy.mage.ui;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Color;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.graphics.SceneTextGrh;
import com.enjoy.mage.manager.MultipleManager;

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
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		Sprite btnOk=new Sprite(0,0,MultipleManager.FACE.getRegion(MultipleGrhCfg.BTNOK_ID), vm){
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
			{
			   pOk.onClick();
			   return true;	
			}
		};
		
		Sprite btnCancle=new Sprite(0,0,MultipleManager.FACE.getRegion(MultipleGrhCfg.BTNCANCLE_ID), vm){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
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
		//this.setTouchAreaBindingEnabled(true);
		
	}
}
