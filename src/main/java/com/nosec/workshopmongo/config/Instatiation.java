package com.nosec.workshopmongo.config;

import com.nosec.workshopmongo.domain.Post;
import com.nosec.workshopmongo.domain.User;
import com.nosec.workshopmongo.repository.PostRepository;
import com.nosec.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {


        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.deleteAll();
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.deleteAll();

        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viagem!", "Vou viajar para sp!", maria );
        Post post2 = new Post(null,sdf.parse("21/03/2018"),"Bom dia!", "Acordei feliz hoje!", maria );

        postRepository.saveAll(Arrays.asList(post1,post2));
        
    }
}
