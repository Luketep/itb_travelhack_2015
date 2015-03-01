/*global define*/

define([
	'underscore',
	'Backbone',
	'date'
],
function SiteModel(
	_,
	Backbone
) {
	'use strict';
	return Backbone.Model.extend({
		NEXT_WEEKEND: 6, // 6 = Saturday
		initialize: function initialize() {
			var nextWeekend = Date.today().moveToDayOfWeek(this.NEXT_WEEKEND),
				source = 'TXL';

			this.set('nextWeekend', nextWeekend);
			this.set('source', source);
		}
	});
});