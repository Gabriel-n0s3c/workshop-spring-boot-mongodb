package com.nosec.workshopmongo.resources;

import com.nosec.workshopmongo.domain.Post;
import com.nosec.workshopmongo.domain.User;
import com.nosec.workshopmongo.dto.UserDTO;
import com.nosec.workshopmongo.resources.util.URL;
import com.nosec.workshopmongo.services.PostService;
import com.nosec.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> obj = service.findByTitle(text);
        return ResponseEntity.ok().body(obj);
    }


}
