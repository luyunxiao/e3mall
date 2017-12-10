package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.pojo.TbContent;
import cn.e3mall.utils.ADItem;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;

public interface ContentService {

	// 根据分类id查询内容列表
	DataGridPageBean findByCategoryId(Long categoryId, Integer page, Integer rows);

	// 保存内容
	E3mallResult saveContent(TbContent content);

	// 根据分类id查询所有
	List<ADItem> findAllByCategoryId(Long categoryId);
}
