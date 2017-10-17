package com.digix.memories.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="memory_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="memory")
public class Memory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8543504211851505568L;

	@Id
	@SequenceGenerator(name="memory_id_seq", sequenceName="memory_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="memory_id_seq")
	@Column(name = "id", updatable=false, nullable=false)
	private long id;
	
	@Column(name = "url")
	private String url;
	
//	@ElementCollection
	@Column(name = "taglist")
	private String taglist;
	
	@Column(name = "owner")
	private long owner;
	
	@Column(name="date")
	private Date memDate;
	
	public Memory(){
//		this.taglist = new ArrayList<String>();
		this.memDate = new Date(System.currentTimeMillis());
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
//	public List<String> getTaglist() {
//		return taglist;
//	}
//	
//	public void setTaglist(List<String> taglist) {
//		this.taglist = taglist;
//	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getOwner() {
		return owner;
	}
	
	public void setOwner(long owner) {
		this.owner = owner;
	}
	
//	public void addTag(String tag) {
//		this.taglist.add(tag);
//	}
//	
//	public void removeTag(String tag) {
//		int idx = this.taglist.indexOf(tag);
//		this.taglist.remove(idx);
//	}
	
	public Date getMemDate() {
		return memDate;
	}

	public String getTaglist() {
		return taglist;
	}

	public void setTaglist(String taglist) {
		this.taglist = taglist;
	}

	public void setMemDate(Date memDate) {
		this.memDate = memDate;
	}
	
}
