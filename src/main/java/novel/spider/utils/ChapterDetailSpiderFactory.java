package novel.spider.utils;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * 
 * 功能: 实现一个工厂 类 通过给定url 返回一个实现好的IChapterDetailSpider的接口实现类
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午5:14:11
 */
public final class ChapterDetailSpiderFactory {
	private ChapterDetailSpiderFactory(){};//不可以被继承，不可以被实现
	
	public static IChapterDetailSpider getChapterDetailSpider(String url){//这个url是小说章节列表的url
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterDetailSpider chapterDetailSpider = null;
		switch(novelSiteEnum){//有的小说网站的章节列表不是有规则的要先排序，再返回。通用
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
