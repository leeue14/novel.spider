package novel.spider.entitys;

import java.sql.Date;

/**
 * 
 * 功能: 小说实体
 * @author:李月
 * @Version:
 * @Date 2017年10月13日 下午9:17:27
 */
public class Novel {
	private String bookName;//书名
	private String author;//作者名
	private String url;//小说章节列表的链接
	private String bookCover;//小说封面
	private String bookDesc;//小说描述
	private String bookType;//小说类别
	private String lastUpdateChapter;//最后更新章节名
	private String lastUpdateChapterUrl;//最后更新章节url
	private Date lastUpdateChapterTime;//最后更新的时间
	private int status;//小说的状态 1 表示连载 2表示完结
	private char firstLetter;//小说的首字母
	private int platformId;//小说来自哪个网站
	private Date addTime;//这本小说存入我们的数据库是哪天
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBookCover() {
		return bookCover;
	}
	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}
	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}
	public String getLastUpdateChapterUrl() {
		return lastUpdateChapterUrl;
	}
	public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
		this.lastUpdateChapterUrl = lastUpdateChapterUrl;
	}
	public Date getLastUpdateChapterTime() {
		return lastUpdateChapterTime;
	}
	public void setLastUpdateChapterTime(Date lastUpdateChapterTime) {
		this.lastUpdateChapterTime = lastUpdateChapterTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public char getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(char firstLetter) {
		this.firstLetter = firstLetter;
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "Novel [bookName=" + bookName + ", author=" + author + ", url="
				+ url + ", bookCover=" + bookCover + ", bookDesc=" + bookDesc
				+ ", bookType=" + bookType + ", lastUpdateChapter="
				+ lastUpdateChapter + ", lastUpdateChapterUrl="
				+ lastUpdateChapterUrl + ", lastUpdateChapterTime="
				+ lastUpdateChapterTime + ", status=" + status
				+ ", firstLetter=" + firstLetter + ", platformId=" + platformId
				+ ", addTime=" + addTime + "]";
	}
	
	
	

}
