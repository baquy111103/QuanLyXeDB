package entity.table.table;

import entity.table.Line;

public class Assignment {
    private Line line;
    private int turnNumber;
    public Assignment(Line line , int turnNumber){
        this.line = line;
        this.turnNumber = turnNumber;
    }

    public Line getLine(){
        return line;
    }

    public float getDistance(){
        return line.getDistance() * turnNumber * 2;
    }

    public void setLine(Line line){
        this.line = line;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    @Override
    public String toString(){
        return "Assignment[line = "+line+" , turnNumber= "+turnNumber+"]";
    }
}
