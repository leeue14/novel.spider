package novel.spider;

/**
 * 
 * 功能:已经被支持的小说网站
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午4:52:58
 */
public enum NovelSiteEnum {
	DingDianXiaoShuo(1, "23us.com"),
	BiQuGe(2, "biqiuge.com"),
	KanShuZhong(3,"kanshuzhong.com"),
	BokanXiaoShuo(4,"bkxs.net");
	private int id;
	private String url;
	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static NovelSiteEnum getEnumById(int id) {
		switch (id) {
		case 1 : return DingDianXiaoShuo;
		case 2 : return BiQuGe;
		case 3 : return KanShuZhong;
		case 4 : return BokanXiaoShuo;
		default : throw new RuntimeException("id=" + id + "是不被支持的小说网站");
		}
	}
	public static NovelSiteEnum getEnumByUrl(String url) {
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url=" + url + "是不被支持的小说网站");
	}
}
