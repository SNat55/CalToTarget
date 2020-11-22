package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.demo.model.ParamModel;

@RestController
public class DemoController {

	@PostMapping("cal/list")
	public ArrayList<List<Integer>> postCal(@Valid @RequestBody ParamModel req) {
		ArrayList<List<Integer>> result = new ArrayList<>();
		List<Integer> list;

		Collections.sort(req.setlist);

		int target, setI, resDiv, resMod, resD, resM;
		target = req.target;
		for (int i = 0; i < req.setlist.size(); i++) {
			setI = req.setlist.get(i);
			if (setI <= target) {
				List<Integer> divResult = divEqualZero(setI, target, req.setlist);
				if (divResult != null) {
					result.add(divResult);
				}
			}
		}

		return result;
	}

	public List<Integer> divEqualZero(Integer input, Integer target, List<Integer> setList) {
		Integer resDiv, resMod;
		List<Integer> list = null;
		resDiv = target / input;
		resMod = target % input;
		if (resMod == 0) {
			list = new ArrayList<Integer>();
			for (int j = 0; j < resDiv; j++) {
				list.add(input);
				Collections.sort(list);
			}
			return list;
		} else {
			return divNotEqualZero(input, target, setList);
		}
	}

	public List<Integer> divNotEqualZero(Integer input, Integer target, List<Integer> setList) {
		Integer setI, resDiv, resMod, resD, resM;
		List<Integer> list = null;
		resDiv = target / input;
		resMod = target % input;
		for (int k = 0; k < setList.size(); k++) {
			resD = resMod / setList.get(k);
			resM = resMod % setList.get(k);
			if (resM == 0) {
				list = new ArrayList<Integer>();
				for (int j = 0; j < resDiv; j++) {
					list.add(input);
				}
				for (int j = 0; j < resD; j++) {
					list.add(setList.get(k));
					Collections.sort(list);
				}
			} else if(resD>0)  {
			}
		}
		return list;
	}

}
