package com.jsf_jpa.service;

import com.jsf_jpa.entity.ScheduleData;

import java.util.List;

public interface ScheduleDataService {
    public void create(ScheduleData scheduleData);

    public void delete(ScheduleData scheduleData);

    public List<ScheduleData> findAllByPeriod(long dateFrom, long dateTo);

    public List<ScheduleData> findAll();

    public void deleteAll();
}
