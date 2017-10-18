package novel.spider.entitys;

import java.sql.Date;

/**
 * 
 * ����: С˵ʵ��
 * @author:����
 * @Version:
 * @Date 2017��10��13�� ����9:17:27
 */
public class Novel {
	private String bookName;//����
	private String author;//������
	private String url;//С˵�½��б������
	private String bookCover;//С˵����
	private String bookDesc;//С˵����
	private String bookType;//С˵���
	private String lastUpdateChapter;//�������½���
	private String lastUpdateChapterUrl;//�������½�url
	private Date lastUpdateChapterTime;//�����µ�ʱ��
	private int status;//С˵��״̬ 1 ��ʾ���� 2��ʾ���
	private char firstLetter;//С˵������ĸ
	private int platformId;//С˵�����ĸ���վ
	private Date addTime;//�ⱾС˵�������ǵ����ݿ�������
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
