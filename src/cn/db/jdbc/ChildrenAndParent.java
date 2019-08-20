package cn.db.jdbc;

import java.util.List;

public class ChildrenAndParent {
	//创建私有的对象
	private int id;
	private String name;
	private List<ChildrenAndParent> children;
	
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
		System.out.println(this.id+"\t"+this.name+"\t"+this.children);
	}
}
