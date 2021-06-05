package com.rest;

import factory.FourArgsFactory;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRecommendController extends ControllerUtil {

  @Autowired
  private MongoTemplate mongoTemplate;

  @ResponseBody
  @GetMapping("/users/recommendations")
  public Object recommend(@RequestParam("gender") String gender, @RequestParam("age") String age,
      @RequestParam("occupation") String occupation, @RequestParam("genre") String genre,
      final UserRecommend mediator) {
    FourArgsFactory state = new FourArgsFactory();
    ArrayList<HashMap<String, String>> result = new ArrayList<>();

    if (!state.checkValidity(new String[]{mediator.getGender(), mediator.getAge(),
        mediator.getOccupation(), mediator.getGenre()}).equals("")) {
      String error = state.checkValidity(new String[]{mediator.getGender(), mediator.getAge(),
          mediator.getOccupation(), mediator.getGenre()});

      HashMap<String, String> temp = new HashMap<>();
      temp.put("error", error);

      return temp;
    }

    state = new FourArgsFactory(mediator.getGender(), mediator.getAge(),
        mediator.getOccupation(), mediator.getGenre());

    return getMovieList(state.getTop10(), "UserRecommend");
  }

}
