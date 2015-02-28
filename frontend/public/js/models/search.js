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
		url: '/thack-api/services/search/Berlin/2015-03-29T12:00:01.000Z'
	});
});