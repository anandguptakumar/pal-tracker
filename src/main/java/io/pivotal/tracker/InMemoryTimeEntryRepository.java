package io.pivotal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> allUsers = new ArrayList<TimeEntry>();
    int pk = 1;
    public TimeEntry create(TimeEntry t)  throws Exception {
        t.setId(pk++);
        allUsers.add(t);
        return t;
    }

    @Override
    public TimeEntry find(long id)  throws Exception {
        TimeEntry found = null;
        try {
            for(TimeEntry t : allUsers){
                if(t.getId()==id){
                    found = t;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public List<TimeEntry> list()  throws Exception {
        return allUsers;
    }

    @Override
    public TimeEntry update(long id, TimeEntry t) throws Exception  {
        TimeEntry found = null;
        try {
            for(TimeEntry x : allUsers){
                if(x.getId()==id){
                    found = x;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(found != null ){
            found.setDate(t.getDate());
            found.setHours(t.getHours());
            found.setProjectId(t.getProjectId());
            found.setUserId(t.getUserId());
        }
        return found;
    }

    @Override
    public void delete(long id) throws Exception  {
        try {
            Iterator<TimeEntry> itr = allUsers.iterator()
                    ;
            while (itr.hasNext())
            {
                TimeEntry x = (TimeEntry)itr.next();
                if( x.getId()==id) {
                    itr.remove();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
