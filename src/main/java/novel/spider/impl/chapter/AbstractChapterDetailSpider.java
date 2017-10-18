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
 * 功能:获抓取一个章节里面的内容，包括上一页 下一页链接
 * @author:李月
 * @Version:
 * @Date 2017年10月9日 下午11:10:35
 */
public class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider{

	@Override
	public ChapterDetail getChapterDetail(String url) {
		try {
			String result = super.crawl(url);
			result = result.replace("&nbsp;", "  ").replace("<br />", "${line}").replace("<br/>", "${line}");//替换网页上的空格和换行符
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);//设置绝对路径
			Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));

			//拿标题内容
			String titleSelector = contexts.get("chapter-detail-title-selector");
			String[] splits = titleSelector.split("\\,");
			splits = parseSelector(splits);
			ChapterDetail detail = new ChapterDetail();
			detail.setTitle((doc.select(splits[0]).get(Integer.parseInt(splits[1])).text()).replace("${line}", "\n"));
			
			//拿章节内容
			String contentSelector = contexts.get("chapter-detail-content-selector");
			detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "\n").
					replace("笔趣阁 www.biqiuge.com，最快更新圣墟最新章节！", ""));
			
			//拿前一章的地址
			String prevSelector = contexts.get("chapter-detail-prev-selector");
			splits = prevSelector.split("\\,");
			splits = parseSelector(splits);
			detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//拿后一章的地址
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
	 * 处理具体元素的下标索引   不管有没有规定下标，没写的默认为0
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
