package cn.db.jdbc;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class ChildrenAndParent {

	private int id;
	private String name;
	private List<ChildrenAndParent> children;

	public ChildrenAndParent(int id, String name, List<ChildrenAndParent> children) {
		this.id = id;
		this.name = name;
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ChildrenAndParent> getChildren() {
		return children;
	}

	public void setChildren(List<ChildrenAndParent> children) {
		this.children = children;
	}

	public void spark() {
		System.out.println(this.id + "\t" + this.name + "\t" + this.children);
	}
	
	public ChildrenAndParent() {
		super();
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
