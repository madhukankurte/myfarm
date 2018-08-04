package com.fram.myfram.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import com.fram.myfram.dao.MemberDAO;
import com.fram.myfram.dto.Member;
import com.fram.myfram.dto.Response;
import com.google.gson.Gson;

@Path("/MemberService")
public class MemberService {

	@POST
	@Path("/SaveMember")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveCrockery(Member member) {
		int result = 0;
		String resultmessage = null;
		Gson gson = new Gson();
		Response response = new Response();
		try {
			result = MemberDAO.saveMemer(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			response.setResult("success");
			resultmessage = gson.toJson(response);
		} else {
			response.setResult("fail");
			resultmessage = gson.toJson(response);
		}
		return resultmessage;
	}
	
	@GET
	@Path("/ReadMembers")
	@Produces("application/json")
	public String getCrockeryByCategory() {
		String analytics = null;
		List<Member> entries = new ArrayList<>();
		try {
			entries = MemberDAO.readAll();
			Gson gson = new Gson();
			analytics = gson.toJson(entries);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return analytics;
	}
}
