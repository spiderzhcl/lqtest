package com.lqtest.api;


import com.lqtest.common.entry.ApiRepository;
import com.lqtest.common.entry.TblApi;
import com.lqtest.tools.RandomUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.lqtest.tools.RandomUtil.RANDOM;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    Log log = LogFactory.getLog(ApiController.class);

    @Autowired
    private ApiRepository apiRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<TblApi> getAllApi() {
        // This returns a JSON or XML with the users
        return apiRepository.findAll();
    }


    @PostMapping(path = "/list")
    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<TblApi> getAllApiPage(@RequestParam Integer start, @RequestParam Integer length) {
        // This returns a JSON or XML with the users
        log.info("start & length: " + start + "   " + length);
        Sort sort = new Sort("apiId");
        Integer p = (start == null ? 1 : start);
        Integer s = (length == null ? 10 : length);
        Pageable pageable = new PageRequest(p, s, sort);

        return apiRepository.findAll(pageable);
    }

    @RequestMapping(path = "/listall", method = RequestMethod.POST)
    public @ResponseBody
    DataTablesOutput<TblApi> listallPOST(@Valid @RequestBody DataTablesInput input) {
        log.info("input : " + input + "   ");
        return apiRepository.findAll(input);
    }

    @RequestMapping(path = "/listall", method = RequestMethod.GET)
    public @ResponseBody
    DataTablesOutput<TblApi> listallGET(@Valid DataTablesInput input) {
        log.info("input : " + input + "   ");
        return apiRepository.findAll(input);
    }


    /**
     * @RequestParam String name
     * @RequestParam String email
     */
    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewApi() {

        TblApi api = new TblApi();
        api.setApiName(RandomUtil.randomString(RANDOM.nextInt(20)));
        api.setEnable(RANDOM.nextInt(2));
        api.setEnv(RANDOM.nextInt(3));
        api.setMockUrl("http://dohko.auth.hualala.com/" +RandomUtil.randomString(RANDOM.nextInt(10)) );
        apiRepository.save(api);
        return "Saved";
    }
}
