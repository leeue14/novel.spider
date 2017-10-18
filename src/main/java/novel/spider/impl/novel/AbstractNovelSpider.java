package novel.spider.impl.novel;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.Doc;
import javax.swing.ListModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.NovelSiteEnum;
import novel.spider.entitys.Novel;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.utils.NovelSpiderUtil;
/**
 * 抽象类可以实现方法
 * 功能: 一个抽象的小说列表抓取 ，已经实现了抓取方法
 * @author:李月
 * @Version:
 * @Date 2017年10月13日 下午9:47:33
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider{

	protected Element nextPageElement;//返回的是一个用jsoup解析后的网页
	protected String nextPage;//下一页
	
	/**
	 * 默认的抓取方法，最多会尝试 {@link INovelSpider#MAX_TRY_TIMES} 次下载
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected Elements getsTr(String url) throws Exception {
		return getsTr(url, INovelSpider.MAX_TRY_TIMES);
	}
	
	private Elements getsTr(String url, int maxTryTimes) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Novel> getsNovel(String url, Integer maxTryTims) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getsNovelOther(String url, Integer maxTryTims) {
		List<String> others = new ArrayList<>();
		try {
			for(int i = 0; i < maxTryTims; i++){
				String result = super.crawl(url);//传入的是抓取到的进去章节列表的url
				Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));//获取这个网站的解析规则
				Document doc = Jsoup.parse(result);//解析网站
				doc.setBaseUri(url);
				String imgSelct = context.get("novel-cover-selector");
				String[] splits = imgSelct.split("\\,");
				splits = parseSelector(splits);
				String imgUrl = doc.select(splits[0]).get(Integer.parseInt(splits[1])).getElementsByTag("img").first().absUrl("src");
				String descSelct = context.get("novel-desc-selector");
				splits = parseSelector(descSelct.split("\\,"));
				//看书中的小说简介很难区别在不在标签里面，要区别开
				doc.select(splits[0]).get(Integer.parseInt(splits[1])).getElementsByTag("br").first().append("#");
				doc.select(splits[0]).get(Integer.parseInt(splits[1])).getElementsByTag("br").get(1).before("#");
				String descUrl = doc.select(splits[0]).get(Integer.parseInt(splits[1])).text().toString().split("#")[1];
				System.out.println(descUrl);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
