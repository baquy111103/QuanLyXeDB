package dto;

import entity.table.Drivers;
import entity.table.Line;

import java.util.Arrays;

public class AssignmentDto {
    private Drivers drivers;
    private Line line;
    private int turnNumber;

    public AssignmentDto(Drivers drivers,Line line,int turnNumber){
        this.drivers = drivers;
        this.line = line;
        this.turnNumber = turnNumber;
    }

    public Drivers getDrivers() {
        return drivers;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public Arrays getAssignmentList() {
        return null;
    }
}
