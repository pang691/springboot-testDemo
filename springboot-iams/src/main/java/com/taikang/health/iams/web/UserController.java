package com.taikang.health.iams.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api( tags = "用户", description = "user")
public class UserController extends BaseController {

}