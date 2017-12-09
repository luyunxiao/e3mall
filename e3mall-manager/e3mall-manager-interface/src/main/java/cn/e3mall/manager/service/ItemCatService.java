package cn.e3mall.manager.service;

import java.util.List;

import cn.e3mall.utils.TreeBean;

public interface ItemCatService {
	List<TreeBean> findByParentId(Long parentId);
}
