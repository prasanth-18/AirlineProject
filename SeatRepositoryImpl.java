package com.example.demo.layer3;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.layer2.Seat;

@Repository
public class SeatRepositoryImpl implements SeatRepository {

	@PersistenceContext
	 EntityManager entityManager;
	@Transactional
	public void addSeat(Seat sRef) {
		System.out.println("Adding Seat....");
		entityManager.persist(sRef);

	}

	@Transactional
	public Seat findSeat(String SeatNo) {
		System.out.println("Finding Seat....");
		return entityManager.find(Seat.class,SeatNo);
		
	}

	@SuppressWarnings({"rawtypes", "unchecked" })
	@Transactional
	public Set<Seat> findAllSeats() {
		Set<Seat> sSet;
		Query query = entityManager.createQuery("from Seat");
		sSet = new HashSet(query.getResultList());
		return sSet;
	}
	
	@Transactional
	public void modifySeat(Seat sRef) {
		entityManager.merge(sRef);

	}

	@Transactional
	public void removeSeat(String SeatNo) {
		Seat seat = entityManager.find(Seat.class,SeatNo);
		entityManager.remove(seat);

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public Set<Seat> findSeatByUserid(int uid) {
		Set<Seat> sSet;
		Query query = entityManager.createNativeQuery("select * from seats where ticketno = (select ticketno from reservation where userid = "+uid+")",Seat.class);
		sSet = new HashSet(query.getResultList());
		return sSet;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public Set<Seat> findSeatByTicketno(int tno) {
		Set<Seat> sSet;
		Query query = entityManager.createNativeQuery("select * from seats where ticketno =" +tno+"",Seat.class);
		sSet = new HashSet(query.getResultList());
		return sSet;
	}

}
