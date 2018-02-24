package com.jsf_jpa.service;

import com.jsf_jpa.entity.ScheduleData;
import com.jsf_jpa.repository.ScheduleDataRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleDataServiceImpl implements ScheduleDataService {
    @Resource
    private ScheduleDataRepository scheduleDataRepository;


    @Override
    public void create(ScheduleData scheduleData) {
        scheduleDataRepository.save(scheduleData);
    }

    @Override
    public void delete(ScheduleData scheduleData) {
        scheduleDataRepository.delete(scheduleData);
    }

    @Override
    public List<ScheduleData> findAllByPeriod(long dateFrom, long dateTo) {
        return scheduleDataRepository.findAllByPeriod(dateFrom, dateTo);
    }

    @Override
    public List<ScheduleData> findAll() {
        return scheduleDataRepository.findAll();
    }
    @Override
    public void deleteAll() {
        scheduleDataRepository.deleteAll();
    }
}
