package com.mindskip.xzs.controller.admin;

import com.github.pagehelper.PageInfo;
import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.service.*;
import com.mindskip.xzs.utility.DateTimeUtil;
import com.mindskip.xzs.utility.PageInfoHelper;
import com.mindskip.xzs.viewmodel.admin.dashboard.IndexVM;
import com.mindskip.xzs.viewmodel.admin.login.LoginVM;
import com.mindskip.xzs.viewmodel.admin.user.UserResponseVM;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController("AdminLoginController")
@RequestMapping(value = "/api/user")
public class LoginController extends BaseApiController {

    private final ExamPaperService examPaperService;
    private final QuestionService questionService;
    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;
    private final UserEventLogService userEventLogService;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public LoginController(ExamPaperService examPaperService, QuestionService questionService, ExamPaperAnswerService examPaperAnswerService, ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService, UserEventLogService userEventLogService, UserService userService, UserEventLogService userEventLogService1, AuthenticationService authenticationService) {
        this.examPaperService = examPaperService;
        this.questionService = questionService;
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
        this.userEventLogService = userEventLogService;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="/login" , method = RequestMethod.POST)
    public RestResponse<User> loginAuth(@RequestBody LoginVM model){
        User user = modelMapper.map(model, User.class);
        if (authenticationService.authUser(model.getUserName(),model.getPassword()) ){
            user.setUserName(model.getUserName());
            return RestResponse.ok(user);
        }else
            return null;
    }

}
