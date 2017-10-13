package novel.spider.utils;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * 
 * ����: ʵ��һ������ �� ͨ������url ����һ��ʵ�ֺõ�IChapterSpider�Ľӿ�ʵ����
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����5:14:11
 */
public final class ChapterSpiderFactory {
	private ChapterSpiderFactory(){};//�����Ա��̳У������Ա�ʵ��
	
	public static IChapterSpider getChapterSpider(String url){//���url��С˵�½��б��url
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = null;
		switch(novelSiteEnum){//�е�С˵��վ���½��б����й����Ҫ�������ٷ��ء�ͨ��
		case DingDianXiaoShuo : 
		case BiQuGe:
		case KanShuZhong:
		case BokanXiaoShuo:
			chapterSpider = new DefaultChapterSpider(); 
			break;
				
		}
		return chapterSpider;

	}

}
