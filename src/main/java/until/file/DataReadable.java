package until.file;

import entity.table.Drivers;

import java.util.List;

public interface DataReadable <T>{
    List<T> readDataFormDB();

    List<Drivers> readDataFromDB();
}
