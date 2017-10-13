package novel.spider.impl;

import java.util.List;

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.utils.NovelSpiderHttpGet;
import novel.spider.utils.NovelSpiderUtil;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 功能: 将抓取的类抽取出共同的一个父类出来
 * 
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午4:25:22
 */
public class AbstractSpider {

	/**
	 * 从已给的url访问到网页，返回网页的所有内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String url) throws Exception {
		
		  CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		  CloseableHttpResponse httpResponse = httpClient.execute(new
		  NovelSpiderHttpGet(url));
	
/*		// 使用代理ip抓取网页
		CloseableHttpClient httpClient = HttpClients.createDefault();// 创建默认httpclient实例
		NovelSpiderHttpGet httpGet = new NovelSpiderHttpGet(url);
		HttpHost proxy = new HttpHost("182.90.50.132", 8123);//使用代理ip爬数据
		RequestConfig requestConfig= RequestConfig.custom().setProxy(proxy).build();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse httpResponse =httpClient.execute(httpGet); // 执行http get请求
*/		
		try {
			String result = EntityUtils.toString(httpResponse.getEntity(),NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url))
							.get("charset"));
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpResponse.close();
			httpClient.close();
		}
	}

}
