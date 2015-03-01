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
		initialize: function initialize(options) {
			this.model = new SearchModel();

			if (options.date) {
				this.model.set('date', options.date);
			}
			if (options.source) {
				this.model.set('source', options.source);
			}
		},
		render: function render() {
			var nextWeekend = this.model.get('date'),
				tplDate = nextWeekend.toISOString().substr(0, 10),
				source = this.model.get('source');

			// navigator.geolocation.getCurrentPosition(function(postion) {
   				//TODO: Do real lookup as nice to have
			// });

			this.$el.html(Mustache.render(template, {
				date: tplDate,
				location: source
			}));
			return this;
		},
		submit: function submit(event) {
			event.preventDefault();
			var source = this.$el.find('#source').val(),
				date = this.$el.find('#startDate').val()

			this.model.set({
				source: source,
				date: date
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