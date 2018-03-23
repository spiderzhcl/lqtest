package com.lqtest.api;

import com.lqtest.common.entry.ModuleRepository;
import com.lqtest.common.entry.TbModule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/module")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    private final Log log = LogFactory.getLog(ModuleController.class);

    @RequestMapping(path = "/")
    public String request() {
        return "api/module";
    }

    @RequestMapping(path="/listall")
    public @ResponseBody
    DataTablesOutput<TbModule> listallGET(@Valid DataTablesInput input) {
        log.info("module input : " + input + "   ");
        return moduleRepository.findAll(input);
    }
}
