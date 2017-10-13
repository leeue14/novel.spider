package novel.spider.configuration;

import java.io.Serializable;

/**
 * 
 * 功能:这个是下载小说的配置文件   
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午4:58:31
 */
public class Configuration implements Serializable{
	//默认每线程可以下载最大的章节数量是100
	public static final int DEFAULT_SIZE = 100;

	
	public static final int DEFAULT_TRY_TIMES = 3;//每个线程下载每一章所允许的最大尝试次数默认是3
	
	
	private String path;//下载后文件保存的地址
	
	private int size;//每个线程能够下载最大章节数量
	private int tryTimes;
	
	public Configuration() {
		this.size = DEFAULT_SIZE;//默认每个线程最大只能下载100个章节
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
