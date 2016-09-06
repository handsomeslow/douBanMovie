package com.jx.RxMovie.tohContent;

import java.util.List;

public class TohEntity {
    private List<TohEntityResult> result;
    private String reason;
    private int error_code;

    public List<TohEntityResult> getResult() {
        return this.result;
    }

    public void setResult(List<TohEntityResult> result) {
        this.result = result;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return this.error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
