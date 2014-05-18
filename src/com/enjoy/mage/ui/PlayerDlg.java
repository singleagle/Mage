package com.enjoy.mage.ui;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.entity.Hero;
import com.enjoy.mage.graphics.DlgGrh;
import com.enjoy.mage.manager.RoleManager;

import android.graphics.Point;

public class PlayerDlg extends Dialog{
	private Sprite mPlayerSprite;
	private Point mOffset=new Point(54,70);
	
	public PlayerDlg(int x,int y)
	{
		this(new Point(x,y));
	}
	
	public PlayerDlg(Point pPos) {
		super(pPos);
	
	}
	
	private ChangeableTextLabel [] mLabel=new ChangeableTextLabel[8];
	public void loadResources()
	{
		for(int i=0;i<8;i++)
		{
			mLabel[i]=new ChangeableTextLabel();
			mLabel[i].onLoadResource();
		}
	}
	
	public void onLoadScene(Scene scene)
	{		
		super.onLoadScene(scene);
		
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mPlayerSprite=new Sprite(0,0,DlgGrh.getmPlayerRegion(), vm)
		{
			
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
				// TODO Auto-generated method stub				
				onClose();
				return true;
			}
			
		};	
		
		mLabel[0].onLoadSprite(mPlayerSprite, "ս����:XXXXXXXX", 31,61);
		mLabel[1].onLoadSprite(mPlayerSprite,"����:XXXXXXXXXXX",31,81);
		
		mLabel[2].onLoadSprite(mPlayerSprite,"HP:XXXXXXXXXXX", 3,200);
		mLabel[3].onLoadSprite(mPlayerSprite, "������:XXXXXXXXX",3,220);
		mLabel[4].onLoadSprite(mPlayerSprite,"����:XXXXXXX",123,220);
		
		mLabel[5].onLoadSprite(mPlayerSprite,"������:XXXXXXX",3,240);
		mLabel[6].onLoadSprite(mPlayerSprite,"����:XXXXXXX",123,240);
		mLabel[7].onLoadSprite(mPlayerSprite,"LV:XXXX",50,10);
				
		scene.registerTouchArea(mPlayerSprite);
		scene.registerUpdateHandler(new UpdateNumber());
		if(!mPlayerSprite.hasParent())
		mDlgSprite.attachChild(mPlayerSprite);		
		mPlayerSprite.setPosition(mOffset.x,mOffset.y);	
		mPlayerSprite.setAlpha(0.1f);
	}
	
	class UpdateNumber implements IUpdateHandler
	{

		@Override
		public void onUpdate(float pSecondsElapsed) {
			// TODO Auto-generated method stub
			
			Hero hero=RoleManager.getHero();
			mLabel[0].setText("ս����:"+hero.getPower());
			mLabel[1].setText("����:"+hero.getmExp()+"/"+hero.getmNextLevelExp());
			mLabel[2].setText("HP:"+hero.getmHp()+"/"+hero.getmMaxHp());
			mLabel[3].setText("������:"+hero.getmMinAttack()+"-"+hero.getmMaxAttack());//��������Χ
			mLabel[4].setText("����:"+hero.getmDefense());
			mLabel[5].setText("������:"+hero.getmDoubleAttackRate());
			mLabel[6].setText("����:"+hero.getmDodgeRate());
			mLabel[7].setText("LV:"+hero.getmLevel());
			
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
