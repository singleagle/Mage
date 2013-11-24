package com.enjoy.mage.entity;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;

import android.graphics.Point;

import com.enjoy.mage.graphics.BubleGrh;
import com.enjoy.mage.graphics.SceneTextGrh;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.RoleManager;

//���볡����������ʹ��
public class NPC extends Role{
	private int mId=0; //NPC ID
	private int mGrhId=0;
	private Point mPos=new Point(0,0);
	private int mActiveDistance=50;//
	private boolean mBCanTalk=false;  //�ж��Ƿ��ܹ������ǽ�̸������ɫ�ߵ�NPC�Ա�  ��ʼ��̸
	
	private int mNpcType=0;
	
	BubleGrh bubleGrh=new BubleGrh();  //������NPC���������
		
	//pPos ��ʾ�ڳ����е����  pId:NPC ID  pGrhId ͼƬID
	public NPC(Point pPos,int pId,int pGrhId,String pName,int pType)
	{
		mPos=pPos;
		setId(pId);
		mGrhId=pGrhId;
		mName=pName;
		mNpcType=pType;
	}
	
	public void loadOnScene(Scene scene)
	{
		Sprite npc=MultipleManager.NPC.getSprite(mGrhId);
	    scene.attachChild(npc);
	    npc.registerUpdateHandler(new NPCUpdate(scene,npc));
	    
	    npc.setPosition(mPos.x-npc.getWidth()/2,mPos.y-npc.getHeight()+20);
		npc.setZIndex(mPos.y);
		float localHeight=npc.getHeight();
		int localY=mPos.y-(int)localHeight;
			
		int localBlackGround=(mName.length()+2)*15;
		
		//////////////////////////
		Rectangle rect=makeColoredRectangle(mPos.x-localBlackGround/2,localY);
		rect.setZIndex(localY);
        scene.attachChild(rect);		
		//////////////////////////
                
		SceneTextGrh text=new SceneTextGrh();		
		text.onLoadResource();
		text.showText(mName,mPos.x-localBlackGround/2+15,localY,mPos.y,scene);	
		
		//////////////////////////
		bubleGrh.onLoadResource();
		bubleGrh.onLoadScene(scene);
	}
    
	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}
	
	
	//npc ���ֱ���
    private Rectangle makeColoredRectangle(final float pX, final float pY) {
        final Rectangle coloredRect = new Rectangle(pX, pY,(mName.length()+2)*14,15);
        coloredRect.setColor(0,0,0);
        coloredRect.setAlpha(0.5f);
        return coloredRect;
    }
    
    public boolean isBCanTalk() {
		return mBCanTalk;
	}

	public int getNpcType() {
		return mNpcType;
	}

	public void setNpcType(int mNpcType) {
		this.mNpcType = mNpcType;
	}

	class NPCUpdate implements IUpdateHandler
    {	
    	private Sprite mSprite;
    	public NPCUpdate(Scene scene,Sprite sprite)
    	{
    		mSprite=sprite;
    	}
   
		@Override
		public void onUpdate(float pSecondsElapsed) {
			// TODO Auto-generated method stub
			
			Point heroPos=RoleManager.getHero().getPos();
			//����
					
			if(MathUtils.distance(mPos.x, mPos.y,heroPos.x,heroPos.y)<mActiveDistance)
			{
				bubleGrh.Show(mPos.x+12,mPos.y-(int)(mSprite.getHeight())+40,mPos.y+3);
				RoleManager.getHero().setTarget(NPC.this);
				mBCanTalk=true;
			}
			else
			{
				bubleGrh.hide();
				mBCanTalk=false;
				
			}	        
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
