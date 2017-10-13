package novel.spider.configuration;

import java.io.Serializable;

/**
 * 
 * ����:���������С˵�������ļ�   
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����4:58:31
 */
public class Configuration implements Serializable{
	//Ĭ��ÿ�߳̿������������½�������100
	public static final int DEFAULT_SIZE = 100;

	
	public static final int DEFAULT_TRY_TIMES = 3;//ÿ���߳�����ÿһ�������������Դ���Ĭ����3
	
	
	private String path;//���غ��ļ�����ĵ�ַ
	
	private int size;//ÿ���߳��ܹ���������½�����
	private int tryTimes;
	
	public Configuration() {
		this.size = DEFAULT_SIZE;//Ĭ��ÿ���߳����ֻ������100���½�
		this.tryTimes = DEFAULT_TRY_TIMES;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTryTimes() {
		return tryTimes ;
	}

	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}
	
	
}
