/*global define*/

define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'models/site',
    'views/graph',
    'views/summary',
	'text!/templates/result.html'
],
function SiteView(
	_,
	Backbone,
	$,
	Mustache,
	SiteModel,
    GraphView,
    SummaryDataView,
	template
) {
	'use strict';
	return Backbone.View.extend({
		el: '#main',
		events: {},
		travelData: undefined,
		initialize: function initialize(travelData) {
			this.travelData = travelData;
            this.graphView = new GraphView(travelData);
            this.summaryDataView = new SummaryDataView ();
            console.log(this.travelData);
		},
		render: function render() {
            this.$el.empty();
			this.$el.append(Mustache.render(template, {}));
            this.$el.find('.graphRow').append(this.graphView.render().$el);
            this.$el.find('.dataRow').append(this.summaryDataView.render().$el);
			return this;
		}
	});
});