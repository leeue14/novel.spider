package novel.spider.utils;

import java.net.URI;
import java.net.URL;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

/**
 * 
 * ����: ��������ʱ�� �������ʧ���˻�Ҫ��������
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����7:06:11
 */
public class NovelSpiderHttpGet extends HttpGet{

	public NovelSpiderHttpGet() {
	}
	public NovelSpiderHttpGet(URI uri){
		super(uri);
	}
	public NovelSpiderHttpGet(String url){
		super(url);
		setDefaultConfig();
	}
	/**
	 * ����Ĭ���������
	 */
	private void setDefaultConfig() {
		this.setConfig(RequestConfig.custom().setSocketTimeout(2_000)
				.setConnectTimeout(10_000)//�������ӷ�������ʱʱ��
				.setConnectionRequestTimeout(10_000)//���ôӷ�������ȡ���ݵĳ�ʱʱ��
				.build()
				);
		this.setHeader("User-Agent", "NovelSpider");	//��������ͷ
		
	}
}
