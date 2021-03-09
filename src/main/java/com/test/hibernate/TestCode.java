package com.test.hibernate;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class TestCode {

	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

	public static void main(String[] args) {

		TestCode ts = new TestCode();
		ts.createRecord();
		ts.updateEmployee(5, "Rondy");
		System.out.println(ts.findAllStudentsWithJpql());
		ts.deleteEmployee(5);
	}

	public Employee createRecord() {
		Session session = sessionFactory.openSession();
		Employee emp = new Employee();
		emp.setId(5);
		emp.setName("testeee");
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
		return emp;
	}

	public List<Employee> findAllStudentsWithJpql() {
		Session session = sessionFactory.openSession();
		List<Employee> empList = session.createCriteria(Employee.class).list();
		session.close();
		return empList;
	}

	public Employee updateEmployee(Integer id, String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee employee = (Employee) session.get(Employee.class, id);
		employee.setName(name);
		session.update(employee);
		session.getTransaction().commit();
		session.close();
		return employee;
	}

	public Employee deleteEmployee(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee employee = (Employee) session.get(Employee.class, id);
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
		return employee;
	}
}