package com.db.tradefinance.model;

public class ErrorObject {
    private String errorId;
    private String errorDesc;
    private String path;

    public ErrorObject(String errorId, String errorDesc, String path) {
        this.errorId = errorId;
        this.errorDesc = errorDesc;
        this.path = path;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorObject{" +
                "errorId='" + errorId + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
