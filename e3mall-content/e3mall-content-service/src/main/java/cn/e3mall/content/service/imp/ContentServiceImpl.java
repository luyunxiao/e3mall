package cn.e3mall.content.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import cn.e3mall.utils.DataGridPageBean;
import cn.e3mall.utils.E3mallResult;

public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public DataGridPageBean findByCategoryId(Long categoryId, Integer page, Integer rows) {
		// 创建example对象
		DataGridPageBean pageBean = new DataGridPageBean();
		TbContentExample example = new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		// 设置分页参数
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		// 创建pageinfo对象，获取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		// 构造返回值对象
		pageBean.setRows(list);
		pageBean.setTotal(pageInfo.getTotal());
		return pageBean;
	}

	@Override
	public E3mallResult saveContent(TbContent content) {
		// 补全属性
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		return E3mallResult.ok();
	}

}
