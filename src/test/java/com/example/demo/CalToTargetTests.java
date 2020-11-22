package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.Assert;

import com.example.demo.model.ParamModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SpringBootTest
@AutoConfigureMockMvc
class CalToTargetTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void isOkReq() throws Exception {
		ParamModel param = new ParamModel();
		param.setlist = new ArrayList<Integer>();
		param.setlist.add(2);
		param.setlist.add(3);
		param.setlist.add(5);
		param.target = 5;

		MockHttpServletRequestBuilder request = post("/cal/list").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(param));
		ResultActions result = mvc.perform(request);
		result.andExpect(status().isOk());
		
		String resultString = result.andReturn().getResponse().getContentAsString();
		Gson gson = new Gson();
		ArrayList<List<Integer>> fromJson = gson.fromJson(resultString, new TypeToken<ArrayList<List<Integer>>>() {
		}.getType());
		for (int i = 0; i < fromJson.size(); i++) {
			List<Integer> arrayList = fromJson.get(i);
			int sum = 0;
			for (int j = 0; j < arrayList.size(); j++)
				sum += arrayList.get(j);
			System.out.println("sum: " + sum);
			Assert.isTrue(sum == param.target, "");
		}
	}
	
	@Test
	public void isNotPostiveValue() throws Exception {
		ParamModel param = new ParamModel();
		param.setlist = new ArrayList<Integer>();
		param.setlist.add(-2);
		param.setlist.add(3);
		param.setlist.add(5);
		param.target = 5;

		MockHttpServletRequestBuilder request = post("/cal/list").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(param));
		ResultActions result = mvc.perform(request);
		
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void isTargetNull() throws Exception {
		ParamModel param = new ParamModel();
		param.setlist = new ArrayList<Integer>();
		param.setlist.add(2);
		param.setlist.add(3);
		param.setlist.add(5);

		MockHttpServletRequestBuilder request = post("/cal/list").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(param));
		ResultActions result = mvc.perform(request);
		
		result.andExpect(status().isBadRequest());
	}
	
	
	public static String toJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
