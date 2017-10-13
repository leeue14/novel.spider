package novel.spider.utils;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * 
 * 功能: 实现一个工厂 类 通过给定url 返回一个实现好的IChapterSpider的接口实现类
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午5:14:11
 */
public final class ChapterSpiderFactory {
	private ChapterSpiderFactory(){};//不可以被继承，不可以被实现
	
	public static IChapterSpider getChapterSpider(String url){//这个url是小说章节列表的url
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = null;
		switch(novelSiteEnum){//有的小说网站的章节列表不是有规则的要先排序，再返回。通用
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
