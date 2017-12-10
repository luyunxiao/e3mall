package cn.e3mall.manager.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.manager.service.ItemParamService;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamExample.Criteria;
import cn.e3mall.utils.E3mallResult;

public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public E3mallResult findByCategoryId(Long itemCatId) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		TbItemParam tbItemParam = null;
		if (list != null && list.size() == 1) {
			tbItemParam = list.get(0);
		}
		return E3mallResult.ok(tbItemParam);
	}

}
