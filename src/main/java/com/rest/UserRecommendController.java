package com.rest;

import factory.FourArgsFactory;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserRecommendController {

  @ResponseBody
  @GetMapping("/recommendations")
  public Object recommend(@RequestBody final UserRecommend mediator) {
    FourArgsFactory state = new FourArgsFactory(mediator.getGender(), mediator.getAge(),
        mediator.getOccupation(), mediator.getGenre());

    ArrayList<HashMap<String, String>> result = new ArrayList<>();

    state.getResult().forEach(i -> {
      HashMap<String, String> temp = new HashMap<>();
      temp.put("imdb", String.format("https://www.imdb.com/title/tt%s", i.split("/")[1]));
      temp.put("genre", i.split("/")[2]);
      temp.put("title", i.split("/")[0]);
      result.add(temp);
    });

    return result;
  }
}
