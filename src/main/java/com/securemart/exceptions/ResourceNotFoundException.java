package com.securemart.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String feild;
    String feildName;
    Long feildId;

    public ResourceNotFoundException(String resourceName, String feild, String feildName) {
        super(String.format("%s not found with %s %s", resourceName, feild, feildName));
        this.resourceName = resourceName;
        this.feild = feild;
        this.feildName = feildName;
    }

    public ResourceNotFoundException(String resourceName, String feild, Long feildId) {
        super(String.format("%s not found with %s %d", resourceName, feild, feildId));
        this.resourceName = resourceName;
        this.feild = feild;
        this.feildId = feildId;
    }
}
