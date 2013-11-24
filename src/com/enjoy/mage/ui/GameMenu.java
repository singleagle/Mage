package com.enjoy.mage.ui;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.SceneInfo;
import com.enjoy.mage.entity.MYScene;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.SceneManager;
import com.enjoy.mage.process.DataAccessor;

public class GameMenu extends MYScene{
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
		
		Sprite start=new Sprite(366,296,startRegion){@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
			 	if(pSceneTouchEvent.isActionDown())
			 	{
			 		SceneManager.gotoScene(SceneConfig.SCENE_MENU_ID,SceneConfig.SCENE_MAIN_ID);
			 		return true;
			 	}
			 	else
			 	{
			 		return false;
			 	}
			}
		};

				
		TextureRegion goonRegion=MultipleManager.FACE.getRegion(MultipleGrhCfg.GOONBTN_ID);
		Sprite goon=new Sprite(367,349,goonRegion)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.isActionDown())
				{DataAccessor.loadUser();
					SceneManager.gotoScene(SceneConfig.SCENE_MENU_ID,SceneConfig.SCENE_MAIN_ID);
					
					return true;
				}
				else
				{
					return false;
				}
			}
		};
		
		TextureRegion exitRegion=MultipleManager.FACE.getRegion(MultipleGrhCfg.EXITBTN_ID);
		Sprite exit=new Sprite(367,402,exitRegion)
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
		this.setTouchAreaBindingEnabled(true);		
	}
}
