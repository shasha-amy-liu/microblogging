package microblogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import microblogging.service.BlogService;
import microblogging.service.UserService;

@Controller
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public @ResponseBody List<BlogVO> list(HttpServletRequest request, HttpServletResponse response) {
//        // get user info
//        String username = ((User) request.getSession().getAttribute("user")).getUsername();
//        User u = userService.getUserByName(username);
//
//        List<Blog> blogs = blogService.getAllBlogsByUser(u);
//        List<BlogVO> result = new ArrayList<BlogVO>();
//        for (Blog blog : blogs) {
//            result.add(new BlogVO(blog));
//        }
//
//        return result;
//    }
//
//    @RequestMapping(value = "/tracking", method = RequestMethod.GET)
//    public @ResponseBody List<BlogVO> tracking(HttpServletRequest request, HttpServletResponse response) {
//        // get user info
//        String username = ((User) request.getSession().getAttribute("user")).getUsername();
//
//        List<Blog> blogs = userService.getBlogTracking(username);
//        List<BlogVO> result = new ArrayList<BlogVO>();
//        for (Blog blog : blogs) {
//            result.add(new BlogVO(blog));
//        }
//
//        return result;
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public @ResponseBody BlogVO add(HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(value = "blogContent", required = true) String blogContent) {
//        // get user info
//        String username = ((User) request.getSession().getAttribute("user")).getUsername();
//
//        // save blog
//        Blog blog = blogService.save(blogContent, username);
//        BlogVO vo = new BlogVO(blog);
//
//        return vo;
//    }
}
