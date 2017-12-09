package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.TreeBean;

public interface ContentCategoryService {

	// 根据分类Id查询分类树形菜单
	List<TreeBean> findByParentId(Long parentId);

	// 创建节点
	E3mallResult createNode(Long parentId, String name);
}
