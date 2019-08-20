package cn.db.jdbc;

public class ChildrenAndParent {
	//创建私有的对象
	private int id;
	private String name;
	private int childrenId;
	
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
	
	
	public int getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(int childrenId) {
		this.childrenId = childrenId;
	}
	public void spark() {
		System.out.println(this.id+"\t"+this.name+"\t"+this.childrenId);
	}
}
