package cn.e3mall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.e3mall.search.pojo.SearchResult;

public interface SearchItemDao {
	// 根据查询条件查询索引库
	SearchResult queryByIndex(SolrQuery query);
}
