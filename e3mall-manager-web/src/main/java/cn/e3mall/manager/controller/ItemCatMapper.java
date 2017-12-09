package cn.e3mall.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.manager.service.ItemCatService;
import cn.e3mall.utils.TreeBean;

@Controller
public class ItemCatMapper {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<TreeBean> list(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return itemCatService.findByParentId(parentId);
	}
}
