package cn.e3mall.content.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.TreeBean;

public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<TreeBean> findByParentId(Long parentId) {
		// 创建List<EasyUITreeNode>集合，封装结果集数据
		List<TreeBean> tList = new ArrayList<TreeBean>();
		// 创建example对象
		TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
		Criteria createCriteria = contentCategoryExample.createCriteria();
		// 设置参数
		createCriteria.andParentIdEqualTo(parentId);
		// 查询
		List<TbContentCategory> selectByExample = contentCategoryMapper.selectByExample(contentCategoryExample);
		// 循环list集合，封装节点数据
		for (TbContentCategory tbContentCategory : selectByExample) {
			// 把数据集封装EasyUITreeNode
			TreeBean treeBean = new TreeBean();
			treeBean.setId(tbContentCategory.getId());
			treeBean.setText(tbContentCategory.getName());
			treeBean.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			tList.add(treeBean);
		}
		return tList;
	}

	@Override
	public E3mallResult createNode(Long parentId, String name) {
		// 保存内容分类表，补全属性
		TbContentCategory contentCategory = new TbContentCategory();
		// 设置属性参数
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		// 补全属性
		// 状态。可选值:1(正常),2(删除)
		contentCategory.setStatus(1);
		// 取值范围:大于零的整数
		contentCategory.setSortOrder(1);
		// 该类目是否为父类目，1为true，0为false
		contentCategory.setIsParent(false);
		// 时间
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		// 保存:返回主键
		contentCategoryMapper.insert(contentCategory);
		// 如果创建节点父节点是子节点？修改节点状态
		// 根据Id查询父节点状态
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		// 判断当前节点是否是父节点
		if (!parent.getIsParent()) {
			// 子节点，修改节点状态
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKeySelective(parent);
		}
		return E3mallResult.ok(contentCategory);
	}

}
