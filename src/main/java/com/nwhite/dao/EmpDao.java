package com.nwhite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nwhite.bean.Employee;

public class EmpDao {
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}

	public List<Employee> getEmployees(){  
	    return template.query("select * from Emp99",new RowMapper<Employee>(){  
	        public Employee mapRow(ResultSet rs, int row) throws SQLException {  
	            Employee e=new Employee();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setSalary(rs.getFloat(3));  
	            e.setDesignation(rs.getString(4));  
	            return e;  
	        }  
	    });  
	}
	
	public int save(Employee e){  
	    String sql="insert into Emp99(name,salary,designation) values('" + e.getName() + "'," + e.getSalary() + ",'" + e.getDesignation() + "')";  
	    return template.update(sql);  
	}
	
	public int update(Employee e){  
	    String sql="update Emp99 set name='" + e.getName()+"', salary=" + e.getSalary() + ",designation='" + e.getDesignation() + "' where id=" + e.getId() + "";  
	    return template.update(sql);  
	}  
	public int delete(int id){  
	    String sql="delete from Emp99 where id=" + id + "";  
	    return template.update(sql);  
	}  
	public Employee getEmpById(int id){  
	    String sql="select * from Emp99 where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));  
	}
}
