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
 * ����: ��ץȡ�����ȡ����ͬ��һ���������
 * 
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����4:25:22
 */
public class AbstractSpider {

	/**
	 * ���Ѹ���url���ʵ���ҳ��������ҳ����������
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String url) throws Exception {
		
		  CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		  CloseableHttpResponse httpResponse = httpClient.execute(new
		  NovelSpiderHttpGet(url));
	
/*		// ʹ�ô���ipץȡ��ҳ
		CloseableHttpClient httpClient = HttpClients.createDefault();// ����Ĭ��httpclientʵ��
		NovelSpiderHttpGet httpGet = new NovelSpiderHttpGet(url);
		HttpHost proxy = new HttpHost("182.90.50.132", 8123);//ʹ�ô���ip������
		RequestConfig requestConfig= RequestConfig.custom().setProxy(proxy).build();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse httpResponse =httpClient.execute(httpGet); // ִ��http get����
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
