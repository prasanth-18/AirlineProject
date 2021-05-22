package com.example.demo.layer4;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.layer2.Seat;
import com.example.demo.layer3.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {
   
	@Autowired
	SeatRepository seatRepo;
	
	@Override
	public void addSeatService(Seat sRef) {
		
		 seatRepo.addSeat(sRef);
	}

	@Override
	public Seat findSeatService(String SeatNo) {
		
		Seat seatObj = seatRepo.findSeat(SeatNo);
		return seatRepo.findSeat(seatObj.getSeatno());
	}

	@Override
	public Set<Seat> findAllSeatService() {
		
		return seatRepo.findAllSeats();
	}

	@Override
	public void modifySeatService(Seat sRef) {
	 	
      Seat seatObj = seatRepo.findSeat(sRef.getSeatno());
       seatRepo.modifySeat(seatObj);
	}

	@Override
	public void removeSeatService(String SeatNo) {
		Seat seatObj = seatRepo.findSeat(SeatNo); 
		seatRepo.removeSeat(seatObj.getSeatno());

	}

	@Override
	public Set<Seat> findSeatServiceByUserid(int uid) {
		return seatRepo.findSeatByUserid(uid);
	}

	@Override
	public Set<Seat> findSeatServiceByTicketno(int tno) {
		return seatRepo.findSeatByTicketno(tno);
	}

}
