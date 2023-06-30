package com.endava.parkinglot.DTO;

import com.endava.parkinglot.enums.WorkingDays;

import java.util.List;

public class ParkingLotCreationDtoResponse {
    private String name;
    private String address;
    private String workingHours;
    private List<WorkingDays> workingDays;
    private List<LevelDto> levels;
    private Boolean isClosed;
    private Boolean operatesNonStop;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public List<WorkingDays> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<WorkingDays> workingDays) {
        this.workingDays = workingDays;
    }

    public List<LevelDto> getLevels() {
        return levels;
    }

    public void setLevels(List<LevelDto> levels) {
        this.levels = levels;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public Boolean getOperatesNonStop() {
        return operatesNonStop;
    }

    public void setOperatesNonStop(Boolean operatesNonStop) {
        this.operatesNonStop = operatesNonStop;
    }
}
