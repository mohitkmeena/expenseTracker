package com.mohit.expenseservice.repository;

import com.mohit.expenseservice.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByUserId(String userId);

    @Query("SELECT e FROM Expense e WHERE e.userId = :userId AND e.createdAt BETWEEN :start AND :end")
    List<Expense> findByUserIdAndCreatedBetween(@Param("userId") String userId,
                                                @Param("start") Timestamp start,
                                                @Param("end") Timestamp end);


    Optional<Expense> findByUserIdAndExternalId(String userId,String externalId);
}
