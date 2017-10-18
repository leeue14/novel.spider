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
 * ���������ʵ�ַ���
 * ����: һ�������С˵�б�ץȡ ���Ѿ�ʵ����ץȡ����
 * @author:����
 * @Version:
 * @Date 2017��10��13�� ����9:47:33
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider{

	protected Element nextPageElement;//���ص���һ����jsoup���������ҳ
	protected String nextPage;//��һҳ
	
	/**
	 * Ĭ�ϵ�ץȡ���������᳢�� {@link INovelSpider#MAX_TRY_TIMES} ������
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
				String result = super.crawl(url);//�������ץȡ���Ľ�ȥ�½��б��url
				Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));//��ȡ�����վ�Ľ�������
				Document doc = Jsoup.parse(result);//������վ
				doc.setBaseUri(url);
				String imgSelct = context.get("novel-cover-selector");
				String[] splits = imgSelct.split("\\,");
				splits = parseSelector(splits);
				String imgUrl = doc.select(splits[0]).get(Integer.parseInt(splits[1])).getElementsByTag("img").first().absUrl("src");
				String descSelct = context.get("novel-desc-selector");
				splits = parseSelector(descSelct.split("\\,"));
				//�����е�С˵�����������ڲ��ڱ�ǩ���棬Ҫ����
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
