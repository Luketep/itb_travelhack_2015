/*global define*/

define([
	'underscore',
	'Backbone'
],
function SearchModel(
	_,
	Backbone
) {
	'use strict';
	return Backbone.Model.extend({
		url: '/getTravelData'
	});
});