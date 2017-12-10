package cn.e3mall.manager.service;

import cn.e3mall.utils.E3mallResult;

public interface ItemParamService {
	
	//根据category_id查询参数
	E3mallResult findByCategoryId(Long itemCatId);
}
