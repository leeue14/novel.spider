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
	 * ���Ա�Ȥ���ץȡʥ������Ŀ¼
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
	 * ���Զ�����ѧ��ץȡʥ������Ŀ¼
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
	 * ���Բ���С˵����ץȡʥ������Ŀ¼
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
	 * ����ץȡ����С˵��ĳ��С˵��ĳһ��������
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
	 * ����ץȡ��Ȥ��ĳ��С˵��ĳһ��������
	 * @throws Exception
	 */
	@Test
	public void testGetBiqugeDetailChapter() throws Exception{
		IChapterDetailSpider spider = new AbstractChapterDetailSpider();
		ChapterDetail chapterDetail =  spider.getChapterDetail("http://www.biqiuge.com/book/4772/2940354.html");
		System.out.println(chapterDetail.toString());
	}
	/**
	 * ����ץȡ����С˵��ĳ��С˵��ĳһ��������
	 * @throws Exception
	 */
	@Test
	public void testGetBkxsDetailChapter() throws Exception{
		IChapterDetailSpider spider = new AbstractChapterDetailSpider();
		ChapterDetail chapterDetail =  spider.getChapterDetail("http://www.bkxs.net/bkxs/48762/15766113.html");
		System.out.println(chapterDetail.toString());
	}
	
	/**
	 * ����С˵����
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
	 * ������С˵��顣������
	 * @throws Exception
	 */
	@Test
	public void testSpiderImage() throws Exception{
		INovelSpider novelSpider = new KanShuZhongNovelSpider();
		novelSpider.getsNovelOther("http://www.kanshuzhong.com/book/86555/", 3);
	}
	
}
