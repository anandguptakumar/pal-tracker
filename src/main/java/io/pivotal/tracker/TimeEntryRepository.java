package io.pivotal.tracker;

import java.time.LocalDate;
import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry t) throws Exception ;
    public TimeEntry find(long id)  throws Exception ;
    public List<TimeEntry> list() throws Exception ;
    public TimeEntry update( long id, TimeEntry t) throws Exception ;
    public void delete(long id) throws Exception ;
}
