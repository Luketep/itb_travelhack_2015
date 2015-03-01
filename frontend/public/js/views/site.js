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
			this.model = new SiteModel();
			this.$head = this.$el.find('head');
			this.$title = this.$head.find('title');
			this.$body = this.$el.find('body');
			this.currentView = new SearchView();
			Backbone.Events.on('Travel.DateReceived', this.travelDataReceived.bind(this));
		},
		render: function render() {
			this.$title.html(Mustache.render(titleTemplate, {}));
			this.currentView.render();
			return this;
		},
		travelDataReceived: function travelDataReceived(travelData) {
			this.currentView = new ResultView(travelData);
            this.render();
		}
	});
});