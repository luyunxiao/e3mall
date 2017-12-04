package cn.e3mall.manager.service;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.utils.DataGridPageBean;

public interface ItemService {

	TbItem findById(Long id);

	DataGridPageBean findByPage(Integer page, Integer rows);
}
