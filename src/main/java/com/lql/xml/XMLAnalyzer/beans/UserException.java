package com.lql.xml.XMLAnalyzer.beans;


import com.lql.xml.XMLAnalyzer.util.ExceptionUtil;

/**
 * Created by lql on 2017/11/6.
 */
public class UserException extends Exception {
    private String errorId;
    private String message;
    private String id;

    public UserException(String ex){
        super(ex);
    }
    public UserException(String id, String errorId, String prefixMsg, Exception ex) {
        this.message = prefixMsg + ExceptionUtil.getErrorInfoFromException(ex);
        this.errorId = errorId;
        this.id = id;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
