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
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.IDUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

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
	public E3mallResult saveItem(TbItem item, TbItemDesc itemDesc) {
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
		item.setPrice(item.getPrice() * 100);
		// 保存商品
		itemMapper.insert(item);
		// 补全商品描述信息属性
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		return E3mallResult.ok();
	}

}
