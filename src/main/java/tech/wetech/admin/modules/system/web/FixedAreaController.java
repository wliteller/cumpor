package tech.wetech.admin.modules.system.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.utils.PageResultSet;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.modules.system.dto.FixedAreaDto;
import tech.wetech.admin.modules.system.po.FixedArea;
import tech.wetech.admin.modules.system.query.FixedAreaQuery;
import tech.wetech.admin.modules.system.service.FixedAreaService;

import javax.validation.Valid;

/**
 * @author rt
 */
@Controller
@RequestMapping("/fixedArea")
public class FixedAreaController {
    @Autowired
    private FixedAreaService fixedAreaService;

    @GetMapping
    @RequiresPermissions("fixedarea:view")
    public String page() {
        return "system/fixedarea/fixedarealist";
    }

    @ResponseBody
    @RequestMapping("/list")
    //@RequiresPermissions("fixedarea:view")
    public PageResultSet<FixedAreaDto> list(FixedAreaQuery fixedAreaQuery) {
        System.out.print("list");
        return fixedAreaService.findByPage(fixedAreaQuery);
    }

    @ResponseBody
    @RequiresPermissions("fixedarea:create")
    @RequestMapping("/create")
    public Result insert(@Valid FixedArea fixedArea){
        fixedAreaService.insertOne(fixedArea);
        return  Result.success();
    }

    @ResponseBody
    @RequestMapping("/checkNo")
    public Result checkNo(@Valid FixedArea fixedArea){
        fixedArea = fixedAreaService.selectOne(fixedArea);
        if(fixedArea != null){
            return  Result.failure("400","已存在");
        }
        return  Result.success();
    }
}
