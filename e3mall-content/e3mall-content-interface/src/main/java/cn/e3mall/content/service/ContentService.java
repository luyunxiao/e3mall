package cn.e3mall.content.service;

import cn.e3mall.utils.DataGridPageBean;

public interface ContentService {

	// 根据分类id查询内容列表
	DataGridPageBean findByCategoryId(Long categoryId, Integer page, Integer rows);
}
