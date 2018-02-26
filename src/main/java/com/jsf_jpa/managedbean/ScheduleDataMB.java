package com.jsf_jpa.managedbean;

import com.jsf_jpa.entity.ScheduleData;
import com.jsf_jpa.service.ScheduleDataService;
import com.jsf_jpa.service.UserService;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component("ScheduleDataMB")
@Scope("session")
public class ScheduleDataMB {

    private String email;
    private String password;

    private Date dateFrom;
    private Date dateTo;
    private Date date;
    private int value;
    private String note;
    private List<ScheduleData> scheduleDataList = new ArrayList<>();
    private String scheduleDataString = "[]";

    public ScheduleDataMB() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.email = userDetails.getUsername();
    }

    @Autowired
    private ScheduleDataService scheduleDataService;

    @Autowired
    private UserService userService;

    public String getEmail() {
        return email;
    }

        public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String signIn(ScheduleDataMB ScheduleDataMB) {
//        User user = userService.findOneByEmailPassword(ScheduleDataMB.email, ScheduleDataMB.password);
//        if (user == null) {
//            user = new User();
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!",
//                            " Login Error!"));
//            return null;
//        } else {
//            return "main";
//        }
//
//    }

//    public String logOut(){
//        email = null;
//        password = null;
//        return"index.xhtml";
//    }


    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDate() {
        return new Date();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<ScheduleData> getScheduleDataList() {
        return scheduleDataList;
    }

    public void setScheduleDataList(List<ScheduleData> scheduleDataList) {

        this.scheduleDataList = scheduleDataList;
    }

    public String getScheduleDataString() {

        return scheduleDataString;
    }

    public void setScheduleDataString(String scheduleDataString) {
        this.scheduleDataString = scheduleDataString;
    }

    public ScheduleDataService getScheduleDataService() {
        return scheduleDataService;
    }

    public void setScheduleDataService(ScheduleDataService scheduleDataService) {
        this.scheduleDataService = scheduleDataService;
    }

    public void findScheduleData(SelectEvent event) {
        scheduleDataList.clear();
        scheduleDataString = "[";


        if (dateFrom != null && dateTo != null) {
            scheduleDataList = scheduleDataService.findAllByPeriod((Long) dateFrom.getTime(), (Long) dateTo.getTime());
        }
        for (ScheduleData sD : scheduleDataList) {
            scheduleDataString += "[" + sD.getDate() + "," + sD.getValue() + "],";
        }
        scheduleDataString += "]";
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("linechart");
    }

    public void clear() {
        scheduleDataService.deleteAll();
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully completed (clear)",
                        " Successfully completed (clear)"));

    }

    public void generate() {

        List<ScheduleData> sDataList = scheduleDataService.findAll();

        if (sDataList.size() != 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Data can`t be added",
                            " Data can`t be added"));
            return;
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "01-01-1970";
        Random randomInt = new Random();


        for (int i = 0; i < 50; i++) {
            ScheduleData scheduleData = new ScheduleData();

            Date beginDate = new Date();
            try {
                beginDate = sdf.parse(dateInString);
            } catch (Exception e) {
            }

            long randomDate = ThreadLocalRandom.current().nextLong(beginDate.getTime(), new Date().getTime());
            scheduleData.setDate(randomDate);

            int v = randomInt.nextInt(201);
            scheduleData.setNote("item_[" + v + "]");
            scheduleData.setValue(v);
            scheduleDataService.create(scheduleData);
        }
    }


    public String add() {
        return "page2.xhtml";
    }

    public void addData(ScheduleDataMB scheduleDataMB) {
        ScheduleData scheduleData = new ScheduleData();

        scheduleData.setDate((Long) scheduleDataMB.date.getTime());
        scheduleData.setNote(scheduleDataMB.note);
        scheduleData.setValue(scheduleDataMB.value);
        try {
            scheduleDataService.create(scheduleData);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully completed",
                            "Successfully completed"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Not successful",
                            "Not successful"));
        }

    }
}
