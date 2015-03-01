/*global define*/

define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'models/site',
	'text!/templates/title.html',
	'views/search',
	'views/result'
],
function SiteView(
	_,
	Backbone,
	$,
	Mustache,
	SiteModel,
	titleTemplate,
	SearchView,
	ResultView

) {
	'use strict';
	return Backbone.View.extend({
		el: 'html',
		events: {},
		initialize: function initialize() {
			Backbone.Events.on('Travel.DateReceived', this.travelDataReceived.bind(this));
			
			this.model = new SiteModel();
			
			this.$head = this.$el.find('head');
			this.$title = this.$head.find('title');
			this.$body = this.$el.find('body');
			
			this.currentView = new SearchView({
				date: this.model.get('nextWeekend'),
				source: this.model.get('source')
			});
		},
		render: function render() {
			this.$title.html(Mustache.render(titleTemplate, {}));
			this.currentView.render();
			return this;
		},
		travelDataReceived: function travelDataReceived(travelData) {
			this.$el.find('#main').removeClass('page1');
			this.currentView = new ResultView(travelData);
            this.render();
		}
	});
});