package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;

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

	@RequestMapping("list")
	@ResponseBody
	public DataGridPageBean findByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "30") Integer rows) {
		return itemService.findByPage(page, rows);
	}

	@RequestMapping("save")
	@ResponseBody
	public E3mallResult save(TbItem item, TbItemDesc itemDesc) {
		return itemService.saveItem(item, itemDesc);
	}
}
