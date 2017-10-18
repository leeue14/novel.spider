package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entitys.Novel;

/**
 * 
 * 功能: 抓取小说网站的全部书籍列表
 * @author:李月
 * @Version:
 * @Date 2017年10月13日 下午9:06:02
 */

public interface INovelSpider {
	
	/**抓取某个页面时最大尝试次数**/
	public static final int MAX_TRY_TIMES = 3;
	/**
	 *传入一个小说网站的书库的url就会返回一堆小说实体
	 *
	 * @param url
	 * @param maxTryTims 网页下载的最大次数 (允许失败重试的次数)
	 * @return
	 */
	public List<Novel> getsNovel(String url, Integer maxTryTims);
	/**
	 * 判断是否还有下一页小说实体
	 * @return
	 */
	public boolean hasNext();
	/**
	 * 跳到下一页小说列表页面
	 * @return
	 */
	public String next();
	/**
	 * 这个是小说迭代器，把每一页的小说都拿出来
	 * @param firstPage
	 * @param maxTryTimes
	 * @return
	 */
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);
	
	/**
	 * 获取小说的封面和简介
	 * @return
	 */
	public List<String> getsNovelOther(String url, Integer maxTryTims);
	

}
