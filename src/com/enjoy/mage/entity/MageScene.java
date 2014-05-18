package com.enjoy.mage.entity;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.config.SmallMapCfg;
import com.enjoy.mage.data.SceneInfo;
import com.enjoy.mage.data.SceneLayout;
import com.enjoy.mage.data.SceneType;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.RoleManager;
import com.enjoy.mage.manager.GameWord;

public class MageScene extends Scene{
	protected  SceneInfo mSceneInfo=null;
	public MageScene(SceneInfo info)
	{
		setSceneInfo(info);
	}
	
	public int getId()
	{
		return mSceneInfo.getId();
	}
	
	public void onLoad()
	{		
		Sprite map=mSceneInfo.getMapImage();
		map.setWidth(854);
		map.setHeight(480);
		
		map.setZIndex(0);	  
		this.attachChild(map);
		int localX=mSceneInfo.getLayout().getPosX();
		int localY=mSceneInfo.getLayout().getPosY();
		
		int count=0;
		for(Door door:mSceneInfo.getDoors())
		{
			if(door.getImgId()==-1)
			  door.loadOnScene(this);
			else
			{
			   if(mSceneInfo.getLayout().getType()==SceneLayout.FLOW)
			   {
			     int x=(SceneConfig.SCENE_ICON_DIS+SceneConfig.SCENE_ICON_W)*(count%SceneConfig.SCENE_ICON_COL)+localX;
			     int y=count/SceneConfig.SCENE_ICON_COL*(SceneConfig.SCENE_ICON_H+40)+SceneConfig.SCENE_ICON_DIS+localY;
			     ((Switch)door).loadOnScene(this,x,y);
			  }
			  else
			  {
				  ((Switch)door).loadOnScene(this); 
			  }
			}
			count++;
		}
		
		for(NPC npc:mSceneInfo.getNpcs())
		{
			npc.loadOnScene(this);
		}
		AttackNumber.loadOnAllScenes(this);
		if(mSceneInfo.getType()==SceneType.SCENE_TYPE_MONST)
		{
			AttackNumber.loadOnScene(this); 
			for(Monst m:mSceneInfo.getMonsts())
			{
				m.loadOnScene(this);
			}
			
			VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
			 Sprite returnSprite=new Sprite(0,0,MultipleManager.SCENEICON.getRegion(SmallMapCfg.GOBACK_ID), vm){
				@Override
			 public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY) {
					if(pSceneTouchEvent.isActionDown())
					{
						GameWord.getInstance().goBackToMainScene(getId());
						return true;
					}
					else
					{
						return false;
					}
					
				}
			};
			this.registerTouchArea(returnSprite);
			returnSprite.setPosition(780,10);
			this.attachChild(returnSprite);			
		}
			
		//this.setTouchAreaBindingEnabled(true);
		this.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				MageScene.this.sortChildren();
				RoleManager.getHero().getGrh().update();
			}
		});		
	}

	public SceneInfo getSceneInfo() {
		return mSceneInfo;
	}

	public void setSceneInfo(SceneInfo pSceneInfo) {
		this.mSceneInfo = pSceneInfo;
	}
}
