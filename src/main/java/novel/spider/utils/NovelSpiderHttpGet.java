package novel.spider.utils;

import java.net.URI;
import java.net.URL;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

/**
 * 
 * 功能: 设置请求时间 如果请求失败了还要继续请求
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午7:06:11
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
	 * 设置默认请求参数
	 */
	private void setDefaultConfig() {
		this.setConfig(RequestConfig.custom().setSocketTimeout(2_000)
				.setConnectTimeout(10_000)//设置连接服务器超时时间
				.setConnectionRequestTimeout(10_000)//设置从服务器读取数据的超时时间
				.build()
				);
		this.setHeader("User-Agent", "NovelSpider");	//设置请求头
		
	}
}
