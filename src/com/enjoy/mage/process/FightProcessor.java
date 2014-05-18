package com.enjoy.mage.process;

import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;

import com.enjoy.mage.entity.AttackNumber;
import com.enjoy.mage.entity.Hero;
import com.enjoy.mage.entity.Monst;
import com.enjoy.mage.entity.Role;

//��������
/**
 * @author shaoleibo
 *
 */
public class FightProcessor {
		
	 //�����ʣ�2�����˺�
	  private static int calculateDoubleAttackRate(Role pRole)
	  {
	    if (pRole.getmDoubleAttackRate() * 9 > Math.random() * 1000.0D)
	      return 2;
	    else
	    return 1;
	  }
	  
	  //������  ����0��ʾMISS 1��ʾԭ��
	  private static int calculateDodgeDamageRate(Role pRole)
	  {
	    if (pRole.getmDodgeRate() * 9 > Math.random() * 2000.0D)
	      return 0;
	    else
	      return 1;
	  }
	  	  
	  //����paramSprite1 �� paramSprite2 ����ͨ�����˺�
	  private static int calculateDamage(Role paramSprite1,Role paramSprite2)
	  {
		 
		  int val=MathUtils.random(paramSprite1.getmMinAttack(),paramSprite1.getmMaxAttack());;
		  if(paramSprite1 instanceof Hero) //�������Ҫ��ɫ �Ժ�Ҫ���������Ĺ�����
		  {
			 // Hero localHero=(Hero)paramSprite1;				  
		  }
		  val-=(paramSprite2.getmDefense()/5);  
		  
		  Debug.w("������:"+val);
		  Debug.w("�������:"+paramSprite2.getmDefense());
		  return val;
	  }  
	  	  
	  
	  //attackRole �����ߣ�bAttackRole��������
	  public static void processAttack(Role attackRole,Role bAttackRole)
	  {
		  int damage=0;
		  int dodge=calculateDodgeDamageRate(bAttackRole);
		  int doubleAttack=calculateDoubleAttackRate(attackRole);
		  damage=dodge*doubleAttack*calculateDamage(attackRole, bAttackRole);
		  	  
		  int x=bAttackRole.getPos().x-10;
		  int y=bAttackRole.getPos().y-80;
		  
		  if(bAttackRole instanceof Monst)  //��������Ϊ����
		  {
		    if(dodge==0)
			  AttackNumber.updateMonstDodge("����", x, y);
		    if(doubleAttack==2)
			  AttackNumber.updateMonstDouble("����", x, y);
		    AttackNumber.updateMonstNum("-"+damage,x,y);
		  }
		  else if(bAttackRole instanceof Hero) ///��������Ϊ��Ҫ��ɫ
		  {
			 if(dodge==0)
			    AttackNumber.updateRoleDodge("����", x, y);
		     if(doubleAttack==2)
				AttackNumber.updateRoleDouble("����", x, y);
			 AttackNumber.updateRoleNum("-"+damage,x,y);
		  }
		  
		  bAttackRole.setmHp(bAttackRole.getmHp()-damage); //Ѫ������
	  }
}
