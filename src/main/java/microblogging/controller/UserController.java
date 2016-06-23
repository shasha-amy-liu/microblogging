package microblogging.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import microblogging.model.User;
import microblogging.service.UserService;
import microblogging.util.PasswordUtil;
import microblogging.vo.CommonRequestStatus;
import microblogging.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    public @ResponseBody CommonRequestStatus isLogin(HttpServletRequest request,
            HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");
        CommonRequestStatus status = new CommonRequestStatus();

        logger.info("enter islogin");

        if (u != null) {
            status.setStatus("true");
            status.setMessage(u.getUsername());
        } else {
            status.setStatus("false");
        }

        return status;
    }

    @RequestMapping(value = "/isFollowing", method = RequestMethod.GET)
    public @ResponseBody List<UserVO> isFollowing(HttpServletRequest request,
            HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");

        List<User> amFollowing = userService.listIsFollowing(u.getId());
        if (amFollowing == null || amFollowing.isEmpty()) {
            return new ArrayList<UserVO>();
        }

        List<UserVO> result = new ArrayList<UserVO>();

        for (User user : amFollowing) {
            result.add(new UserVO(user));
        }

        return result;
    }

    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public @ResponseBody CommonRequestStatus follow(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "people", required = true) String people) {
        String[] input = people.split(", ");

        User u = (User) request.getSession().getAttribute("user");

        for (String s : input) {
            if (s != null && !s.isEmpty()) {
                User b = userService.findUserByUsername(s);
                userService.followUser(u, b);
            }
        }

        CommonRequestStatus status = new CommonRequestStatus();
        status.setStatus("true");
        return status;
    }

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public @ResponseBody User getUser(HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(value = "username", required = true) String username) {
//        User u = userService.getUserByName(username);
//        return u;
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody CommonRequestStatus addUser(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        password = PasswordUtil.getInstance().encode(password);
        User u = new User(username, password);
        userService.save(u);

        CommonRequestStatus status = new CommonRequestStatus();
        status.setStatus("true");
        return status;
    }

    @RequestMapping(value = "/listAllUsersNotFollowedYet", method = RequestMethod.GET)
    public @ResponseBody Set<UserVO> listAllUsersNotFollowedYet(HttpServletRequest request,
            HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("user");

        Set<User> users = userService.listAllUsersNotFollowedYet(u.getId());
        Set<UserVO> result = new HashSet<UserVO>();
        for (User user : users) {
            result.add(new UserVO(user));
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();

        return "index";
    }

    @RequestMapping(value = { "/checkLogin", "/login" }, method = RequestMethod.POST)
    public @ResponseBody CommonRequestStatus checkLogin(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {

        User storedUser = userService.findUserByUsername(username);
        CommonRequestStatus status = new CommonRequestStatus();
        if (PasswordUtil.getInstance().check(password, storedUser.getPassword())) {
            status.setStatus("true");
            if (request.getSession().getAttribute("user") == null) {
                request.getSession().setAttribute("user", storedUser);
            }
        } else {
            status.setStatus("false");
        }

        return status;
    }
}
