package cn.e3mall.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.search.mapper.SearchItemMapper;
import cn.e3mall.search.pojo.SearchItem;
import cn.e3mall.search.service.SearchService;
import cn.e3mall.utils.E3mallResult;

@Service
public class SearchServiceImpl implements SearchService {
	// 查询数据库
	@Autowired
	private SearchItemMapper searchItemMapper;
	// 添加索引库
	@Autowired
	private SolrServer solrServer;

	@Override
	public E3mallResult dataImport() {
		// 查询数据库
		List<SearchItem> searchList = searchItemMapper.dataImport();
		// 循环数据集合，把每一个数据放入索引库
		for (SearchItem searchItem : searchList) {
			// 创建文档对象，封装查询数据库数据集
			SolrInputDocument doc = new SolrInputDocument();
			// 封装 Id
			doc.addField("id", searchItem.getId());
			// 封装商品标题
			doc.addField("item_title", searchItem.getTitle());
			// 封装商品买点
			doc.addField("item_sell_point", searchItem.getSell_point());
			// 封装商品价格
			doc.addField("item_price", searchItem.getPrice());
			// 封装商品图片地址
			doc.addField("item_image", searchItem.getImage());
			// 商品类别
			doc.addField("item_category_name", searchItem.getCategory_name());
			// 商品描述
			doc.addField("item_desc", searchItem.getItem_desc());
			try {
				solrServer.add(doc);
				// 提交
				solrServer.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return E3mallResult.ok();
	}
}
