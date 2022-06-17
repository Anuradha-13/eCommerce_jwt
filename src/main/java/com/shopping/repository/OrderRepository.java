package com.shopping.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUserId(int userid);

	// Using Between

	
	List<Order> findAllByDateCreatedBetween(LocalDate startDate, LocalDate endDate);

	// Using Native query
	@Query(value = "SELECT * FROM ORDERS WHERE DATE_CREATED BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<Order> getAllBetweenDates(
			@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
			@Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate);

	// Using spring data jpa query
	@Query("select a from Order a where a.dateCreated <= :dateCreated")
	List<Order> findAllWithDateCreatedTimeBefore(@Param("dateCreated") LocalDate dateCreated);

}
