package com.example.demo.layer4;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.layer2.Seat;
import com.example.demo.layer4.exceptions.SeatAlreadyExistException;
import com.example.demo.layer4.exceptions.SeatNotFoundException;
@Service
public interface SeatService 
{
	
	String addSeatService(Seat sRef) throws SeatAlreadyExistException;   //C - add/create
	Seat findSeatService(int sno) throws SeatNotFoundException;     //R - find/reading
	Set<Seat> findAllSeatsService();     //R - find all/reading all
	String modifySeatService(Seat sRef)throws SeatNotFoundException; //U - modify/update
	String removeSeatService(int sno)throws SeatNotFoundException; //D - remove/delete
	
	Set<Seat> findSeatServiceByUserid(int uid);
	Set<Seat> findSeatServiceByTicketno(int tno);
	Set <Seat> findSeatServiceBySeatNo(String seatno);  
	void removeSeatServiceByTicketNo(int tno); 
}
