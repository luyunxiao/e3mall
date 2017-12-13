package cn.e3mall.search.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	private Integer totalPages;
	private Integer page;
	private List<SearchItem> itemList;
	private Long totalCount;

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
