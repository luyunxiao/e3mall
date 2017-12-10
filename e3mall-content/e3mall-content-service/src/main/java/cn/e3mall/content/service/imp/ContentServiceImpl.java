package cn.e3mall.content.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import cn.e3mall.utils.ADItem;
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

	@Value("${WIDTH}")
	private Integer WIDTH;
	@Value("${WIDTHB}")
	private Integer WIDTHB;
	@Value("${HEIGHT}")
	private Integer HEIGHT;
	@Value("${HEIGHTB}")
	private Integer HEIGHTB;

	@Override
	public List<ADItem> findAllByCategoryId(Long categoryId) {
		// 创建example对象
		TbContentExample example = new TbContentExample();
		// 设置查询参数
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		// 查询
		List<TbContent> cList = contentMapper.selectByExample(example);
		// 创建广告集合，封装首页分类数据
		ArrayList<ADItem> list = new ArrayList<ADItem>();
		// 循环，封装首页分类数据
		for (TbContent content : cList) {
			// 创建AdItem对象
			ADItem adItem = new ADItem();
			// 设置商品提示
			adItem.setAlt(content.getTitle());
			adItem.setHeight(HEIGHT);
			adItem.setHeightB(HEIGHTB);
			// 设置商品购买地址
			adItem.setHref(content.getUrl());
			// 设置首页商品图片地址
			adItem.setSrc(content.getPic());
			// 设置首页商品图片地址2
			adItem.setSrcB(content.getPic2());
			adItem.setWidth(WIDTH);
			adItem.setWidthB(WIDTHB);
			// 把ad放入adList集合
			list.add(adItem);
		}
		return list;
	}

}
