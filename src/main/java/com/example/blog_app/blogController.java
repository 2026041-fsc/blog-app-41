package com.example.blog_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping; // 追加
import org.springframework.web.bind.annotation.RequestParam; // 追加
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class blogController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/blogs")
    public String blogs(Model model){
        String sql = "SELECT * FROM blog";
        List<Blog >blogList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Blog.class));
        model.addAttribute("blogList", blogList);
        return "blogs";
    }

    @GetMapping("/blogs/new")
    public String newBlogs() {
        return "blogs/new";
    }
    
    @PostMapping("/blogs")
    public String create(@RequestParam("title") String title, @RequestParam("content") String content){
        String sql = "INSERT INTO blog(title,content) VALUES (?,?)";
        jdbcTemplate.update(sql,title,content);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        String sql = "SELECT * FROM blog WHERE id = ?";
        Blog blog = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Blog.class),id);

        model.addAttribute("blog", blog);
        return "blogs/show";
    }
}
