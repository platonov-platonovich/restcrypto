//package app;
//
//import app.models.Post;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//import static org.springframework.web.bind.annotation.RequestMethod.POST;
//
//
//@org.springframework.cloud.netflix.feign.FeignClient(value = "jplaceholder", url = "https://api.coinlore.net/api/ticker/")
//public interface FeignClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
//    List<Post> getPosts();
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{postId}", produces = "application/json")
//    Post getPostById(@PathVariable("postId") Long postId);
//}
