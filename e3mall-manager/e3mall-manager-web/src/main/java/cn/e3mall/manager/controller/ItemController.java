package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.pojo.TbItem;

@Controller
@RequestMapping("/item/")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("findById/{id}")
	@ResponseBody
	public TbItem fingById(@PathVariable Long id) {
		return itemService.findById(id);
	}
}
