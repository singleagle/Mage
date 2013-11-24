package com.enjoy.mage.data;
import java.util.ArrayList;

/**
 * @author shaoleibo
 * �����б�   
 */
public class HeroTaskList {
	public ArrayList<Task> mTasks=new ArrayList<Task>();// ���ɽ���������б�
	public ArrayList<Task> mHistorys=new ArrayList<Task>(); //����������б�
	
	//�Ƿ�Ϊ��ʼ״̬ û���κ�����������
	public boolean isInit()
	{
		if(mTasks.size()==0&&mHistorys.size()==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//��������
	public void addTask(Task task)
	{
		mTasks.add(task);
	}
	
	private void removeTask(Task task)
	{
		mTasks.remove(task);
	}
	
	//��������Ѿ���� ����ӵ���������б���  ���Ҵӵ�ǰ�����б��Ƴ�
    public void addHistory(Task task)
    {
    	mHistorys.add(task);
    	removeTask(task);
    }
	
}
