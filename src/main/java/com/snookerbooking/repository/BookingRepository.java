package com.snookerbooking.repository;

import com.snookerbooking.entity.Booking;
import com.snookerbooking.entity.SnookerTable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("""
			SELECT b FROM Booking b, SnookerTable t
			WHERE b.snookerTableId = t.id AND t.ownerId = :ownerId
			""")
	List<Booking> findByOwnerId(Long ownerId);

}
