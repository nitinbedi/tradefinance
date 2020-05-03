package com.db.tradefinance.model;

import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.validation.PresentOrFuture;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import com.db.tradefinance.common.model.EntityBase;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;


@Document(collection = "Trade")
public class Trade extends EntityBase {


	@NotEmpty( message = "NotEmpty.trade.tradeId")
	private String tradeId;

	@NotNull(message = "NotNull.trade.version")
	@Min(value=1, message = "trade.Version.minvalue")
	private Integer version;
	@NotEmpty(message = "NotEmpty.trade.counterPartyID")
	private String counterPartyID;
	@NotEmpty(message = "NotEmpty.trade.bookid")
	private String bookid;

	@NotNull(message = "NotNull.trade.maturityDate")
	@PresentOrFuture(message = "trade.maturityDate.notFutureDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maturityDate;

	private List<ErrorObject> errors;

	private String expired;

	public List<ErrorObject> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorObject> errors) {
		this.errors = errors;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCounterPartyID() {
		return counterPartyID;
	}

	public void setCounterPartyID(String counterPartyID) {
		this.counterPartyID = counterPartyID;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public Date getMaturityDate() {
		if(maturityDate==null)
			return null;
		return DateUtils.removeTimeFromDate(maturityDate);
	}

	public void setMaturityDate(Date maturityDate) {
		if(maturityDate==null)
			return;
		this.maturityDate = DateUtils.removeTimeFromDate(maturityDate);
	}



	public String getExpired() {
		if (this.expired==null)
			setExpired();
		return expired;
	}

	public void setExpired() {
		String expired = DateUtils.presentOrFutureDate(getMaturityDate()) ? "N" : "Y";
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Trade{" +
				" id='" + getId() + '\'' +
				", tradeId='" + tradeId + '\'' +
				", version=" + version +
				", counterPartyID='" + counterPartyID + '\'' +
				", bookid='" + bookid + '\'' +
				", maturityDate=" + maturityDate +
				", expired='" + expired + '\'' +
				'}';
	}
}
