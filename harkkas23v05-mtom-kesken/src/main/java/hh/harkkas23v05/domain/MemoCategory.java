package hh.harkkas23v05.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




//many-to-many liitostaulu (Memo&Category)
@Entity
@Table(name = "memo_category") 
public class MemoCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long memocategoryid;
	
	@ManyToOne
	@JoinColumn(name="memoid")
	private Memo memo;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;

	public MemoCategory() {
		super();
	}

	public MemoCategory(Memo memo, Category category) {
		super();
		this.memo = memo;
		this.category = category;
	}

	public long getMemocategoryid() {
		return memocategoryid;
	}

	public void setMemocategoryid(long memocategoryid) {
		this.memocategoryid = memocategoryid;
	}

	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "MemoCategory [memocategoryid=" + memocategoryid + ", memo=" + memo + ", category=" + category + "]";
	}
	
	
	


}
