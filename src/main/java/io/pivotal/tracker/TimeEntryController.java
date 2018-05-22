package io.pivotal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class TimeEntryController {
    public TimeEntryController( @Autowired TimeEntryRepository repo) {
        this.repo = repo;
    }

    private TimeEntryRepository repo;

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List l = null;
        try {
            l = repo.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<TimeEntry>>(l, HttpStatus.OK);
    }

    @GetMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry found = null;
        try {
              found = repo.find(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(found == null )
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        else{
            return new ResponseEntity<TimeEntry>(found, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<TimeEntry>  update (@PathVariable long id, @RequestBody TimeEntry expected) {
        List<TimeEntry> l = null;
        TimeEntry found = null;
        try {
            found = repo.update(id, expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(found == null )
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        else{
            found.setDate(expected.getDate());
            found.setHours(expected.getHours());
            found.setProjectId(expected.getProjectId());
            found.setUserId(expected.getUserId());
            return new ResponseEntity<TimeEntry>(found, HttpStatus.OK);

        }
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TimeEntry>  create(@RequestBody TimeEntry timeEntryToCreated) {
        TimeEntry t = null;
        try {
            t = repo.create(timeEntryToCreated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TimeEntry>(t, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete (@PathVariable Long id) {
        try {
            repo.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
