package com.lqtest.api;


import com.lqtest.common.entry.ApiRepository;
import com.lqtest.common.entry.ReqRepository;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import static com.lqtest.tools.RandomUtil.RANDOM;

@Controller
@RequestMapping(path = "/api")
public class ApiController {

    Log log = LogFactory.getLog(ApiController.class);

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ReqRepository reqRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<TblApi> getAllApi() {
        // This returns a JSON or XML with the users
        return apiRepository.findAll();
    }

    @RequestMapping(path = "/detail")
    public String request(@RequestParam Long apiId,ModelMap model) {
        TblApi api = apiRepository.findOne(apiId);
        model.put("thisapi",api);
        Specification specification = new Specification<TblApi>() {
            @Override
            public Predicate toPredicate(Root<TblApi> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(null != api.getApiId()){
                    predicates.add(criteriaBuilder.equal(root.get("apiId"), api.getApiId()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        model.put("apiDetail",reqRepository.findAll(specification));
        return "api/detail";
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
    public DataTablesOutput<TblApi> listallPOST(@Valid @RequestBody DataTablesInput input) {
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
        api.setTitle(RandomUtil.randomString(RANDOM.nextInt(20)));
//        api.setActive(RANDOM.nextInt(2));
//        api.setEnv(RANDOM.nextInt(3));
        api.setMockUrl("http://dohko.auth.hualala.com/" +RandomUtil.randomString(RANDOM.nextInt(10)) );
        apiRepository.save(api);
        return "Saved";
    }
}
