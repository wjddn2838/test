package notice.model.vo;

import java.util.List;

public class PageDate {
	private List<Notice> noticeList;
	private String pageNavi;
	
	public PageDate() {}

	
	public List<Notice> getNoticeList() {
		return noticeList;
	}


	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}


	public String getPageNavi() {
		return pageNavi;
	}


	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}


	public PageDate(List<Notice> noticeList, String pageNavi) {
		super();
		this.noticeList = noticeList;
		this.pageNavi = pageNavi;
	}
	
	
}
