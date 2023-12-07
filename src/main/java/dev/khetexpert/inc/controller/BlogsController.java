// BlogsController Class

package dev.khetexpert.inc.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.Blog;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.service.BlogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogsController {

    private BlogsService blogsService;

    @PostMapping("/write")
    public ResponseEntity<ApiResponse> writeBlog(@Valid @RequestBody Blog blog) {
        return blogsService.writeBlog(blog);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateBlog(@Valid @RequestBody Blog blog) {
        return blogsService.updateBlog(blog);
    }

    @PostMapping("/rate")
    public ResponseEntity<ApiResponse> rateBlog(@RequestHeader("docId") String docId) throws ExecutionException, InterruptedException {
        return blogsService.rateBlog(docId);
    }

    @PostMapping("/unrate")
    public ResponseEntity<ApiResponse> unRateBlog(@RequestHeader("docId") String docId) throws ExecutionException, InterruptedException {
        return blogsService.unRateBlog(docId);
    }

}
