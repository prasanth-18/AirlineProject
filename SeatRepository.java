package com.example.demo.layer3;

import java.util.Set;
import org.springframework.stereotype.Repository;
import com.example.demo.layer2.Seat;

@Repository
public interface SeatRepository {
	
	void addSeat(Seat sRef);   //C - add/create
	Seat findSeat(int sno); //R - find/reading
	Set<Seat> findAllSeats();     //R - find all/reading all
	void modifySeat(Seat sRef); //U - modify/update
	void removeSeat(int sno); //D - remove/delete//block by flight admin
	
	Set<Seat> findSeatByUserid(int uid);
	Set<Seat> findSeatByTicketno(int tno);
	Set<Seat> findSeatBySeatNo(String seatno); 
	void removeSeatByTicketNo(int tno);
}
