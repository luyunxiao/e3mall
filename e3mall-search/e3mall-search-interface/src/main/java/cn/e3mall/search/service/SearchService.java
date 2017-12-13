package cn.e3mall.search.service;

import cn.e3mall.search.pojo.SearchResult;
import cn.e3mall.utils.E3mallResult;

public interface SearchService {

	E3mallResult dataImport();

	SearchResult queryByIndex(String qName, Integer page, Integer rows);
}
