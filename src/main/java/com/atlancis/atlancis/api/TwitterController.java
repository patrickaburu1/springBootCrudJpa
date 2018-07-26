package com.atlancis.atlancis.api;

import com.atlancis.atlancis.ResourceNotFoundException;
import com.atlancis.atlancis.model.Twitter;
import com.atlancis.atlancis.repo.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("twitter")
public class TwitterController {

    @Autowired
    private TwitterRepository twitterRepository;


    @GetMapping(value="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String username,@RequestParam String age,@RequestParam String userid) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Twitter n = new Twitter();
        n.setAge(age);
        n.setFirstname(name);
        n.setUser_id(userid);
        n.setUsername(username);
        twitterRepository.save(n);


        return "Saved";
    }


    @RequestMapping(value="/getTweets",method = RequestMethod.GET)
    public  @ResponseBody Iterable<Twitter> getAllTweets(){

        Iterable<Twitter> twitter=  twitterRepository.findAll();

        return twitter;

   /*    return twitterRepository.findAll();*/

    }


    @PutMapping("/update/{id}")
    public Twitter updateTweeter(@PathVariable(value = "id") Integer noteId, @Valid @RequestBody Twitter noteDetails) {

        Twitter note = twitterRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet","id",noteId));

        if(noteDetails.getFirstname()!=null)
        note.setFirstname(noteDetails.getFirstname());

        if(noteDetails.getUsername()!=null)
        note.setUsername(noteDetails.getUsername());

        Twitter updatedNote = twitterRepository.save(note);

        return updatedNote;
    }



    // Delete a Tweet
    @DeleteMapping("/update/{id}")
    public ResponseEntity<?> deleteTwitter(@PathVariable(value = "id") Integer twitterId) {
        Twitter twitter = twitterRepository.findById(twitterId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", twitterId));

        twitterRepository.delete(twitter);

        return ResponseEntity.ok().build();
    }



}
