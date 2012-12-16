var User = Backbone.Model.extend({});

var UserStore = Backbone.Collection.extend({
  model: User,
  url: 'http://localhost:8080/voteforlunch/user'
});

var user = new UserStore;

var UserView = Backbone.View.extend({
  initialize: function() {
    user.fetch({ success: function() { view.render(); } });
  },
	
  events: { 
	"submit #adduserform" : "handleNewMessage",
	"reset" : "render"
  },

  handleNewMessage: function(data) {
	user.create({userName: $('#newuser').val()});
    $('#newuser').val('');
  },

  render: function() {
    var data = user.map(function(message) {
    	return message.get('userName');
    });
    var result = data.reduce(function(memo,str) { return memo + '<div>' + str + '</div>'; }, '');
    $("#users").html(result);
    return this;
  }

});

user.bind('reset', function(message) {
  user.fetch({ success: function() { view.render(); }});
});

var view = new UserView({
  el: $('#userTemplate')
});

