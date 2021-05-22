package com.example.demo.layer5;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.layer2.Seat;
import com.example.demo.layer4.SeatService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController  //REpresentational State Transfer html xml json
public class SeatController {


	@Autowired
	SeatService seatService;
	
	@GetMapping(path="/getSeat/{mySeatNo}") 
	@ResponseBody
	public ResponseEntity<Seat> getSeat(@PathVariable("mySeatNo")  int sno) {
		
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
	public Set<Seat> getAllSeats() {
	
		Set<Seat> seatList = seatService.findAllSeatsService();
		return seatList;
		
	}
	
	
	
	@PostMapping(path="/addSeat")
	public void addSeat(@RequestBody Seat seat) {
		
		Seat seatObj =new Seat();
		seatObj.setSno(seat.getSno());
		seatObj.setSeatno(seat.getSeatno());
	    seatObj.setPassengerfullname(seat.getPassengerfullname());
	    seatObj.setAge(seat.getAge());
		seatObj.setAgegroup(seat.getAgegroup());
	    seatObj.setReservation(seat.getReservation());
		 
	    seatService.addSeatService(seatObj);
		 
		System.out.println("-----------------");
		
		
	}
	
	@PutMapping(path="/modifySeat")
	public void modifySeat(@RequestBody Seat seat) {
		 seatService.modifySeatService(seat);
		
		
	}
	
	@DeleteMapping(path="/deleteSeat")
	public void removeDepartment(@RequestBody Seat seat) {
			seatService.removeSeatService(seat.getSno());
		
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

}
