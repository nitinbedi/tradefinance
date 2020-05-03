package com.db.tradefinance.common.model;

import com.db.tradefinance.config.AppConfiguration;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Date;

public abstract class EntityBase{
    
	public static final Integer STATUS_DELETED = -999;
	@Id
    private String id;


    
    protected Integer status;

	private Date modifiedDate;

	private Date createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public Date getCreatedDate() {
		if(createdDate==null)
			this.setCreatedDate();
		return createdDate;
	}

	public void setCreatedDate() {
		setCreatedDate(null);
	}

	public void setCreatedDate(Date createdDate) {
		if(createdDate==null)
			this.createdDate =Calendar.getInstance().getTime();
		else
			this.createdDate = createdDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate() {
		Date modifiedDate = Calendar.getInstance().getTime();
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBase other = (EntityBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		System.out.println(" equal method working? "+ this.getId() + " " + other.getId());
		return true;
	}

	@Override
	public String toString() {
		return "EntityBase{" +
				"id='" + id + '\'' +
				", status=" + status +
				", modifiedDate=" + modifiedDate +
				", createdDate=" + createdDate +
				'}';
	}
}
