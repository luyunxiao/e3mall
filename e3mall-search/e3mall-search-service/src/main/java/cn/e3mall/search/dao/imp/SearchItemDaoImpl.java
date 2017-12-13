package cn.e3mall.search.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.search.dao.SearchItemDao;
import cn.e3mall.search.pojo.SearchItem;
import cn.e3mall.search.pojo.SearchResult;

@Repository
public class SearchItemDaoImpl implements SearchItemDao {

	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult queryByIndex(SolrQuery query) {
		// 创建 SearchResult 封装结果
		SearchResult result = new SearchResult();
		// 定义集合，封装商品搜索对象
		List<SearchItem> searchList = new ArrayList<SearchItem>();
		// 根据封装参数对象 SolrQueyr 进行查询
		try {
			QueryResponse response = solrServer.query(query);
			// 获取查询结果集
			SolrDocumentList results = response.getResults();
			// 获取查询总记录数
			long numFound = results.getNumFound();
			result.setTotalCount(numFound);
			// 循环文档集合，把文件集合数据设置 pojo，在 pojo 放入 list 集合
			for (SolrDocument solrDocument : results) {
				// 创建 SearchItem，封装属性值
				SearchItem item = new SearchItem();
				// 获取索引库 Id
				String id = (String) solrDocument.get("id");
				item.setId(Long.parseLong(id));
				// 获取商品标题
				String title = (String) solrDocument.get("item_title");
				// 获取高亮显示
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				Map<String, List<String>> maps = highlighting.get(id);
				List<String> list = maps.get("item_title");
				if (list != null && list.size() > 0) {
					title = list.get(0);
				}
				item.setTitle(title);
				// 获取索引库中商品买点
				String sellPoint = (String) solrDocument.get("item_sell_point");
				item.setSell_point(sellPoint);
				// 获取索引库商品价格
				Long price = (Long) solrDocument.get("item_price");
				item.setPrice(price);
				// 获取图片地址
				String image = (String) solrDocument.get("item_image");
				item.setImage(image);
				// 获取商品类别名称
				String categoryName = (String) solrDocument.get("item_category_name");
				item.setCategory_name(categoryName);
				// 获取商品描述信息
				String desc = (String) solrDocument.get("item_desc");
				item.setItem_desc(desc);
				// 把索引库数据放入 pojo，把 pojo 放入集合
				searchList.add(item);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setItemList(searchList);
		return result;
	}
}
