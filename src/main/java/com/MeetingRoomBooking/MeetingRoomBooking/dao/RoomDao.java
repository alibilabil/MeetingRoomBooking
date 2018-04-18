package com.MeetingRoomBooking.MeetingRoomBooking.dao;

import java.util.List;

import com.MeetingRoomBooking.MeetingRoomBooking.model.Room;



public interface RoomDao {
	List <Room> listRoom();
	Room saveOrUpdate (Room room);
	Room getIdRoom(Integer idRoom);
	void delete(Integer idRoom);
	List <Room> showByFloor(Integer floor);
	List <Room> byFloor2();

}
