package com.atpfx.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atpfx.fxcm.FxcmConnection;
import com.fxcm.external.api.transport.IGateway;

@RestController
public class CommandServlet {

    @Resource
    private FxcmConnection fxcmConnection;

    @RequestMapping("/connect/fxcm")
    @ResponseBody
    public IGateway connect() throws Exception {
        return fxcmConnection.connect();
    }
}
