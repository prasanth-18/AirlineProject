package com.example.demo.layer4;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.layer2.Seat;
import com.example.demo.layer3.SeatRepository;
import com.example.demo.layer4.exceptions.SeatAlreadyExistException;
import com.example.demo.layer4.exceptions.SeatNotFoundException;

@Service
public class SeatServiceImpl implements SeatService {
   
	@Autowired
	SeatRepository seatRepo;
	
	@Override
	public String addSeatService(Seat sRef) throws SeatAlreadyExistException {
		
		try {
			seatRepo.addSeat(sRef);
		}
		catch(Exception e)
		{
			throw new SeatAlreadyExistException("Seat Already Exist");
		}
		return "Seat Added Successfully";
	}

	@Override
	public Seat findSeatService(int serialNo) throws SeatNotFoundException {
		
		Seat seatObj = seatRepo.findSeat(serialNo);
		if(seatObj==null)
		{
			throw new SeatNotFoundException("Seat Not Found");
		}
		return seatRepo.findSeat(seatObj.getSno());
	}

	@Override
	public Set<Seat> findAllSeatsService() {
		
		return seatRepo.findAllSeats();
	}

	@Override
	public String modifySeatService(Seat sRef)throws  SeatNotFoundException {
	 	
      Seat seatObj = seatRepo.findSeat(sRef.getSno());
		if(seatObj == null)
		{
		   throw new SeatNotFoundException("Seat Not Found");
		}
	
      else {
       seatRepo.modifySeat(sRef);
      }
       return "Seat Modified Successfully";
	}

	@Override
	public String removeSeatService(int sno) throws  SeatNotFoundException {
		Seat seatObj = seatRepo.findSeat(sno); 
		if(seatObj == null)
		{
		   throw new SeatNotFoundException("Seat Not Found");
		}
	
		else {
			seatRepo.removeSeat(seatObj.getSno());
		}
	
	return "Seat Deleted Successfully";
	}

	@Override
	public Set<Seat> findSeatServiceByUserid(int uid){
		
		return seatRepo.findSeatByUserid(uid);
	}

	@Override
	public Set<Seat> findSeatServiceByTicketno(int tno) {
		return seatRepo.findSeatByTicketno(tno);
	}
	
	@Override
	public Set<Seat> findSeatServiceBySeatNo(String seatno){
		return seatRepo.findSeatBySeatNo(seatno);
	}
	@Override
	public void removeSeatServiceByTicketNo(int tno) {
		
		seatRepo.removeSeatByTicketNo(tno);

	}

	
}
