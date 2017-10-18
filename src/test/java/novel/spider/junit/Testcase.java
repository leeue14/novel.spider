package novel.spider.junit;

import java.util.List;

import org.junit.Test;

import novel.spider.configuration.Configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.impl.chapter.AbstractChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.download.NovelDownload;
import novel.spider.impl.novel.KanShuZhongNovelSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.interfaces.INovelSpider;
import novel.spider.utils.NovelSpiderUtil;

public class Testcase {
	/**
	 * 测试笔趣阁的抓取圣墟文章目录
	 * @throws Exception
	 */
	@Test
	public void testGetBiQiuGeAllChapter() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter>  chapters  = spider.getsChapter("http://www.biqiuge.com/book/4772/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
	/**
	 * 测试顶点文学的抓取圣墟文章目录
	 * @throws Exception
	 */
	@Test
	public void testGetAllX23UsAllChapter() throws Exception{
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getsChapter("http://www.23us.com/html/66/66656/");
		for(Chapter chapter : chapters){
			System.out.println(chapter);
		}
	}
	/**
	 * 测试博看小说网的抓取圣墟文章目录
	 * @throws Exception
	 */
	@Test
	public void testBkxsAllChapter() throws Exception{
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getsChapter("http://www.bkxs.net/bkxs/48762/");
		for(Chapter chapter : chapters){
			System.out.println(chapter);
		}
	}
	

	
	/**
	 * 测试抓取顶点小说网某个小说的某一章内容容
	 * @throws Exception
	 */
	@Test
	public void testGetDetailChapter() throws Exception{
		IChapterDetailSpider spider = new AbstractChapterDetailSpider();
	//	ChapterDetail chapterDetail =  spider.getChapterDetail("http://www.23us.com/html/66/66656/27429411.html");
		ChapterDetail chapterDetail =  spider.getChapterDetail("http://www.kanshuzhong.com/book/115979/24711129.html");
		System.out.println(chapterDetail.toString());
	}
	/**
	 * 测试抓取笔趣阁某个小说的某一章内容容
	 * @throws Exception
	 */
	@Test
	public void testGetBiqugeDetailChapter() throws Exception{
		IChapterDetailSpider spider = new AbstractChapterDetailSpider();
		ChapterDetail chapterDetail =  spider.getChapterDetail("http://www.biqiuge.com/book/4772/2940354.html");
		System.out.println(chapterDetail.toString());
	}
	/**
	 * 测试抓取博看小说网某个小说的某一章内容容
	 * @throws Exception
	 */
	@Test
	public void testGetBkxsDetailChapter() throws Exception{
		IChapterDetailSpider spider = new AbstractChapterDetailSpider();
		ChapterDetail chapterDetail =  spider.getChapterDetail("http://www.bkxs.net/bkxs/48762/15766113.html");
		System.out.println(chapterDetail.toString());
	}
	
	/**
	 * 测试小说下载
	 */
	@Test
	public void testDownload() {
		INovelDownload download = new NovelDownload(); 
		Configuration config = new Configuration();
		config.setPath("G:/1");
		config.setSize(50);
		config.setTryTimes(10);;
		download.download("http://www.23us.com/html/66/66656/", config);
	}
	/**
	 * 测试爬小说简介。看书网
	 * @throws Exception
	 */
	@Test
	public void testSpiderImage() throws Exception{
		INovelSpider novelSpider = new KanShuZhongNovelSpider();
		novelSpider.getsNovelOther("http://www.kanshuzhong.com/book/86555/", 3);
	}
	
}
