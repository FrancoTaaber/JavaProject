package com.example.photosapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Log {
    @JsonProperty("time")
    private String time;

    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("method")
    private String method;

    @JsonProperty("body")
    private String body;

    @JsonProperty("oldBody")
    private String oldBody;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOldBody() {
        return oldBody;
    }

    public void setOldBody(String oldBody) {
        this.oldBody = oldBody;
    }
}
