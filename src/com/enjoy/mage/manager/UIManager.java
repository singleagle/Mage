package com.enjoy.mage.manager;
import org.anddev.andengine.entity.scene.Scene;

import com.enjoy.mage.data.ItemsReader;
import com.enjoy.mage.entity.NPC;
import com.enjoy.mage.graphics.Controller;
import com.enjoy.mage.graphics.DlgGrh;
import com.enjoy.mage.ui.Dialog;
import com.enjoy.mage.ui.IClickListener;
import com.enjoy.mage.ui.InventoryDlg;
import com.enjoy.mage.ui.PlayerDlg;
import com.enjoy.mage.ui.TaskDlg;
import com.enjoy.mage.ui.ToolBar;
import com.enjoy.mage.ui.ToolBarItem;
/**
 * @author shaoleibo
 * ͳһ��ȾUI����
 */
public class UIManager {
	
	static ToolBar mToolBar=new ToolBar();
	static Dialog mPlayerDlg=new PlayerDlg(254,25);
	static Dialog mInventoryDlg;
	static Dialog mTaskDlg=new TaskDlg(300,25);
	static Controller mController=new Controller();
	
	
	//�жϵ�ǰ��ɫ�Ƿ���NPC���� �����NPC���� �͵�������Ի���
	static public void showTaskDlg(NPC pNpc)
	{
	     if(pNpc!=null)
	     {
	    	if(pNpc.isBCanTalk())  //�Ƿ��ܹ���NPC��̸  �ߵ�NPC������ isBCanTalk���ܹ�����ΪTrue
	    	{
			 ((TaskDlg)mTaskDlg).setNpc(pNpc);
			 mTaskDlg.show();
			 //Debug.w("showTaskDlg-end");
	    	}
	     }
		
	}
	
	static private void init()
	{		

		//localInventory.addItem(localEntity.copy());
		mInventoryDlg=new InventoryDlg(400,25,RoleManager.getHero().getmInventory());
		/////////////////////////////////////		
		
		ToolBarItem roleIconItem=new ToolBarItem(15,70,DlgGrh.getmRoleInfoIconRegion(),new IClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				mPlayerDlg.show();
			
			}
		});
	    ToolBarItem bagIconItem=new ToolBarItem(15,150,DlgGrh.getmBagIconRegion(),new IClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				mInventoryDlg.show();
			}
		});
	    
	    mToolBar.add(roleIconItem);
	    mToolBar.add(bagIconItem);
	    
	}
	
	static public void onLoadResources()
	{
		////////////////////////////////
		
		/////////////////////////////////	
		mController.onLoadResources(); //�������
		DlgGrh.onLoadResources();   //���봰�ڽ���
		ItemsReader.onLoad();
		((PlayerDlg)mPlayerDlg).loadResources();
		init();	
	}
	
	static int count=0;
	static public void onLoadScene(Scene scene)
	{			
		mController.onLoadInScene(scene);
		mToolBar.onLoadScene(scene);
		mInventoryDlg.onLoadScene(scene);
		mPlayerDlg.onLoadScene(scene);	
		mTaskDlg.onLoadScene(scene);
	}
}
