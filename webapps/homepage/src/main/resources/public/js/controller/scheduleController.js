// This is a JavaScript file
app.controller('ScheduleCtrl',function($scope){

    eventDates = [
    {"startTime":"2018-01-27T13:30:00+09:00","endTime":"2018-01-27T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-02-17T13:30:00+09:00","endTime":"2018-02-17T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-03-03T13:30:00+09:00","endTime":"2018-03-03T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-04-21T13:30:00+09:00","endTime":"2018-04-21T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-05-19T13:30:00+09:00","endTime":"2018-05-19T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-06-16T13:30:00+09:00","endTime":"2018-06-16T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-07-21T13:30:00+09:00","endTime":"2018-07-21T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-08-18T13:30:00+09:00","endTime":"2018-08-18T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-09-08T13:30:00+09:00","endTime":"2018-09-08T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-10-20T13:30:00+09:00","endTime":"2018-10-20T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-11-17T13:30:00+09:00","endTime":"2018-11-17T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2018-12-15T13:30:00+09:00","endTime":"2018-12-15T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2019-01-19T13:30:00+09:00","endTime":"2019-01-19T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2019-02-16T13:30:00+09:00","endTime":"2019-02-16T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"},
    {"startTime":"2019-03-02T13:30:00+09:00","endTime":"2019-03-02T15:30:00+09:00","summary":"みんなで遊ぼうドイツゲーム","location":"小平市中央公民館"}
    ];


    $.ajax({
      url: "/schedule",
      async: false
    }).done(function(data) {
      eventDates = data;
          //eventDates = [];

      for (i = 0; i < eventDates.length; i++) {
          var sch = eventDates[i];
          var startTime = new Date(sch['startTime']);
          var endTime = new Date(sch['endTime']);
          sch['yyyymmdd'] = (startTime.getYear()+1900) + "/" + (startTime.getMonth()+1) + "/" + startTime.getDate();
          sch['hhmm'] = startTime.getHours() + ":" + startTime.getMinutes() + "~" + endTime.getHours() + ":" + endTime.getMinutes();
          eventDates[i] = sch;
      }

      $scope.eventDates = eventDates;
    });

}) ;
