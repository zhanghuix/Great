package com.hecha.wap.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.nutz.http.Http;

public class BillControllerTest extends BaseTest {

	@Test
	public void billList() {
		// send
		String url = "http://localhost:8080/market-wap/mobile/bill/billList";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", "8");
		paramMap.put("billchannel", "0");
//		paramMap.put("billret", "10000027");
		try {
			// URLEncoder
			paramMap = paramToURLEncoder(paramMap, "UTF-8");

			url = paramToUrl(url, paramMap);
			System.out.println("url:" + url);
			// return
			String result = Http.get(url).getContent();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
