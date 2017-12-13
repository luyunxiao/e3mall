package cn.e3mall.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.e3mall.search.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;

@Controller
public class SearchItemController {
	@Autowired
	private SearchService searchService;

	@RequestMapping("search")
	public String queryItemIndex(@RequestParam(value = "q") String qName,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "60") Integer rows,
			Model model) {
		// 给参数重新编码
		try {
			qName = new String(qName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SearchResult result = searchService.queryByIndex(qName, page, rows);
		// 回显查询参数
		model.addAttribute("query", qName);
		// 回显总页码
		model.addAttribute("totalPages", result.getTotalPages());
		// 回显商品列表
		model.addAttribute("itemList", result.getItemList());
		// 回显当前页
		model.addAttribute("page", result.getPage());
		return "search";
	}
}