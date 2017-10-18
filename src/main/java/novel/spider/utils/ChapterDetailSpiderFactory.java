package novel.spider.utils;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * 
 * ����: ʵ��һ������ �� ͨ������url ����һ��ʵ�ֺõ�IChapterDetailSpider�Ľӿ�ʵ����
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����5:14:11
 */
public final class ChapterDetailSpiderFactory {
	private ChapterDetailSpiderFactory(){};//�����Ա��̳У������Ա�ʵ��
	
	public static IChapterDetailSpider getChapterDetailSpider(String url){//���url��С˵�½��б��url
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterDetailSpider chapterDetailSpider = null;
		switch(novelSiteEnum){//�е�С˵��վ���½��б����й����Ҫ�������ٷ��ء�ͨ��
		case DingDianXiaoShuo : 
		case BiQuGe:
		case KanShuZhong:
		case BokanXiaoShuo:
			chapterDetailSpider = new DefaultChapterDetailSpider(); 
			break;
				
		}
		return chapterDetailSpider;

	}

}
