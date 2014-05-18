package com.enjoy.mage.entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.SmallMapCfg;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.GameWord;


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
			VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
			Sprite icon=new Sprite(20,28,region, vm){		
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub			
				 
				if(pSceneTouchEvent.isActionDown())
				 {
					GameWord.getInstance().gotoScene(1000,mToSceneId);
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
