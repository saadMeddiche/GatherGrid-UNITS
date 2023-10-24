package com.gathergrid.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private String message;
    private Object data;
    private int status;

    public Response(String message, Object data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ",message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
