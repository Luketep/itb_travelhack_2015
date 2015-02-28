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
		baseUrl: '/thack-api/services/search/',
		url: function() {
			var source = this.get('source'),
				date = this.get('date');

			return this.baseUrl + source + '/' + date ;
		}
	});
});