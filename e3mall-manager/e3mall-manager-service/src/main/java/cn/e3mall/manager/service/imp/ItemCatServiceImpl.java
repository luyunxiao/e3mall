package cn.e3mall.manager.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.manager.service.ItemCatService;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.utils.TreeBean;

public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TreeBean> findByParentId(Long parentId) {
		List<TreeBean> tList = new ArrayList<TreeBean>();
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		for (TbItemCat tbItemCat : list) {
			TreeBean treeBean = new TreeBean();
			treeBean.setId(tbItemCat.getId());
			treeBean.setText(tbItemCat.getName());
			treeBean.setState(tbItemCat.getIsParent() ? "closed" : "open");
			tList.add(treeBean);
		}
		return tList;
	}
}
