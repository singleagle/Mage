package com.enjoy.mage.manager;
import java.util.Hashtable;

import org.anddev.andengine.entity.scene.Scene;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.MonstReader;
import com.enjoy.mage.data.SceneInfo;
import com.enjoy.mage.data.SceneType;
import com.enjoy.mage.data.ScenesReader;
import com.enjoy.mage.entity.AttackNumber;
import com.enjoy.mage.entity.MYScene;
import com.enjoy.mage.graphics.DoorGrh;
import com.enjoy.mage.graphics.MapsGrh;
import com.enjoy.mage.ui.GameMenu;

/**
 * @author shaoleibo
 * 
 */
public class SceneManager {
	private static Hashtable<Integer,MYScene> mScenes=new Hashtable<Integer,MYScene>();
	
	static public void onLoadResources()
	{
		DoorGrh.onLoadResource(); //���͵�ͼƬ��Դ
		MapsGrh.onLoadResource(); //��ͼ��Դ
		MonstReader.onLoad();	 //������Դ���� ������ScenesReader.onLoad()ǰ����
		ScenesReader.onLoad();
		AttackNumber.onLoadResources();	
	}
			
	//��ȡ�����������ж�Ӧ�ĳ���
	static public MYScene getScene(int id)
	{
		return mScenes.get(id);
	}
	
	static public void clearScene()
	{
		for(MYScene s:mScenes.values())
		{
		   s.clearChildScene();
		   s.clearEntityModifiers();
		   s.clearTouchAreas();
		   s.clearUpdateHandlers();
		   s.detachChildren();
		   s.detachSelf();
		}
	}
		
	static public Scene startMainScene()
	{		
		MYScene scene=getScene(SceneConfig.SCENE_MAIN_ID);
		scene.onLoad();
        RoleManager.onLoadScene(scene);
	    UIManager.onLoadScene(scene);	
		return scene;
	}
	
	static public Scene startGameMenu()
	{
		loadAllScenes();
		MYScene scene=getScene(SceneConfig.SCENE_MENU_ID);
		scene.onLoad();
		
		return scene;
	}
	
	
	//ά��һ��û���������ĳ����б?ֱ�������л��Ż��������
	static private void loadAllScenes()
	{
		for(SceneInfo scene:ScenesReader.getSceneInfos())
		{
			int type=scene.getType();
			MYScene localScene=null;
			if(type==SceneType.SCENE_TYPE_MENU)
			{
				localScene=new GameMenu(scene);
				localScene.onLoad();
			}
			else
			{
				localScene=new MYScene(scene);
				if(type==SceneType.SCENE_TYPE_SWITCH)
				{
					localScene.onLoad();  //Ԥ��������Դ
				}
			}		
			mScenes.put(localScene.getId(),localScene);	
		}
	}	
	
	//������ת
	static public void gotoScene(int fromId,int toId)
	{		 
		 MYScene srcScene=getScene(fromId);
		 srcScene.clearTouchAreas();
		 srcScene.clearUpdateHandlers();
		 srcScene.detachChildren();
	     srcScene.detachSelf();
	      
	     MYScene scene=getScene(toId);  //��Ҫ�л��ĳ���
		 scene.back();
	     Global.GetEngine().setScene(scene);
		 scene.onLoad();
		 if(scene.getSceneInfo().getType()!=SceneType.SCENE_TYPE_SWITCH) //��Ϊѡ�񳡾�
		 {
               RoleManager.onLoadScene(scene);
			   UIManager.onLoadScene(scene);	 			 
		 }
		 
	     scene.setTouchAreaBindingEnabled(true);				
	}	
	
	//���ص�������
	static public void goBackToMainScene(int fromId)
	{
		SceneManager.gotoScene(fromId,SceneConfig.SCENE_MAIN_ID);
	}
}
