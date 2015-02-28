/*global define*/

define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'router/router',
	'views/site'
],
function SiteApplicationDefined(
	_,
	Backbone,
	$,
	Mustache,
	Router,
	SiteView
) {
	'use strict';
	return Backbone.View.extend({
		initialize: function initialized() {
			this.router = new Router();
			Backbone.history.start();
			this.view = new SiteView();
		},
		render: function render() {
			this.view.render();
			return this;
		}
	});
});