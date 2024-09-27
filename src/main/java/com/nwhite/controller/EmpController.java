package com.nwhite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwhite.bean.Employee;
import com.nwhite.dao.EmpDao;

@Controller
public class EmpController {
	@Autowired  
    EmpDao dao;//will inject dao from xml file
	
	@RequestMapping("/viewemp")  
    public String viewemp(Model m){  
        List<Employee> list=dao.getEmployees();  
        m.addAttribute("list",list);
        return "viewemp";  
    }
	
	@RequestMapping("/empform")  
    public String showform(Model m){  
    	m.addAttribute("command", new Employee());
    	return "empform"; 
    }

	@RequestMapping(value="/save",method = RequestMethod.POST)  
    public String save(@ModelAttribute("emp") Employee emp){  
        dao.save(emp);  
        return "redirect:/viewemp";
    }
	
	@RequestMapping(value="/editemp/{id}")  
    public String edit(@PathVariable int id, Model m){  
        Employee emp = dao.getEmpById(id);  
        m.addAttribute("command", emp);
        return "empeditform";  
    }
	
	@RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public String editsave(@ModelAttribute("emp") Employee emp){  
        dao.update(emp);  
        return "redirect:/viewemp";  
    }
	
	@RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
    public String delete(@PathVariable int id){  
        dao.delete(id);  
        return "redirect:/viewemp";  
    }
}
