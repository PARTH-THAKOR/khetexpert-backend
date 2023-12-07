// ExpertBlogService Class

package dev.khetexpert.inc.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import dev.khetexpert.inc.entity.Blog;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class BlogsService {

    public static final String COLLECTION = "blogs";

    public ResponseEntity<ApiResponse> writeBlog(Blog blog) {
        long docId = new Date().getTime();
        blog.setBlogId(docId + blog.getExpertEmail());
        FirestoreClient.getFirestore().collection(COLLECTION).document(docId + blog.getExpertEmail()).set(blog);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Blog Posted").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> updateBlog(Blog blog) {
        FirestoreClient.getFirestore().collection(COLLECTION).document(blog.getBlogId()).set(blog);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Blog Updated").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> rateBlog(String docId) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(docId).get().get();
        Blog blog = snapshot.toObject(Blog.class);
        if (blog != null) {
            blog.setRatting(blog.getRatting() + 1);
            FirestoreClient.getFirestore().collection(COLLECTION).document(blog.getBlogId()).set(blog);
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("Blog rated").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("Blog not Found");
        }
    }

    public ResponseEntity<ApiResponse> unRateBlog(String docId) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = FirestoreClient.getFirestore().collection(COLLECTION).document(docId).get().get();
        Blog blog = snapshot.toObject(Blog.class);
        if (blog != null) {
            blog.setRatting(blog.getRatting() - 1);
            FirestoreClient.getFirestore().collection(COLLECTION).document(blog.getBlogId()).set(blog);
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("Blog unrated").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("Blog not Found");
        }
    }

}
