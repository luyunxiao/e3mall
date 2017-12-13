package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.search.service.SearchService;
import cn.e3mall.utils.E3mallResult;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3mallResult dataImport() {
		return searchService.dataImport();
	}
}
