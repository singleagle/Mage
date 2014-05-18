package com.enjoy.mage.ui;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.SceneInfo;
import com.enjoy.mage.entity.MageScene;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.GameWord;
import com.enjoy.mage.process.DataAccessor;

public class GameMenu extends MageScene{
	IClickListener mClick;
	
	public void setmClick(IClickListener mClick) {
		this.mClick = mClick;
	}

	public GameMenu(SceneInfo info)
	{
		super(info);
	}
		
	public void onLoad()
	{
		Sprite map=mSceneInfo.getMapImage();
		map.setWidth(854);
		map.setHeight(480);
		map.setZIndex(0);	  
		this.attachChild(map);
				
		TextureRegion startRegion=MultipleManager.FACE.getRegion(MultipleGrhCfg.STARTBTN_ID);
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		Sprite start=new Sprite(366,296,startRegion, vm){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
			 	if(pSceneTouchEvent.isActionDown())
			 	{
			 		GameWord.getInstance().gotoScene(SceneConfig.SCENE_MENU_ID,SceneConfig.SCENE_MAIN_ID);
			 		return true;
			 	}
			 	else
			 	{
			 		return false;
			 	}
			}
		};

				
		TextureRegion goonRegion=MultipleManager.FACE.getRegion(MultipleGrhCfg.GOONBTN_ID);
		Sprite goon=new Sprite(367,349,goonRegion, vm)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.isActionDown())
				{DataAccessor.loadUser();
					GameWord.getInstance().gotoScene(SceneConfig.SCENE_MENU_ID,SceneConfig.SCENE_MAIN_ID);
					
					return true;
				}
				else
				{
					return false;
				}
			}
		};
		
		TextureRegion exitRegion=MultipleManager.FACE.getRegion(MultipleGrhCfg.EXITBTN_ID);
		Sprite exit=new Sprite(367,402,exitRegion, vm)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				
				if(pSceneTouchEvent.isActionDown())
				{
					if(mClick!=null)
					mClick.onClick();
					return true;
				}
				else
				{
					return false;
				}
			}
		};				
		ScaleModifier sm=new ScaleModifier(0.8f,1.0f,2.0f);
		LoopEntityModifier lm=new LoopEntityModifier(sm);		
		if(DataAccessor.exitHeroSaveFile())
		{
			goon.registerEntityModifier(lm);
		}
		else
		{
			start.registerEntityModifier(lm);
		}
		
		map.attachChild(start);
		map.attachChild(goon);
		map.attachChild(exit);
		
		this.registerTouchArea(start);
		this.registerTouchArea(goon);
		this.registerTouchArea(exit);
		//this.setTouchAreaBindingEnabled(true);		
	}
}
