package com.example.demo.layer4;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.layer2.Seat;
@Service
public interface SeatService 
{
	
	void addSeatService(Seat sRef);   //C - add/create
	Seat findSeatService(String SeatNo);     //R - find/reading
	Set<Seat> findAllSeatService();     //R - find all/reading all
	void modifySeatService(Seat sRef); //U - modify/update
	void removeSeatService(String SeatNo); //D - remove/delete
	
	Set<Seat> findSeatServiceByUserid(int uid);
	Set<Seat> findSeatServiceByTicketno(int tno);

}
