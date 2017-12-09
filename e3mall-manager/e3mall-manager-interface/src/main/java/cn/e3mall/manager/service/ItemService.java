package cn.e3mall.manager.service;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;

public interface ItemService {

	TbItem findById(Long id);

	DataGridPageBean findByPage(Integer page, Integer rows);

	E3mallResult saveItem(TbItem item, TbItemDesc itemDesc);
}
