package zlagoda.zlagoda.controller.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class HttpWrapper {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public HttpWrapper(HttpServletRequest request, HttpServletResponse response) {
        this.req = request;
        this.resp = response;
    }

    public HttpServletRequest getRequest() {
        return req;
    }

    public void setRequest(HttpServletRequest request) {
        this.req = request;
    }

    public HttpServletResponse getResponse() {
        return resp;
    }

    public void setResponse(HttpServletResponse response) {
        this.resp = response;
    }
}
