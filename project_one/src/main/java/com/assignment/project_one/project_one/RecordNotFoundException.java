package com.assignment.project_one.project_one;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String id) {
        super("Record not found by " + id);
    }
}
