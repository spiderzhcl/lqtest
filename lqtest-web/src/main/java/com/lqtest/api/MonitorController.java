package com.lqtest.api;

import com.hualala.grpc.HealthInterface;
import com.lqtest.service.MonitorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/stat")
public class MonitorController {

    private static final Log log = LogFactory.getLog(MonitorController.class);

    @Autowired
    MonitorService monitorService;

    @GetMapping(path = "/grpc2")
    public String getAllApi(@RequestParam Long moduleid, ModelMap mode) {
        return "";
    }

    @GetMapping(path = "/grpc")
    public @ResponseBody
    Object stat(@RequestParam String url, @RequestParam int port) {
        HealthInterface.StatRes statRes = monitorService.statHealth(url, port);
        return statRes;
    }
}
