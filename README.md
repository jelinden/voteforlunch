Vote for lunch
==========================

This is an exercise to make an application to vote for a lunch place.

It's made with Redis, Spring-data and Backbone.js.

1. Install Redis (http://www.redis.io/download)
2. Fetch (Fork at https://github.com/jelinden/voteforlunch) the project and run it. mvn jetty:run -Pdev
3. Hope for the best (http://localhost:8080)

You can also see it at heroku, http://morning-river-8638.herokuapp.com/

This only offers a starting point. The purpose is to make a fully working program.

Requirements
=============

- User has to be able to add restaurants. Restaurant name, 0-5 stars(one vote per user), web address for lunch listing and a street address
- User has to be able to vote for his or hers favorite lunch place for today
- Between all users the chosen restaurant is the one which got most votes
- The current lunch place to be chosen should be shown at all times (unless there are no votes)

Additional/voluntary requirements
=======================

- When choosing the restaurant to be chosen, in a draw, take the stars given to the restaurants into account
- Show the restaurants in a map
- Give the user the ability to give the restaurants individual scoring and take that into account when choosing the lunch place

How to count the score:
<table>  
<tr><th></th>              <th>Stars</th>   <th>User a</th>    <th>User b</th>    <th>Was it chosen in the last 30 days?</th></tr>
<tr><td>Happy garden</td>  <td>3</td>       <td>5</td>         <td>1</td>         <td>0.9 (1.1.2013)</td></tr>
<tr><td>Monte etna</td>    <td>3</td>       <td>2</td>         <td>4</td>         <td>0.9 (2.1.2013)</td></tr>
<tr><td>Pelmenit</td>      <td>2</td>       <td>1</td>         <td>3</td>         <td></td></tr>
</table>
<pre>
1.1.2013
Happy G   1.3*1.5*1.1*1=2.145
Monte E   1.3*1.2*1.4*1=2.184     +chosen restaurant
Pelmenit  1.2*1.1*1.3*1=1.716

2.1.2013
Happy G   1.3*1.5*1.1*1=2.145     +chosen restaurant
Monte E   1.3*1.2*1.4*0.9=1.9656
Pelmenit  1.2*1.1*1.3*1=1.716 
</pre>
And so on...






