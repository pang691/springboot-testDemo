package com.taikang.test.scheduled.controller;

import com.taikang.test.scheduled.bo.QuartzBo;
import com.taikang.test.scheduled.config.PagerResult;
import com.taikang.test.scheduled.service.IQuartzService;
import io.swagger.annotations.*;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/quartz")
@Api(value = "QuartzApiController",description = "启动流程",tags = "/quartz")
public class QuartzApiController {

    private static final Logger logger = LoggerFactory.getLogger(QuartzApiController.class);

    @Autowired
    private IQuartzService quartzService;

    //添加启动任务
    //@RequirePermission
    @ApiOperation(value = "添加启动任务", notes = "用于添加启动任务", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = QuartzBo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
//            @ApiImplicitParam(name="jobName",value = "任务名称",required = false,paramType = "query",dataType = "String"),
//            @ApiImplicitParam(name="className",value = "任务全类名",required = false,paramType = "query",dataType = "String"),
//            @ApiImplicitParam(name = "jobCron", value = "任务表达式", required = false, paramType = "query", dataType = "String")
    })
    @ApiResponses(
            @ApiResponse(code = 200, message = "添加成功", response = QuartzBo.class)
    )
    @PostMapping("/startJob")
    public String startJob1(QuartzBo [] bos) {

        if(bos.length < 0 ){
            return "参数有误！";
        }
        QuartzBo bo = bos[0];
        if(!CronExpression.isValidExpression(bo.getJobCron())){
            return "任务表达式无效，请填写正确的表达式";
        }
//        bo.setId(IdHelper.genWorkerId());
        bo.setCreateTime(new Date());
        bo.setState(1);

        try {
            quartzService.addJob(bo);
            return "添加成功";
        } catch (Exception e) {
            logger.error("添加启动任务异常:",e);
            return "添加失败";
        }
    }



    //修改启动任务
    //@RequirePermission
    @ApiOperation(value = "修改启动任务", notes = "用于修改启动任务", httpMethod = "PUT",
            produces = MediaType.APPLICATION_JSON_VALUE, response = QuartzBo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name="id",value = "任务ID",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="jobName",value = "任务名称\n(insuranceCardJob)",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="className",value = "任务全类名\n(com.taikang.health.timer.scheduled.job.InsuranceCardJob)",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "jobCron", value = "任务表达式\n(0 30 14 * * ? *)", required = false, paramType = "query", dataType = "String")
    })
    @ApiResponses(
            @ApiResponse(code = 200, message = "添加成功", response = QuartzBo.class)
    )
    @PutMapping("/modify")
    public String modifyQuartzJob(@ApiIgnore QuartzBo vo) {
        if(!CronExpression.isValidExpression(vo.getJobCron())){
            return "任务表达式无效，请填写正确的表达式";
        }
        try {
            QuartzBo bo = quartzService.getQuartzBoById(vo.getId());
            bo.setJobName(vo.getJobName());
            bo.setJobCron(vo.getJobCron());
            bo.setClassName(vo.getClassName());
            bo.setCreateTime(new Date());
            quartzService.modifyJob(bo);
            return "修改成功";
        } catch (Exception e) {
            logger.error("修改启动任务异常:",e);
            return "修改失败";
        }
    }


    //删除、停止任务
    //@RequirePermission
    @ApiOperation(value = "删除、停止任务", notes = "用于删除、停止任务", httpMethod = "DELETE",
            produces = MediaType.APPLICATION_JSON_VALUE, response = QuartzBo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name="id",value = "任务ID",required = true,paramType = "query",dataType = "String")
    })
    @ApiResponses(
            @ApiResponse(code = 200, message = "成功")
    )
    @DeleteMapping("/delete")
    public String deleteJob(String id) {

        if(StringUtils.isEmpty(id)){
            return "参数异常";
        }

        try {
            quartzService.removeJob(id);
        } catch (Exception e) {
            logger.error("删除任务异常:",e);
            return "删除任务失败";
        }
        return "删除任务成功";
    }
    //根据查询任务信息
//    @RestMessage
//    //@RequirePermission
//    @ApiOperation(value = "查询单个任务信息", notes = "用于查询单个任务信息", httpMethod = "GET",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = QuartzBo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
//            @ApiImplicitParam(name="id",value = "任务ID",required = true,paramType = "query",dataType = "String")
//    })
//    @ApiResponses(
//            @ApiResponse(code = 200, message = "成功")
//    )
//    @GetMapping("/info")
//    public QuartzBo getQuartzJob(String id) {
//        QuartzBo info = null;
//        try {
//            info = quartzService.getQuartzBoById(id);
//            return info;
//        } catch (Exception e) {
//            //return "删除任务失败");
//        }
//        return info;
//    }


    //查询所有任务
    //@RequirePermission
    @ApiOperation(value = "任务列表", notes = "任务列表", httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_VALUE, response = QuartzBo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name="jobName",value = "任务名称 \n(insurance)",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "String")
    })
    @ApiResponses(
            @ApiResponse(code = 200, message = "成功")
    )
    @GetMapping("/queryQuartzBoList")
    public List<QuartzBo> queryQuartzBoList(@ApiIgnore QuartzBo bo) {
        PagerResult<QuartzBo> listBo = null;
        bo.setIsOpen(null);
        try {
            listBo = quartzService.queryQuartzBoList(bo);
            return listBo.getData();
        } catch (Exception e) {
            logger.error("查询任务异常:",e);
            return null;
        }
    }


    //开启、关闭任务
    //@RequirePermission
    @ApiOperation(value = "开启、关闭任务", notes = "用于开启、关闭任务", httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_VALUE, response = QuartzBo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name="id",value = "任务ID",required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="state",value = "任务状态值",required = false,paramType = "query",dataType = "String")
    })
    @ApiResponses(
            @ApiResponse(code = 200, message = "成功")
    )
    @GetMapping("/closeJob")
    public String closeJob(String id, Integer state) {
        String message = null;
        try {
            message = quartzService.closeJob(id,state);
        } catch (Exception e) {
            logger.error("关闭或者开启任务异常:",e);
            return "任务失败: "+e.getMessage();
        }
        return message;
    }
}


