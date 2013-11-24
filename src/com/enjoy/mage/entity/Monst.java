package com.enjoy.mage.entity;
import java.util.ArrayList;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.Scene;

import android.graphics.Point;

import com.enjoy.mage.common.Tools;
import com.enjoy.mage.data.MonstDropGoodsInfo;
import com.enjoy.mage.data.MonstGrhReader;
import com.enjoy.mage.graphics.MonstGrh;
import com.enjoy.mage.manager.RoleManager;
import com.enjoy.mage.process.FightProcessor;
import com.enjoy.mage.process.GoodsDropProcessor;

public class Monst extends Role {
  
  private int mGrhId=-1; //ͼ����ԴID    
  private int mPrizeExp=0;//�������ö��پ���
  private ArrayList<MonstDropGoodsInfo> mDropGoodsList=new ArrayList<MonstDropGoodsInfo>();
  
  public Monst(int pID,int pGrhID,Point pPos)
  {
	  mGrhId=pGrhID;
	  mPos=pPos;
  }
  
  public Monst(int pID,int pGrhID)
  {
	  mID=pID;
	  mGrhId=pGrhID;
	  mPos=new Point(300,200);
	  
  }
  MonstGrh mGrh;
  
  Scene mScene;
  public void loadOnScene(Scene scene)
  {
	  
	  mScene=scene;
	  mHp=mMaxHp;
	  setIsDie(false);
	  mGrh=MonstGrhReader.getGrh(mGrhId);
	  mGrh.setPos(mPos);
	  mGrh.onLoadInScene(scene);
	  scene.registerUpdateHandler(new MonstUpdate());
  }

  public int getPrizeExp() {
	return mPrizeExp;
  }

  public void setPrizeExp(int mGetExp) {
	  this.mPrizeExp = mGetExp;
  }
  boolean bInView=false;
  class MonstUpdate implements IUpdateHandler
  {

	@Override
	public void onUpdate(float pSecondsElapsed) {
		// TODO Auto-generated method stub
		if(Tools.Distance(mPos,RoleManager.getHero().getPos())<65)
		{		
			bInView=true;
		//	mStandDir=(RoleManager.getHero().getPos().x-mPos.x)>=0?1:-1;
		}
		else
			bInView=false;
		
		if(!isDie())
		{
		if(bInView)
		{
			Monst.this.setTarget(RoleManager.getHero());
			mGrh.attackAni(new IMonstAttack() {
				
				@Override
				public void onAttack() {
					// TODO Auto-generated method stub
					FightProcessor.processAttack(Monst.this,RoleManager.getHero());//���﹥����ɫ
				}

				@Override
				public Role getTarget() {
					// TODO Auto-generated method stub
					return Monst.this.getTarget();
				}
			});
			RoleManager.getHero().setTarget(Monst.this); //���ù���Ŀ��			
		}
		else
		{
			RoleManager.getHero().setTarget(null);
			mGrh.stand(1);
		}	
		}
		
		mGrh.updateHPVal(((double)Monst.this.getmHp())/Monst.this.getmMaxHp());
		
		if(mHp<=0)
		{
			onDie();
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}	  
  }
  
  //������������
  public void onDie()
  {
	  if(!isDie())
	  {
	    this.setIsDie(true);
	    mGrh.hideHPBar();
	    mGrh.hideAll();
	    this.setTarget(null);
	    Hero localHero=RoleManager.getHero();
	    localHero.setTarget(null);
	    
	    for(MonstDropGoodsInfo info:mDropGoodsList)
	    GoodsDropProcessor.monstDropGoods(mScene, mPos,info.getmID(),info.getmMinDropNum(),info.getmMaxDropNum());
	    
	    int exp=localHero.getmExp()+this.getPrizeExp();
	    localHero.setmExp(exp);//�������Ӿ���
	  }
      
  }

public ArrayList<MonstDropGoodsInfo> getmDropGoodsList() {
	return mDropGoodsList;
}
  
}
