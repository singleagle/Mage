package com.enjoy.mage.entity;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;

import android.graphics.Point;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.GameConfig;
import com.enjoy.mage.data.HeroTaskList;
import com.enjoy.mage.data.Inventory;
import com.enjoy.mage.graphics.PlayerGrh;
import com.enjoy.mage.process.FightProcessor;

public class Hero extends Role{
	private int mNextLevelExp=0; //��һ�����辭��
	private int mExp=0;	
	
	private PlayerGrh mGrh; //����ͼ��  ����Ժ��ɫ�����ҪIDȥ
	private HeroTaskList mTasks=new HeroTaskList();//��������
	private Inventory mInventory=new Inventory();//����
	
	private boolean mBInDoor=false;
	
	public Hero()
	{
		this.mLevel=1;
		this.mMinAttack=20;
		this.mMaxAttack=25;
		this.mMaxHp=130;//���Ѫ�� ���ŵȼ����Ӷ�����
		this.mDefense=20;
		this.mDoubleAttack=30;
		this.mDodgeRate=10;
		this.mNextLevelExp=Global.mExpList[this.mLevel+1];		
	}
	
	//�Ƿ��ڳ�����
	public boolean isInDoor()
	{
		return mBInDoor;
	}
	
	public void setInDoor(boolean pBInDoor)
	{
		mBInDoor=pBInDoor;
	}

	public PlayerGrh getGrh() {
		return mGrh;
	}

	public void setGrh(PlayerGrh pGrh) {
		mGrh = pGrh;
	}
		
	public void onLoadScene(Scene scene)
	{
		mScene=scene;
		setmHp(this.mMaxHp);//���볡��  Ѫ����
		setIsDie(false);//���������͸���
		
		mGrh.onLoadInScene(scene);
		setTarget(null);
		
		scene.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				mGrh.updateHPVal(((float)mHp)/mMaxHp);
				
				if(mHp<=0)
				{
					setIsDie(true);
					mGrh.toDie();
				}
				
				if(mExp>=getmNextLevelExp())
				{
					doLevelUp();
				}
			}
		});
	}
	
	//����
	public void doLevelUp()
	{
		if(mLevel<=GameConfig.LEVEL_MAX)
		{
		 mExp=mExp-mNextLevelExp;//��� ��ʣ�ྭ��
		 mNextLevelExp=getmNextLevelExp();
		 mLevel+=1;
		 mMaxAttack+=5;
		 mMinAttack+=5;
		 mDefense+=5;
		 mMaxHp+=18;
		 mHp=mMaxHp;
		 AttackNumber.showLevelUp();
		}
	}
	
	//����������
	public Point getPos()
	{
		return mGrh.getmPos();
	}
	
	public void setPos(int x,int y)
	{
		mGrh.setPos(x,y);
	}
    
	public HeroTaskList getTasks() {
		return mTasks;
	}

	public int getmNextLevelExp() {
		return Global.mExpList[this.mLevel+1];
	}

	public void setmNextLevelExp(int mNextLevelExp) {
		this.mNextLevelExp = mNextLevelExp;
	}

	public int getmExp() {
		return mExp;
	}

	public void setmExp(int mExp) {
		this.mExp = mExp;
	}
	
	public void attackTarget()
	{
		if(!this.isDie())
		{
		 mGrh.toAttacking(new IAnimationListener() {
			
			@Override
			public void onAnimationEnd(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				mGrh.toStand();//�л�Ϊվ��
				Role target=Hero.this.getTarget();
				if(target instanceof Monst)
				{
					Monst monst=(Monst)target;
					FightProcessor.processAttack(Hero.this, monst);					
				}				
			}
		 });
		}
	}

	public Inventory getmInventory() {
		return mInventory;
	}

	public void setmInventory(Inventory mInventory) {
		this.mInventory = mInventory;
	}
	
	public void pickUp(ItemEntity item)
	{
		if(mInventory.addItem(item))
		  item.removeOnGround(); //�ӵ������Ƴ�
		else 
		  AttackNumber.showCommonInfo("��������");	
	}
	
	public void addExp(int exp)
	{
		this.mExp+=exp;
	}
	
	
}
