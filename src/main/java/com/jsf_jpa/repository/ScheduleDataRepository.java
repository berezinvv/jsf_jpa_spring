package com.jsf_jpa.repository;

import com.jsf_jpa.entity.ScheduleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDataRepository extends JpaRepository<ScheduleData, Integer> {
    @Query("select s from ScheduleData s where s.date>=:dateFrom and s.date<=:dateTo")
    List<ScheduleData> findAllByPeriod(@Param("dateFrom") long dateFrom, @Param("dateTo") long dateTo);
}
