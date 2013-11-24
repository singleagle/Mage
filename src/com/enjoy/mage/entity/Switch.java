package com.enjoy.mage.entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.enjoy.mage.config.SmallMapCfg;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.SceneManager;


public class Switch extends Door{
    public Switch(int x,int y,int pToScene,int pToSceneX,int pToSceneY,String pDesc,int pImageId) {
		
    	super(x,y,pToScene,pToSceneX,pToSceneY,pDesc,pImageId);
		// TODO Auto-generated constructor stub		
	}
	
	public void loadOnScene(final Scene scene)
	{
		loadOnScene(scene,mPos.x,mPos.y);
	}
	
	public void loadOnScene(final Scene scene,int x,int y)
	{
		  if(mImgId!=-1)
		  {
			Sprite board=MultipleManager.SCENEICON.getSprite(SmallMapCfg.BORDER_ID);
			TextureRegion region=MultipleManager.SCENEICON.getRegion(mImgId);
			Sprite icon=new Sprite(20,28,region){		
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub			
				 
				if(pSceneTouchEvent.isActionDown())
				 {
					SceneManager.gotoScene(1000,mToSceneId);
					return true; 
				 }
				else 
					return false;
			 };
			};
//			
	    	board.attachChild(icon);
			board.setPosition(x,y);
		    mTextGrh.showText(getTitle(),28,50,2,board);
			scene.registerTouchArea(icon);
		    scene.attachChild(board);		
		 }
	}
}
