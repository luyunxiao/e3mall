package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/content/query/list")
	@ResponseBody
	public DataGridPageBean findByCategoryId(Long categoryId, Integer page, Integer rows) {
		return contentService.findByCategoryId(categoryId, page, rows);
	}

	@RequestMapping("/content/save")
	@ResponseBody
	public E3mallResult saveContent(TbContent content) {
		return contentService.saveContent(content);
	}
}
