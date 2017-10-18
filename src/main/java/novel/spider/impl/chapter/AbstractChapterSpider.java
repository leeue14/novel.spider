package novel.spider.impl.chapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.utils.NovelSpiderUtil;

/**
 * 
 * ����:ץȡĳ��С˵�½ڵ�ȫ���б�� ʵ����
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����4:24:22
 */

public class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
	

	@Override
	public List<Chapter> getsChapter(String url) {
		try {
			String result = crawl(url);
		//	System.out.println(result);
			Document doc = Jsoup.parse(result);
			//����href��·��Ϊ����·��
			doc.setBaseUri(url);
			Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters = new ArrayList<>();
			for (Element a : as) {
				Chapter chapter = new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			return chapters;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
