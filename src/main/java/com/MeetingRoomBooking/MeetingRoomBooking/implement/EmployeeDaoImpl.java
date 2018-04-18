package com.MeetingRoomBooking.MeetingRoomBooking.implement;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingRoomBooking.MeetingRoomBooking.dao.EmployeeDao;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Employee;


@Service
public class EmployeeDaoImpl implements EmployeeDao{
	private EntityManagerFactory emf;
	@Autowired
	public EmployeeDaoImpl(EntityManagerFactory emf) {

		this.emf = emf;
	}
	
	@Override
	public List<Employee> listEmployee() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Employee", Employee.class).getResultList();
	
	}

	@Override
	public Employee saveOrUpdate(Employee employee) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Employee saved = em.merge(employee);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public Employee getIdEmployee(Integer idEmployee) {
		EntityManager em = emf.createEntityManager();
		return em.find(Employee.class, idEmployee);
	}

	@Override
	public void delete(Integer idEmployee) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Employee.class,idEmployee));
		em.getTransaction().commit();
	}

	@Override
	public Employee login(String username, String password) {
		EntityManager em = emf.createEntityManager();
		try{
		Employee employee = em.createQuery("from Employee where username=:username",Employee.class).setParameter("username", username).getSingleResult();
		if(employee != null && employee.getPassword().equals(password)){
			return employee;
		}
		
		
		return null;
		}catch(Exception ie){
			return null;
		}
	}

	@Override
	public Employee getUserByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Employee where username=:username",Employee.class).setParameter("username", username).getSingleResult();
	}

}
