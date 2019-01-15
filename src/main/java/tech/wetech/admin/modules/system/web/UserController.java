package tech.wetech.admin.modules.system.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.wetech.admin.core.utils.BaseController;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.PageResultSet;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.core.utils.ResultCodeEnum;
import tech.wetech.admin.modules.system.dto.UserDto;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.modules.system.query.UserQuery;
import tech.wetech.admin.modules.system.service.GroupService;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.modules.system.service.RoleService;
import tech.wetech.admin.modules.system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author cjbi
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;

    @GetMapping
    @RequiresPermissions("user:view")
    public String page(Model model) {
        setCommonData(model);
        return "system/user";
    }

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("user:view")
    public PageResultSet<UserDto> list(UserQuery userQuery) {
        return userService.findByPage(userQuery);
    }

    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("user:create")
<<<<<<< HEAD
    @SystemLog("用户管理创建用户")
=======
    @SystemLog("鐢ㄦ埛绠＄悊鍒涘缓鐢ㄦ埛")
>>>>>>> a73359c91648809dcb907679ec8f7a34a32261a2
    public Result create(@Valid User user) {
        userService.createUser(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("user:update")
<<<<<<< HEAD
    @SystemLog("用户管理更新用户")
=======
    @SystemLog("鐢ㄦ埛绠＄悊鏇存柊鐢ㄦ埛")
>>>>>>> a73359c91648809dcb907679ec8f7a34a32261a2
    public Result update(User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("user:delete")
<<<<<<< HEAD
    @SystemLog("用户管理删除用户")
    public Result delete(@RequestParam("id") Long[] ids, HttpServletRequest request) {
        // 当前用户
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println("username:"+username);
=======
    @SystemLog("鐢ㄦ埛绠＄悊鍒犻櫎鐢ㄦ埛")
    public Result delete(@RequestParam("id") Long[] ids, HttpServletRequest request) {
        // 褰撳墠鐢ㄦ埛
        String username = (String) SecurityUtils.getSubject().getPrincipal();
>>>>>>> a73359c91648809dcb907679ec8f7a34a32261a2
        User user = userService.findByUsername(username);
        boolean isSelf = Arrays.stream(ids).anyMatch(id -> id.equals(user.getId()));
        if (isSelf) {
            return Result.failure(ResultCodeEnum.FAILED_DEL_OWN);
        }
        Arrays.asList(ids).forEach(id -> userService.deleteUser(id));
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("user:update")
    @PostMapping("/{id}/change/password")
<<<<<<< HEAD
    @SystemLog("用户管理更改用户密码")
=======
    @SystemLog("鐢ㄦ埛绠＄悊鏇存敼鐢ㄦ埛瀵嗙爜")
>>>>>>> a73359c91648809dcb907679ec8f7a34a32261a2
    public Result changePassword(@PathVariable("id") Long id, String newPassword) {
        userService.changePassword(id, newPassword);
        return Result.success();
    }

    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("groupList",groupService.findAll());
    }

}
