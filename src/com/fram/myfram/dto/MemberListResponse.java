package com.fram.myfram.dto;

import java.util.ArrayList;
import java.util.List;

public class MemberListResponse {
	
	private List<Member> data=new ArrayList<>();

	public List<Member> getData() {
		return data;
	}

	public void setData(List<Member> data) {
		this.data = data;
	}
	
	

}
