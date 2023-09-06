package com.drc.pack.dto;

import java.util.List;

public class Department {
	private int deptid;
	private String deptname;

	private List<Employee> emp;

	public Department() {
	}

	public Department(int deptid, String deptname, List<Employee> emp) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
		this.emp = emp;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public List<Employee> getEmp() {
		return emp;
	}

	public void setEmp(List<Employee> emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", deptname=" + deptname + ", emp=" + emp + "]";
	}

}
