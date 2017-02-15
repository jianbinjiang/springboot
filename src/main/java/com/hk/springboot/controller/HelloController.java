package com.hk.springboot.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "", description = "hello1111111111",tags="第一个demo")
public class HelloController {
	Logger log = Logger.getLogger(HelloController.class);

	@ApiOperation(value = "第一个helloworld", nickname = "demo")
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	// @ResponseBody
	public String hello(@PathVariable("name") String name, Model model) {
		model.addAttribute("name", name);
		log.info("------------------"+name);
		return "hello/hello";
	}

	@ApiOperation(value = "主页")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		return "hello world";
	}
	@ApiOperation(value = "http请求")
	@RequestMapping(value = "/httpclient", method = RequestMethod.POST)
	@ResponseBody
	public String httpclient(){
		String s = "{'name':'reiz','a':'1111'}";
		return s;
	}
}
