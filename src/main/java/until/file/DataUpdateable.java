package until.file;

import entity.table.Line;

import java.util.List;

public interface DataUpdateable <T>{
    List<Line> readDataFromDB();

    void update(T data);
}
