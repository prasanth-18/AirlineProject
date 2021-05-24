package com.example.demo.layer5;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.layer2.Reservation;
import com.example.demo.layer2.Seat;

import com.example.demo.layer3.ReservationRepositoryImpl;
import com.example.demo.layer4.SeatService;
import com.example.demo.layer4.exceptions.SeatAlreadyExistException;
import com.example.demo.layer4.exceptions.SeatNotFoundException;


@RestController  //REpresentational State Transfer html xml json
public class SeatController {


	@Autowired
	SeatService seatService;
	
	 @Autowired 		   
    ReservationRepositoryImpl resvRepoImpl;
	
	
	@GetMapping(path="/getSeat/{mySerialNo}") 
	@ResponseBody
	public ResponseEntity<Seat> getSeat(@PathVariable("mySerialNo") int sno)throws SeatNotFoundException 
	{
		System.out.println("Seat Controller.....");
	     Seat seat=null;
		
			seat = seatService.findSeatService(sno);
			if(seat==null)
			{ return ResponseEntity.notFound().build();
			
			}
			else {
				return ResponseEntity.ok(seat);
			}
		
	}
	
	
	@GetMapping(path="/getSeats")
	@ResponseBody
	public Set<Seat> getAllSeats()
	{
	   Set<Seat> seatList = seatService.findAllSeatsService();
		return seatList;
		
	}
	
	
	
	@PostMapping(path="/addSeat")
	public String addSeat(@RequestBody Seat seat){
    		
		Seat seatObj =new Seat();
		seatObj.setSno(seat.getSno());
		seatObj.setSeatno(seat.getSeatno());
	    seatObj.setPassengerfullname(seat.getPassengerfullname());
	    seatObj.setAge(seat.getAge());
		seatObj.setAgegroup(seat.getAgegroup());
	 //  seatObj.setReservation(.);  //Not Working
		System.out.println("-----------------");
		 String stmsg = null;
		 try {
	            stmsg = seatService.addSeatService(seatObj);
	            
		 }
		 catch(SeatAlreadyExistException e) 
		 {
			 e.printStackTrace();
			 return e.getMessage();
		 }
		 catch(Exception e) 
		 {
				e.printStackTrace();
				return e.getMessage();
			}
		System.out.println("Controller printing"+stmsg);
		return stmsg;
		
	}
	
	@PutMapping(path="/modifySeat") // Not Working
	public String modifySeat(@RequestBody Seat seat) throws SeatNotFoundException  
	{
		String stmsg =null;
	 try
	 {
			 stmsg = seatService.modifySeatService(seat);
		}
   catch (SeatNotFoundException e) 
   {
		
		e.printStackTrace();
		return e.getMessage();
	  }
	 catch(Exception e) 
	 {
			e.printStackTrace();
		}
		System.out.println("controller is saying: "+stmsg);
		  return stmsg;
		
	}
	
	@DeleteMapping(path="/deleteSeat")
	public String removeSeat(@RequestBody Seat seat) throws SeatNotFoundException
	{ 
		String stmsg = null;
	try 
	{
		stmsg = seatService.removeSeatService(seat.getSno());
	} 
	catch (SeatNotFoundException e)
	{
		e.printStackTrace();
		return e.getMessage();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println("controller is saying: "+stmsg);
	  return stmsg;
		
	}
	
	@GetMapping(path="/getSeatTN/{getSeatByTicketNo}")
	@ResponseBody
	public Set<Seat> findSeatServiceByTicketno(@PathVariable("getSeatByTicketNo")  int tno) {
	
		Set<Seat> seatList = seatService.findSeatServiceByTicketno(tno);
		return seatList;
		
	}
	
	@GetMapping(path="/getSeatUI/{getSeatByUserId}")
	@ResponseBody
	public Set<Seat> findSeatServiceByUserid(@PathVariable("getSeatByUserId")  int uid) {
		
	
		Set<Seat> seatList = seatService.findSeatServiceByUserid(uid);
		return seatList;
		
	}
	
	@GetMapping(path="/getSeatSN/{mySeatNo}") 
	@ResponseBody
	public Set<Seat> findSeatServiceBySeatNo(@PathVariable("mySeatNo")  String seatno) {
		
		Set<Seat> seatList = seatService.findSeatServiceBySeatNo(seatno);
		return seatList;
		
	}
	@DeleteMapping(path="/deleteSeatTN/{myTicketNo}")
	@ResponseBody 
	public void removeSeatByTicketNo(@PathVariable("myTicketNo")Integer tno) {
		
		    System.out.println(tno);
		    Reservation resv =   resvRepoImpl.findReservation(tno);
		     System.out.println(resv.getTicketno());
			 seatService.removeSeatServiceByTicketNo(resv.getTicketno()); //resv not null operation should perform
		      
	}
	
}
