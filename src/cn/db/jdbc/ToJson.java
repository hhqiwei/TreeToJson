package cn.db.jdbc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class ToJson {

	public void treeToJson(List<Tree> list) {
		List<Tree> treeList = new ArrayList<Tree>();
		treeList = listToTree(list);// 调用函数，传入List<Tree>参数
		System.out.println("SUCCESS TO JSON.\n"+JSON.toJSONString(treeList));
	}

	// 用递归的方法
	public List<Tree> listToTree(List<Tree> list) {
		// 用递归找子节点
		List<Tree> treeList = new ArrayList<Tree>();
		for (Tree tree : list) {
			if (tree.getPid() == 0) {
				treeList.add(findChildren(tree, list));
			}
		}
		return treeList;
	}

	private Tree findChildren(Tree tree, List<Tree> list) {
		for (Tree node : list) {
			if (node.getPid() == tree.getId()) {
				if (tree.getChildren() == null) {
					tree.setChildren(new ArrayList<Tree>());
				}
				tree.getChildren().add(findChildren(node, list));
			}
		}
		return tree;
	}
}
