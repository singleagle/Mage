package com.enjoy.mage.manager;
import java.util.Hashtable;

import org.andengine.entity.scene.Scene;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.MonstReader;
import com.enjoy.mage.data.SceneInfo;
import com.enjoy.mage.data.SceneType;
import com.enjoy.mage.data.ScenesReader;
import com.enjoy.mage.entity.AttackNumber;
import com.enjoy.mage.entity.MageScene;
import com.enjoy.mage.graphics.DoorGrh;
import com.enjoy.mage.graphics.MapsGrh;
import com.enjoy.mage.ui.GameMenu;

/**
 * @author weihongtang
 * 
 */
public class GameWord {
	
	private static GameWord sMyself;
	
	private static Hashtable<Integer,MageScene> mScenes= new Hashtable<Integer,MageScene>();
	
	static public GameWord getInstance(){
		if(sMyself == null){
			sMyself = new GameWord();
		}
		return sMyself;
	}
	
	private GameWord(){

	}
	
	public void loadResources(){
		DoorGrh.onLoadResource(); 
		MapsGrh.onLoadResource(); 
		MonstReader.onLoad();	 
		ScenesReader.onLoad();
		AttackNumber.onLoadResources();	
	}
			
	public MageScene getScene(int id)
	{
		return mScenes.get(id);
	}
	
	public void clearScene()
	{
		for(MageScene s:mScenes.values())
		{
		   s.clearChildScene();
		   s.clearEntityModifiers();
		   s.clearTouchAreas();
		   s.clearUpdateHandlers();
		   s.detachChildren();
		   s.detachSelf();
		}
	}
		
	public Scene startMainScene()
	{		
		MageScene scene=getScene(SceneConfig.SCENE_MAIN_ID);
		scene.onLoad();
        RoleManager.onLoadScene(scene);
	    UIManager.onLoadScene(scene);	
		return scene;
	}
	
	public Scene startGameMenu()
	{
		loadAllScenes();
		MageScene scene=getScene(SceneConfig.SCENE_MENU_ID);
		scene.onLoad();
		
		return scene;
	}
	
	
	private void loadAllScenes()
	{
		for(SceneInfo scene:ScenesReader.getSceneInfos())
		{
			int type=scene.getType();
			MageScene localScene=null;
			if(type==SceneType.SCENE_TYPE_MENU)
			{
				localScene=new GameMenu(scene);
				localScene.onLoad();
			}
			else
			{
				localScene=new MageScene(scene);
				if(type==SceneType.SCENE_TYPE_SWITCH)
				{
					localScene.onLoad();  
				}
			}		
			mScenes.put(localScene.getId(),localScene);	
		}
	}	
	
	public void gotoScene(int fromId,int toId)
	{		 
		 MageScene srcScene=getScene(fromId);
		 srcScene.clearTouchAreas();
		 srcScene.clearUpdateHandlers();
		 srcScene.detachChildren();
	     srcScene.detachSelf();
	      
	     MageScene scene = getScene(toId);  
		 scene.back();
	     Global.GetEngine().setScene(scene);
		 scene.onLoad();
		 if(scene.getSceneInfo().getType()!=SceneType.SCENE_TYPE_SWITCH)
		 {
               RoleManager.onLoadScene(scene);
			   UIManager.onLoadScene(scene);	 			 
		 }
		 
	     //scene.setTouchAreaBindingEnabled(true);				
	}	
	
	public void goBackToMainScene(int fromId)
	{
		gotoScene(fromId,SceneConfig.SCENE_MAIN_ID);
	}
}
