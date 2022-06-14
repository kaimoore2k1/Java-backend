package com.senshop.backend.controller;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.senshop.backend.model.Blog;
import com.senshop.backend.model.Comment;
import com.senshop.backend.model.Image;
import com.senshop.backend.repository.BlogRepository;
import com.senshop.backend.repository.CommentRepository;

@Controller
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CommentRepository commentRepository;
    // @Autowired
    // private MongoTemplate mongoTemplate;

    public List<Comment> getCommentsByBlogID(String blogID) {
        ObjectId _id = new ObjectId(blogID);

        return commentRepository.findByBlogId(_id);
    }

    @QueryMapping
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();

        blogs.forEach(arg0 -> {
            arg0.setComments(getCommentsByBlogID(arg0.get_id()));
        });

        return blogs;
    }

    @QueryMapping
    public List<Blog> getHotBlogs() {
        Sort sort = Sort.by(Sort.Direction.DESC, "like");

        List<Blog> blogs = blogRepository.findAll(sort);
        blogs.forEach(arg0 -> {
            arg0.setComments(getCommentsByBlogID(arg0.get_id()));
        });
        return blogs;
    }

    @QueryMapping
    public Blog getBlogBySlug(@Argument String slug) {
        Blog blog = blogRepository.findBySlug(slug);

        blog.setComments(getCommentsByBlogID(blog.get_id()));

        return blog;
    }

    // createBlog(
    // title: String
    // image: String
    // author: String
    // category: String
    // description: String
    // content: String
    // slug: String
    // ): Blog

    @MutationMapping
    public Blog createBlog(@Argument String title, @Argument String image, @Argument String author,
            @Argument String category, @Argument String description, @Argument String content, @Argument String slug) {

        return blogRepository.save(new Blog(title, image, author, category, description, content, slug));
    }

    // updateBlog(
    // _id: String
    // title: String
    // image: String
    // author: String
    // category: String
    // description: String
    // content: String
    // slug: String
    // ): Blog

    @MutationMapping
    public Blog updateBlog(@Argument String _id, @Argument String title, @Argument String image,
            @Argument String author, @Argument String category, @Argument String description, @Argument String content,
            @Argument String slug) {
        Blog blog = blogRepository.findById(_id).get();
        blog.setTitle(title);
        blog.setImage(new Image(image));
        blog.setAuthor(author);
        blog.setCategory(category);
        blog.setDescription(description);
        blog.setContent(content);
        blog.setSlug(slug);

        return blogRepository.save(blog);
    }
    // deleteBlog(_id: String): Blog

    @MutationMapping
    public Blog deleteBlog(@Argument String _id) {
        Blog blog = blogRepository.findById(_id).get();
        blogRepository.delete(blog);
        return blog;
    }

    // likeBlog(_id: String, user: String): Blog
    @MutationMapping
    public Blog likeBlog(@Argument String _id, @Argument String user) {
        Blog blog = blogRepository.findById(_id).get();
        ArrayList<String> like = blog.getLike();
        // System.out.println(like + " " + like.contains(user));
        if (like.contains(user)) {
            like.remove(user);
        } else {
            like.add(user);
        }

        blog.setLike(like);

        return blogRepository.save(blog);
    }

    // shareBlog(_id: String): Blog
    @MutationMapping
    public Blog shareBlog(@Argument String _id) {
        Blog blog = blogRepository.findById(_id).get();
        blog.setShare(blog.getShare() + 1);
        blogRepository.save(blog);
        return blog;
    }

}
