package com.enjoy.mage.ui;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.data.Inventory;
import com.enjoy.mage.data.Task;
import com.enjoy.mage.data.TasksReader;
import com.enjoy.mage.entity.AttackNumber;
import com.enjoy.mage.entity.NPC;
import com.enjoy.mage.entity.NpcType;
import com.enjoy.mage.entity.TextType;
import com.enjoy.mage.graphics.DlgGrh;
import com.enjoy.mage.manager.RoleManager;
import com.enjoy.mage.manager.GameWord;

import android.graphics.Point;

public class TaskDlg extends Dialog{
    private NPC mNpc;
    private Button mBtn=new Button();

	public TaskDlg(Point pPos) {
		super(pPos);
		// TODO Auto-generated constructor stub
	}
	
	public TaskDlg(int x, int y) {
		// TODO Auto-generated constructor stub
		this(new Point(x,y));
	}
	
	Sprite mLineSprite;
	public void onLoadScene(Scene scene)
	{
		super.onLoadScene(scene);
		
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mLineSprite=new Sprite(UIConfig.TASK_LINE_X,UIConfig.TASK_LINE_Y,DlgGrh.getLineRegion(), vm)
		{
			
			///contains��ѭ�������� pX,pYΪ ���ص���� ��ͨ���ж�containsȻ����ִ��onAreaTouched
			@Override
			public boolean contains(final float pX, final float pY) {
				if(bInCloseRect(pX,pY))
				    return true;
				else
					return false;
			}

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
	            onClose();
				return true;
			}
			
		};
		
		scene.registerTouchArea(mLineSprite);
		
		mDlgSprite.attachChild(mLineSprite);
		
		initNpcName("");
		initContent("");
		
		mBtn.onLoadResource();
	}
	
	private void initNpcName(String text)
	{		
		mTextGrh.initText(TextType.TASK_NAME,text,UIConfig.TASK_TEXTNAME_X,UIConfig.TASK_TEXTNAME_Y,mDlgSprite);		
	}
	
	private void initContent(String text)
	{
		mTextGrh.initText(TextType.TASK_CONTENT,text,UIConfig.TASK_CONTENT_X,UIConfig.TASK_CONTENT_Y,mDlgSprite);
	}

	public NPC getNpc() {
		return mNpc;
	}

	public void setNpc(NPC pNpc) {	
		
		remove();
		this.mNpc = pNpc;
		mTextGrh.setNameText(pNpc.getName());		
		process(pNpc.getNpcType(),pNpc.getId());
	}

	public void process(int type,int npcid)
	{
		if(type==NpcType.NPC_TYPE_GOODS)
		{
			if(RoleManager.getHero().getTasks().isInit()) //��������б�Ϊ��
			{
				final Task localTask=TasksReader.getInitTask(npcid);
			
				if(localTask!=null)
				{
			       String localDesc=localTask.getmDesc();
				   String strDesc=Tools.ProcessText(localDesc);
				   mTextGrh.setContentText(strDesc);//������������
				   
					mBtn.onLoadSprite(mScene,mDlgSprite,"������Ʒ",new IClickListener()
					{

						@Override
						public void onClick() {
							// TODO Auto-generated method stub
							Inventory bag=RoleManager.getHero().getmInventory();
							TaskDlg.this.onClose();
							if(bag.isExitsItems(localTask.getmRequiredItemsID()))
							{
								bag.deleteItems(localTask.getmRequiredItemsID());
								AttackNumber.showCommonInfo("�㽻������Ʒ");
								RoleManager.getHero().addExp(50);
							}
							else
							{
								AttackNumber.showCommonInfo("��Ʒ������");
							}
						}
						
					}); //��ӽ�������ť
				}
			}
		}
		else if(type==NpcType.NPC_TYPE_TRANSMIT)
		{
			Task localTask=TasksReader.getInitTask(npcid);
			
			Debug.w("Task Count:"+TasksReader.getGlobalTaskList().size());
			
			if(localTask!=null)
			{			
				String localDesc=localTask.getmDesc();
				String strDesc=Tools.ProcessText(localDesc);
			    mTextGrh.setContentText(strDesc);//������������
				   
			    mBtn.onLoadSprite(mScene,mDlgSprite,"ѡ�񳡾�",new IClickListener()
				{

					@Override
					public void onClick() {
							// TODO Auto-generated method stub
						//SceneManager.gotoScene(1000, 1002);
						mScene.setChildScene(GameWord.getInstance().getScene(1001));
				
					}						
				});  
				
			}
		}
	}
	
	private void remove()
	{
		mDlgSprite.detachChildren();
		mDlgSprite.attachChild(mLineSprite);		
		initNpcName("");
		initContent("");
	}
}
