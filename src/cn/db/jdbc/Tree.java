package cn.db.jdbc;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class Tree {

	private int id;
	private String name;
	private int pid;
	private List<Tree> children;

	public Tree(int id, String name, int pid) {
		this.id = id;
		this.name = name;
		this.pid = pid;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public void spark() {
		System.out.println(this.id + "\t" + this.name + this.pid + "\t" + this.children);
	}

	public Tree() {
		super();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
