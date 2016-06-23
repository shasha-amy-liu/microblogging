package microblogging.controller;

import java.util.ArrayList;
import java.util.List;

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

import microblogging.model.Blog;
import microblogging.model.User;
import microblogging.service.BlogService;
import microblogging.vo.BlogVO;

@Controller
@RequestMapping("blog")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<BlogVO> list(HttpServletRequest request,
            HttpServletResponse response) {
        // get user from session
        User user = (User) request.getSession().getAttribute("user");

        List<Blog> blogs = blogService.findByUserId(user.getId());
        List<BlogVO> result = new ArrayList<BlogVO>();
        for (Blog blog : blogs) {
            result.add(new BlogVO(blog, user));
        }

        return result;
    }

    @RequestMapping(value = "/tracking", method = RequestMethod.GET)
    public @ResponseBody List<BlogVO> tracking(HttpServletRequest request,
            HttpServletResponse response) {
        List<BlogVO> result = new ArrayList<>();
        // get user from session
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String userId = user.getId();

            List<Blog> blogs = blogService.findByUserId(userId);
            logger.info(String.format("blog size = %d", blogs.size()));
            for (Blog blog : blogs) {
                result.add(new BlogVO(blog, user));
            }
        }

        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody BlogVO add(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "blogContent", required = true) String blogContent) {
        // get user from session
        User user = (User) request.getSession().getAttribute("user");

        // save blog
        Blog blog = blogService.save(blogContent, user.getId());
        BlogVO vo = new BlogVO(blog, user);

        return vo;
    }
}
