package hh.harkkas23v05.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	
	//memo category
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryid;
	
	private String categoryname;
	

	//many-to-many
	@ManyToMany(mappedBy = "categories")
	private Set<Memo> memos = new HashSet<>();


	public Category() {
		super();
	}


	public Category(String categoryname) {
		super();
		this.categoryname = categoryname;
	}


	public long getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}


	public String getCategoryname() {
		return categoryname;
	}


	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	public Set<Memo> getMemos() {
		return memos;
	}


	public void setMemos(Set<Memo> memos) {
		this.memos = memos;
	}


	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + "]";
	}
	
	
	
}


