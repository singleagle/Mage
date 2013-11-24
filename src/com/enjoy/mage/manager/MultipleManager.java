package com.enjoy.mage.manager;

import com.enjoy.mage.graphics.MultipleGrh;

public class MultipleManager {
	
	public static MultipleGrh FACE; //������FACEͼ���ͼƬ����  �Լ�����Ѫ��
	public static MultipleGrh SCENEICON; //���ͼ��ļ���	
	public static MultipleGrh MSTAND; //�����վ��ͼƬ����
	public static MultipleGrh NPC;//NPC
	public static MultipleGrh MINITEMS;//�����е���Ʒͼ��
	
	
	public static void onLoadResource()
	{
		FACE=new MultipleGrh();
		FACE.onLoadResource("multiple/","multiple.xml");
		
		SCENEICON=new MultipleGrh();
		SCENEICON.onLoadResource("smallmap/","small.xml");
		
		MSTAND=new MultipleGrh();
		MSTAND.onLoadResource("monst/","m_s.xml");
		
		NPC=new MultipleGrh();
		NPC.onLoadResource("npc/","npcgrh.xml");
		
		MINITEMS=new MultipleGrh();
		MINITEMS.onLoadResource("minitems/","minitems.xml");
				
	}	
}
