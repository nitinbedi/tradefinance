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


	@NotEmpty( message = "{trade.mandatory}")
	private String tradeId;
	@Min(value=1, message = "{trade.version.minvalue}")
	private int version;
	@NotEmpty(message = "counter Party ID cannot be null")
	private String counterPartyID;
	@NotEmpty(message = "book id cannot be null")
	private String bookid;

	@NotNull(message = "Maturity Date cannot be null")
	@PresentOrFuture(message = "{trade.maturityDate.notFutureDate}")
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
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
		return DateUtils.removeTimeFromDate(maturityDate);
	}

	public void setMaturityDate(Date maturityDate) {
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
