package com.drc.pack.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.drc.pack.dto.Department;
import com.drc.pack.dto.Employee;

@Repository
public class EmpDepDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(Department department) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(c -> {
			PreparedStatement preparedStatement = c.prepareStatement("insert into department(deptname) values(?)",
					new String[] { "deptid" });
			preparedStatement.setString(1, department.getDeptname());
			return preparedStatement;
		}, keyHolder);

		List<Employee> emp = department.getEmp();

		for (Employee employee : emp) {
			Number key = keyHolder.getKey();
			jdbcTemplate.update("insert into employee(empname,deptid) values(?,?)", employee.getEmpname(),
					key.intValue());
		}

	}

	public void listAdd(List<Department> departments) {
		for (Department department2 : departments) {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(c -> {
				PreparedStatement preparedStatement = c.prepareStatement("insert into department(deptname) values(?)",
						new String[] { "deptid" });
				preparedStatement.setString(1, department2.getDeptname());
				return preparedStatement;
			}, holder);
			Number key = holder.getKey();

			List<Employee> emp = department2.getEmp();
			jdbcTemplate.batchUpdate("insert into employee(empname,deptid) values(?,?)", new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, emp.get(i).getEmpname());
					ps.setInt(2,key.intValue() );
					
				}
				
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return emp.size();
				}
			});
		}
	}

}
