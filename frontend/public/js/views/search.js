/*global define*/
define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'models/search',
	'text!/templates/search.html',
	'date'
],
function SearchView(
	_,
	Backbone,
	$,
	Mustache,
	SearchModel,
	template
) {
	'use strict';
	return Backbone.View.extend({
		el: '#main',
		events: {
			'click button[type=submit]': 'submit'
		},
		initialize: function initialize() {
			this.model = new SearchModel();
		},
		render: function render() {
			var nextWeekend = Date.today().moveToDayOfWeek(6),
				tplDate = nextWeekend.toISOString().substr(0, 10);

			// navigator.geolocation.getCurrentPosition(function(postion) {
   				//TODO: Do real lookup as nice to have
			// });

			this.$el.html(Mustache.render(template, {
				date: tplDate,
				location: 'Berlin'
			}));
			return this;
		},
		submit: function submit(event) {
			event.preventDefault();
			this.model.set({
				date: this.$el.find('#startDate').val(),
				location: this.$el.find('#location').val()
			});
			this.model.fetch({
				success: this.success.bind(this),
				error: this.error.bind(this)
			});
		},
		success: function success(model, response, options) {
			Backbone.Events.trigger('Travel.DateReceived', response);
		},
		error: function error(model, response, options) {
			Backbone.Events.trigger('Travel.generalError', response);
		}
	});
});