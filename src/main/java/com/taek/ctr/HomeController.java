package com.taek.ctr;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taek.calculator.originLogic.CalculatorOrigin;
import com.taek.vo.CalcVo;

import dao.JdbcContext;

@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/calculator", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> calculator(CalcVo calcVo) {
		logger.error("in calculator ");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		CalculatorOrigin calculatorOrigin = new CalculatorOrigin();
		Double resCalculate = 0.0;
		try {
			resCalculate = calculatorOrigin.calculate(calcVo.getNum1(), calcVo.getNum2(), calcVo.getOperator());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return map;
		}
		map.put("res", resCalculate);
		return map;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.error("Welcome home! The client locale is {}.", locale);
	//	System.out.println("dataSource : " + dataSource);
		return "home";
	}
	
}
