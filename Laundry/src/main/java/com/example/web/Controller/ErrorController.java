package com.example.web.Controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	@RequestMapping(value="/error/error{errno}.php")
public String error(Model model,@PathVariable String errno,HttpServletResponse res) {
		String msg="";
		res.setStatus(200);
		if(errno.equals("401")) {
			msg=errno+" ���� �Դϴ�.";
		}
		else if(errno.equals("403")) {
			msg=errno+" ���� �Դϴ�.";
		}
		else if(errno.equals("404")) {
			msg=errno+" ���� �Դϴ�."+"\n"+"��û�Ͻ� �������� ���� ���� �ʽ��ϴ�."+"\n"+"\"page not found\"";
		}
		else if(errno.equals("500")) {
			msg=errno+" ���� �Դϴ�.";
		}
		else {
	     msg="java.lang.Throwable ���� �Դϴ�.";
		}
	model.addAttribute("msg", msg);
	return "error/error";
}
	@RequestMapping(value="/error{errno}.php")
public void err(@PathVariable String errno,HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);
		RequestDispatcher form;
		form=req.getRequestDispatcher("/head.php");
		form.include(req, res);
		form=req.getRequestDispatcher("/error/error"+errno+".php");
		 form.include(req, res);
		
}
		
}
