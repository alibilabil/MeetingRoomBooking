package com.MeetingRoomBooking.MeetingRoomBooking.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingRoomBooking.MeetingRoomBooking.Repository.RoomRepository;
import com.MeetingRoomBooking.MeetingRoomBooking.dao.RoomDao;
import com.MeetingRoomBooking.MeetingRoomBooking.model.Room;




@Service
public class RoomDaoImpl implements RoomDao{
	private EntityManagerFactory emf;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	public RoomDaoImpl(EntityManagerFactory emf) {

		this.emf = emf;
	}
	
	@Override
	public List<Room> listRoom() {
		return roomRepository.listRoom();
	
	}

	@Override
	public Room saveOrUpdate(Room room) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Room saved = em.merge(room);
		em.getTransaction().commit();
		return saved;
		

	}

	@Override
	public Room getIdRoom(Integer idRoom) {
		EntityManager em = emf.createEntityManager();
		return em.find(Room.class, idRoom);
	}

	@Override
	public void delete(Integer idRoom) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Room.class,idRoom));
		em.getTransaction().commit();
	}

	

	@Override
	public List<Room> showByFloor(Integer floor) {
		
		return roomRepository.byFloor(floor);
	}

	@Override
	public List<Room> byFloor2() {
		
		return roomRepository.byFloor2();
	}

}
