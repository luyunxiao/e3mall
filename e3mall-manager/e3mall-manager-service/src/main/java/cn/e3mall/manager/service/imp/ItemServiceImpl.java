package cn.e3mall.manager.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.mapper.TbItemParamItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemParamItem;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.IDUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem findById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public DataGridPageBean findByPage(Integer page, Integer rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		DataGridPageBean pageBean = new DataGridPageBean();
		pageBean.setRows(list);
		pageBean.setTotal(pageInfo.getTotal());
		return pageBean;
	}

	@Override
	public E3mallResult saveItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) {
		// 补全属性
		// 设置Id
		// 使用工具类生成ID
		long id = IDUtils.genItemId();
		item.setId(id);
		// 补全时间类型
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte) 1);
		// 保存商品
		itemMapper.insert(item);
		// 补全商品描述信息属性
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		// 补全商品参数信息
		itemParamItem.setItemId(id);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItemMapper.insert(itemParamItem);
		return E3mallResult.ok();
	}

}
