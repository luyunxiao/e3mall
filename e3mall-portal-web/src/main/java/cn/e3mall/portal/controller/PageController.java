package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.utils.ADItem;
import cn.e3mall.utils.JsonUtils;

@Controller
public class PageController {

	@Autowired
	private ContentService contentService;

	@Value("${BIG_CATEGORY_ID}")
	private Long BIG_CATEGORY_ID;

	@RequestMapping("index")
	public String showIndex(Model model) {
		List<ADItem> bigADs = contentService.findAllByCategoryId(BIG_CATEGORY_ID);
		// 前台页面获取 json 格式数组数据
		String objectToJson = JsonUtils.objectToJson(bigADs);
		// 页面需要回显数据
		model.addAttribute("ad1", objectToJson);
		return "index";
	}
}
