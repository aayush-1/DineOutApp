package lld.projects.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Slot {
    int slotId;
    LocalTime slotStartTime;
    List<Table> bookedTables = new ArrayList<>();

    public Slot(int slotId, LocalTime localDateTime) {
        this.slotId = slotId;
        this.slotStartTime = localDateTime;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalTime getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(LocalTime slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public List<Table> getBookedTables() {
        return bookedTables;
    }

    public void setBookedTables(List<Table> bookedTableIds) {
        this.bookedTables = bookedTableIds;
    }

    public void addBookedTable(Table table) {
        this.bookedTables.add(table);
    }

    public void removeBookedTable(Table table) {
        this.bookedTables.remove(table);
    }
}
