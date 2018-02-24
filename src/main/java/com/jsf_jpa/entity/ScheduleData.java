package com.jsf_jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "ScheduleData")
public class ScheduleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date", nullable = false, unique = true)
    private long date;

    @Column(name = "value", nullable = false)
    private int value;

    @Column(name = "note")
    private String note;

    public long getId() {
        return id;
    }

    public ScheduleData() {
    }

    public ScheduleData(long date, int value, String note) {
        this.date = date;
        this.value = value;
        this.note = note;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
}
