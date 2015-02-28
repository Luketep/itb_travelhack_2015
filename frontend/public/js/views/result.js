/*global define*/

define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'models/site',
	'text!/templates/result.html'
],
function SiteView(
	_,
	Backbone,
	$,
	Mustache,
	SiteModel,
	template
) {
	'use strict';
	return Backbone.View.extend({
		el: '#main',
		events: {},
		travelData: undefined,
		initialize: function initialize(travelData) {
			this.travelData = travelData;
			console.log(this.travelData);
		},
		render: function render() {
			this.$el.html(Mustache.render(template, {
				travelData: this.travelData.source + ' ' + this.travelData.date
			}));
			return this;
		}
	});
});