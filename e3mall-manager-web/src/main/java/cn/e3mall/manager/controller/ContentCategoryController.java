package cn.e3mall.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.TreeBean;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	// 根据内容分类父Id，查询内容分类树形菜单
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeBean> findContentCategoryByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.findByParentId(parentId);
	}

	@RequestMapping("/content/category/create")
	@ResponseBody
	public E3mallResult createNode(Long parentId, String name) {
		return contentCategoryService.createNode(parentId, name);
	}

}
