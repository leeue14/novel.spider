package novel.spider.impl.chapter;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.ChapterDetail;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.utils.NovelSpiderUtil;
/**
 * 
 * ����:��ץȡһ���½���������ݣ�������һҳ ��һҳ����
 * @author:����
 * @Version:
 * @Date 2017��10��9�� ����11:10:35
 */
public class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider{

	@Override
	public ChapterDetail getChapterDetail(String url) {
		try {
			String result = super.crawl(url);
			result = result.replace("&nbsp;", "  ").replace("<br />", "${line}").replace("<br/>", "${line}");//�滻��ҳ�ϵĿո�ͻ��з�
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);//���þ���·��
			Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));

			//�ñ�������
			String titleSelector = contexts.get("chapter-detail-title-selector");
			String[] splits = titleSelector.split("\\,");
			splits = parseSelector(splits);
			ChapterDetail detail = new ChapterDetail();
			detail.setTitle((doc.select(splits[0]).get(Integer.parseInt(splits[1])).text()).replace("${line}", "\n"));
			
			//���½�����
			String contentSelector = contexts.get("chapter-detail-content-selector");
			detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "\n").
					replace("��Ȥ�� www.biqiuge.com��������ʥ�������½ڣ�", ""));
			
			//��ǰһ�µĵ�ַ
			String prevSelector = contexts.get("chapter-detail-prev-selector");
			splits = prevSelector.split("\\,");
			splits = parseSelector(splits);
			detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//�ú�һ�µĵ�ַ
			String nextSelector = contexts.get("chapter-detail-next-selector");
			splits = nextSelector.split("\\,");
			splits = parseSelector(splits);
			detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			return detail;
		
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	/**
	 * �������Ԫ�ص��±�����   ������û�й涨�±꣬ûд��Ĭ��Ϊ0
	 */
	private String[] parseSelector(String[] splits) {
		String[] newSplits = new String[2];
		if (splits.length == 1) {
			newSplits[0] = splits[0];
			newSplits[1] = "0";
			return newSplits;
		} else {
			return splits;
		}
	}

	
}
