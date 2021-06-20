let gender_list = ["Female", "Male"];

$(document).ready(function () {
  let age = document.getElementById("age").value ?? "";
  let gender = document.getElementById("gender").value ?? "";
  let occupation = document.getElementById("occupation").value ?? "";
  let genre = document.getElementById("genre").value ?? "";

  $.ajax({
    url: `http://localhost:8080/users/recommendations?gender=${gender}&age=${age}&occupation=${occupation}&genre=${genre}`,
    method: "GET",
    dataType: "json",
  })
    .done(function (data) {
      let list = data;

      $(list).each(function (index, value) {
        let movie_title = document.querySelectorAll(".top_movie_title")[index];
        movie_title.innerHTML = value.title;

        let movie_img = document.querySelectorAll(".top_movie_img")[index];
        let movie_img_link = document.querySelectorAll(".top_movie_img_link")[
          index
        ];
        movie_img.src =
          value.poster ==
          "https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003"
            ? "https://image.shutterstock.com/image-vector/no-image-available-sign-internet-600w-261719003.jpg"
            : value.poster;

        movie_img_link.href = value.link;
      });
    })
    .fail(function () {
      alert("fail");
    });

  let random_gender = gender_list[Math.round(Math.random())];
  let random_age = Math.floor(Math.floor(Math.random() * 30 + 10));

  let target = document.getElementById("random_header");
  target.innerHTML = `Recommend Movies for ${
    Math.round(random_age / 10) * 10
  }'s ${random_gender}`;

  $.ajax({
    url: `http://localhost:8080/users/recommendations?gender=${random_gender[0]}&age=${random_age}&occupation=&genre=`,
    method: "GET",
    dataType: "json",
  })
    .done(function (data) {
      console.log("HERE");
      let list = data;

      $(list).each(function (index, value) {
        let movie_title = document.querySelectorAll(".random_movie_title")[
          index
        ];
        movie_title.innerHTML = value.title;

        let movie_img = document.querySelectorAll(".random_movie_img")[index];
        let movie_img_link = document.querySelectorAll(
          ".random_movie_img_link"
        )[index];
        movie_img.src =
          value.poster ==
          "https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003"
            ? "https://image.shutterstock.com/image-vector/no-image-available-sign-internet-600w-261719003.jpg"
            : value.poster;

        movie_img_link.href = value.link;
      });
    })
    .fail(function () {
      alert("fail");
    });
});

function getRecommendedWithInfo() {
  let age = document.getElementById("age").value ?? "";
  let gender = document.getElementById("gender").value ?? "";
  let occupation = document.getElementById("occupation").value ?? "";
  let genre = document.getElementById("genre").value ?? "";
  let list;

  $.ajax({
    url: `http://localhost:8080/users/recommendations?gender=${gender}&age=${age}&occupation=${occupation}&genre=${genre}`,
    method: "GET",
  }).done(function (data) {
    list = data;

    if (data.error) {
      alert(data.error);
      return;
    }

    $(list).each(function (index, value) {
      let movie_title = document.querySelectorAll(".input_movie_title")[index];
      movie_title.innerHTML = value.title;

      let movie_img = document.querySelectorAll(".input_movie_img")[index];
      let movie_img_link = document.querySelectorAll(".input_movie_img_link")[
        index
      ];
      movie_img.src =
        value.poster ==
        "https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003"
          ? "https://image.shutterstock.com/image-vector/no-image-available-sign-internet-600w-261719003.jpg"
          : value.poster;

      movie_img_link.href = value.link;
    });
  });
}

function getRecommendedWithTitle() {
  let title = document.getElementById("title").value ?? "";

  $.ajax({
    url: `http://localhost:8080/movies/recommendations/?title=${title}&limit=`,
    method: "GET",
  }).done(function (data) {
    list = data;

    if (data.error) {
      alert(data.error);
      return;
    }

    $(list).each(function (index, value) {
      let movie_title = document.querySelectorAll(".input_movie_title_other")[
        index
      ];
      movie_title.innerHTML = value.title;

      let movie_img = document.querySelectorAll(".input_movie_img_other")[
        index
      ];
      let movie_img_link = document.querySelectorAll(
        ".input_movie_img_link_other"
      )[index];
      movie_img.src =
        value.poster ==
        "https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003"
          ? "https://image.shutterstock.com/image-vector/no-image-available-sign-internet-600w-261719003.jpg"
          : value.poster;

      movie_img_link.href = value.link;
    });
  });
}
