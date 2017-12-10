package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.manager.service.ItemParamService;
import cn.e3mall.utils.E3mallResult;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping(value = "/item/param/query/itemcatid/{id}")
	@ResponseBody
	public E3mallResult findByItemCategoryId(@PathVariable(value = "id") Long itemCatId) {
		return itemParamService.findByCategoryId(itemCatId);
	}
}
