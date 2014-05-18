package com.enjoy.mage.process;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.andengine.util.debug.Debug;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.data.Inventory;
import com.enjoy.mage.data.ItemsReader;
import com.enjoy.mage.entity.Hero;
import com.enjoy.mage.entity.ItemEntity;
import com.enjoy.mage.manager.RoleManager;
public class DataAccessor {
	
	public static final String FILE_NAME="data.swx";
	
	//�����Ƿ񱣴�ɹ�
	public static boolean saveUser()
	{		
		try{
		  
		  FileOutputStream output=Global.GetContext().openFileOutput(FILE_NAME,0);
		  Hero localHero=RoleManager.getHero();	
		  
		  Debug.w("source level:"+localHero.getmLevel());
		  byte[] level=Tools.ConvertIntToByteArray(localHero.getmLevel());
		  byte[] power=Tools.ConvertIntToByteArray(localHero.getPower());
		  byte[] exp=Tools.ConvertIntToByteArray(localHero.getmExp());
		  byte[] hp=Tools.ConvertIntToByteArray(localHero.getmHp());
		  byte[] maxHp=Tools.ConvertIntToByteArray(localHero.getmMaxHp());		  
		  byte[] mp=Tools.ConvertIntToByteArray(localHero.getmMp());
		  byte[] maxMp=Tools.ConvertIntToByteArray(localHero.getmMaxMp());
		  
		  byte[] minAttack=Tools.ConvertIntToByteArray(localHero.getmMinAttack());
		  byte[] maxAttack=Tools.ConvertIntToByteArray(localHero.getmMaxAttack());
		  byte[] defense=Tools.ConvertIntToByteArray(localHero.getmDefense()); //����
		  //����
		  byte[] doubleattack=Tools.ConvertIntToByteArray(localHero.getmDoubleAttackRate());
		  //����
		  byte[] dodge=Tools.ConvertIntToByteArray(localHero.getmDodgeRate());		  
		  output.write(level);
		  output.write(power);
		  output.write(exp);
		  output.write(hp);
		  output.write(maxHp);
		  output.write(mp);
		  output.write(maxMp);
		  output.write(minAttack);
		  output.write(maxAttack);
		  output.write(defense);
		  output.write(doubleattack);
		  output.write(dodge);
		  
		  Inventory bag=localHero.getmInventory();
		  output.write(Tools.ConvertIntToByteArray(bag.getItems().size()));
		  for(ItemEntity item:bag.getItems())
		  {
			  output.write(Tools.ConvertIntToByteArray(item.getmId())); //��ƷID
			  output.write(Tools.ConvertIntToByteArray(item.getAmount()));//��Ʒ����
		  }		  
		  output.flush();
		  output.close();
		  return true;
		}
		catch(Exception e)
		{
			Debug.w("�����ļ������쳣:"+e.getStackTrace().toString());
			return false;
		}
	
	}
	
	///
	public static void loadUser()
	{
	   try{
		 Hero localHero=RoleManager.getHero();
		 FileInputStream input=Global.GetContext().openFileInput(FILE_NAME);
		 
		 int level=getValFromBytes(input);
		 
		 Debug.w("level:"+level);
		 int power=getValFromBytes(input);
		 int exp=getValFromBytes(input);		 
     	 int hp=getValFromBytes(input);
     	 int maxHp=getValFromBytes(input);
     	 int mp=getValFromBytes(input);
     	 int maxMp=getValFromBytes(input);
     	 int min_attack=getValFromBytes(input);
     	 int max_attack=getValFromBytes(input);
     	 int defense=getValFromBytes(input);
     	 int double_attack=getValFromBytes(input);
     	 int dodge=getValFromBytes(input);
     	 
     	 int itemCount=getValFromBytes(input);
     	 
     	 for(int i=0;i<itemCount;i++)
     	 {
     		 int pId=getValFromBytes(input); 
     		 int amount=getValFromBytes(input);
     		 ItemEntity item=ItemsReader.mItemsTable.get(pId);
     		 item.setAmount(amount);
     		 localHero.getmInventory().addFromFile(item.copy());
     	 }
         localHero.setmLevel(level);
         localHero.setPower(power);
         localHero.setmExp(exp);
         localHero.setmHp(hp);
         localHero.setmMaxHp(maxHp);
         localHero.setmMp(mp);
         localHero.setmMaxMp(maxMp);
         localHero.setmMinAttack(min_attack);
         localHero.setmMaxAttack(max_attack);
         localHero.setmDefense(defense);
         localHero.setmDoubleAttackRate(double_attack);
         localHero.setmDodgeRate(dodge);    
         input.close();
		}
		catch(Exception e)
		{
			
		}
    }
	
	//�Ƿ���ڴ浵�ļ�
	public static boolean exitHeroSaveFile()
	{
		for(String file:Global.GetContext().fileList())
		{
			if(file.equals(FILE_NAME))
				return true;
		}
		return false;
	}
	
	
	//
	private static int getValFromBytes(FileInputStream input)
	{
		try{
		  byte [] bVal=new byte[4];
		  input.read(bVal);		 
 		  return Tools.ConvertByteArrayToInt(bVal);
		}
		catch(Exception e)
		{
		  return 0;
		}		
	}
}
