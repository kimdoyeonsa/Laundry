package com.example.web.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.web.DAO.LaundryDAO;
import com.example.web.DAO.ValueDAO;
import com.example.web.model.LaundryDTO;





@Controller
public class WebController {
	Map<String, Object> hmap = new HashMap<String, Object>();
	LaundryDAO ldao=LaundryDAO.getInstance();
	ValueDAO vdao=ValueDAO.getInstance();
	@RequestMapping("head.php")
	public void head(HttpServletRequest req,HttpServletResponse res) throws IOException {
		PrintWriter out=res.getWriter();
		ArrayList<String> arraystring=new ArrayList<String>();
		arraystring.add("<head>");
		arraystring.add("<meta name=\"viewport\" content=\"user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, height=device-height\" />");
		arraystring.add("<script src=\""+req.getContextPath()+"/resources/js/jquery/jquery-3.3.1.js\"></script>");
		arraystring.add("<script src=\""+req.getContextPath()+"/resources/bootstrap/js/bootstrap.js\"></script>");
		arraystring.add("<link href=\""+req.getContextPath()+"/resources/bootstrap/css/bootstrap.css\" rel=\"stylesheet\">");
		arraystring.add("<script src=\""+req.getContextPath()+"/resources/bootstrap-sweetalert/dist/sweetalert.js\"></script>");
		arraystring.add("<link href=\""+req.getContextPath()+"/resources/bootstrap-sweetalert/dist/sweetalert.css\" rel=\"stylesheet\">");
		arraystring.add("</head>");
		
		for(String str:arraystring) {
			out.println(str);
		}
		
	}
	@RequestMapping("index.php")	 
	public void index(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestDispatcher form;
		form=req.getRequestDispatcher("/head.php");
	    form.include(req, res);
		PrintWriter out=res.getWriter();
		out.println("<script>");
		out.println("function list(){"
				+"location.href=\"create.php\";"
				+ "}");
		out.println("</script>");
		out.println("<center>");
		out.println("<button class=\"btn btn-default\" width=\"80%\" onclick=\"javascript:list()\">¼¼Å¹¸ñ·Ï</button>");
		out.println("</center>");
	}
	@RequestMapping("create.php")
	public void create(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ldao.CreateLaundry("Laundry");
		res.sendRedirect("Form/list.php");
	}
	
	@RequestMapping("Form/list.php")
	public ModelAndView Formlist(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ModelAndView mnv=new ModelAndView();
		mnv.setViewName("form/list");
		String keyField="dong";
		if(req.getParameter("keyField")!=null) {
			keyField=req.getParameter("keyField");
		}
		hmap.put("keyField", keyField);
		String search="";
		if(req.getParameter("search")!=null) {
		 search=req.getParameter("search");
		 hmap.put("search", search);
		}
		else {
		hmap.put("search", "");
		}
		
		ArrayList<LaundryDTO> items=null;
		items=ldao.list("Laundry",keyField,search);
		hmap.put("items", items);
		return mnv.addAllObjects(hmap);
	}
	@RequestMapping("Form/insert.php")
	public String Forminsert(HttpServletRequest req, HttpServletResponse res) throws IOException {
		return "form/insert";
	}
	@RequestMapping("Form/update.php")
	public ModelAndView Formupdate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ModelAndView mnv=new ModelAndView();
		int idx=Integer.parseInt(req.getParameter("idx"));
		ArrayList<LaundryDTO> items=null;
		items=ldao.read("Laundry",idx);
		hmap.put("id", idx);
		hmap.put("items", items);
		mnv.setViewName("form/update");
		return mnv.addAllObjects(hmap);
	}
	@RequestMapping("insert/proc.php")
	public void insertproc(LaundryDTO ldto,HttpServletRequest req,HttpServletResponse res) throws IOException {
		PrintWriter out=res.getWriter();
		ldto.setId(vdao.select_int_nextval("Laundry", "Laundry", "id"));
		ldto.setDong(req.getParameter("dong"));
		ldto.setHosu(Integer.parseInt(req.getParameter("hosu")));
		ldto.setName(req.getParameter("name"));
		ldto.setAmount(req.getParameter("amount"));
		ldto.setPay(req.getParameter("pay"));
		ldto.setWork(req.getParameter("work"));
		ldto.setPhone(req.getParameter("phone"));
		ldto.setOutput(req.getParameter("output"));
		int flag=ldao.insert("Laundry", ldto);
		out.println("{\"result\":\""+flag+"\"}");
		
	}
	@RequestMapping("update/proc.php")
	public void updateproc(LaundryDTO ldto, HttpServletRequest req,HttpServletResponse res) throws IOException {
		PrintWriter out=res.getWriter();
		ldto.setDong(req.getParameter("dong"));
		ldto.setHosu(Integer.parseInt(req.getParameter("hosu")));
		ldto.setName(req.getParameter("name"));
		ldto.setAmount(req.getParameter("amount"));
		ldto.setPay(req.getParameter("pay"));
		ldto.setWork(req.getParameter("work"));
		ldto.setPhone(req.getParameter("phone"));
		ldto.setOutput(req.getParameter("output"));
		ldto.setId(Integer.parseInt(req.getParameter("id")));
		int flag=ldao.update("Laundry", ldto);
		out.println("{\"result\":\""+flag+"\"}");
	}
	@RequestMapping("delete/proc.php")
	public void deleteproc(LaundryDTO ldto, HttpServletRequest req,HttpServletResponse res) throws IOException {
		ldto.setId(Integer.parseInt(req.getParameter("id")));
		ldao.delete("Laundry", ldto);
		res.sendRedirect(req.getContextPath()+"/Form/list.php");
	}
	
}
