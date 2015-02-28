/*global define*/

define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'models/site',
	'text!/templates/title.html'
],
function SiteView(
	_,
	Backbone,
	$,
	Mustache,
	SiteModel,
	titleTemplate
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
		},
		render: function render() {
			this.$title.html(Mustache.render(titleTemplate, {}));
			return this;
		}
	});
});