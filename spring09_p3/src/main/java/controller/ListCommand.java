package controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	//input에 입력한 문자열을 Date타입으로 변환
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date from;
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date to;
	

	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
	
	
}
