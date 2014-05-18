package com.enjoy.mage.entity;

import org.andengine.entity.scene.Scene;

import android.graphics.Point;

public class Role {
	public int getmHp() {
		return mHp;
	}
	
	public void setmHp(int pHp) {
		if(pHp>=0)
		 this.mHp = pHp;
		else
		 this.mHp=0;
	}
	
	public int getmMaxHp() {
		return mMaxHp;
	}
	
	public void setmMaxHp(int pMaxHp) {
		if(pMaxHp>=0)
		 this.mMaxHp = pMaxHp;
		else
		 this.mMaxHp=0;	
	}
	
	public int getmMp() {
		return mMp;
	}
	
	public void setmMp(int mMp) {
		this.mMp = mMp;
	}
	public int getmMaxMp() {
		return mMaxMp;
	}
	public void setmMaxMp(int mMaxMp) {
		this.mMaxMp = mMaxMp;
	}
	public int getmMaxAttack() {
		return mMaxAttack;
	}
	public void setmMaxAttack(int mMaxAttack) {
		this.mMaxAttack = mMaxAttack;
	}
	public int getmMinAttack() {
		return mMinAttack;
	}
	public void setmMinAttack(int mMinAttack) {
		this.mMinAttack = mMinAttack;
	}
	public int getmDefense() {
		return mDefense;
	}
	public void setmDefense(int mDefense) {
		this.mDefense = mDefense;
	}
	public int getmDoubleAttackRate() {
		return mDoubleAttack;
	}
	
	//eg:13=13%
	public void setmDoubleAttackRate(int pDoubleAttack) {
		this.mDoubleAttack = pDoubleAttack;
	}
	public int getmDodgeRate() {
		return mDodgeRate;
	}
	public void setmDodgeRate(int mDodgeRate) {
		this.mDodgeRate = mDodgeRate;
	}
	
	protected int mID=-1; //ʵ��Ψһ��ʾ
	
	public int getmID() {
		return mID;
	}
	public void setmID(int mID) {
		this.mID = mID;
	}

	protected int mHp=0; //��ǰ��Ѫ��
	protected int mMaxHp=0;
	protected int mMp=0;
	protected int mMaxMp=0;
	
	//��������Χ
	protected int mMaxAttack;
	protected int mMinAttack;
	protected int mDefense;        //����
	
	protected int mDoubleAttack;//������ doubleAttack
	protected int mDodgeRate;      //����
	
	protected String mName;  //��ɫ���
	protected boolean bIsDie=false;//�Ƿ�����
	
	protected int mPower=0;//ս����
	
	protected int mLevel=0;//��ǰ�ȼ�
	
	
	public boolean isDie()
	{
		return bIsDie;
	}
	
	public void setIsDie(boolean b)
	{
		bIsDie=b;
	}
	
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public int getPower() {
		return mPower;
	}
	public void setPower(int mPower) {
		this.mPower = mPower;
	}
	
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	protected Role mTarget;//��ǰĿ��  ����˵�ߵ�NPC�Ա� ����NPC��ΪĿ��  Ŀ���һֱ���ֵ�ѡ���¸�Ŀ��ǰ
	public Role getTarget() {
		return mTarget;
	}
	
	public void setTarget(Role mTarget) {
		this.mTarget = mTarget;
	}
	
	protected Point mPos=new Point(0,0);

	public Point getPos() {
		return mPos;
	}
	public void setPos(Point mPos) {
		this.mPos = mPos;
	}
	
	protected Scene mScene=null;//���ڳ���
	public Scene getmScene()
	{
		return mScene;
	}
}
