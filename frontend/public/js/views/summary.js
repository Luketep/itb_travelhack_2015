/*global define*/

define([
        'underscore',
        'Backbone',
        'jquery',
        'Mustache',
        'models/summary',
        'text!/templates/summary.html'
    ],
    function SiteView(
        _,
        Backbone,
        $,
        Mustache,
        SummaryModel,
        titleTemplate

        ) {
        'use strict';
        return Backbone.View.extend({
            events: {},
            initialize: function initialize() {
                Backbone.Events.on('Graph.DestinationClicked', this.updateFromGraph.bind(this));
                this.model = new SummaryModel();
            },
            render: function render() {
                this.$el.empty();
                this.$el.append(Mustache.render(titleTemplate,this.model.toJSON()));
                return this;
            },
            updateFromGraph: function updateFromGraph(destinationNodeData) {
                if (destinationNodeData.sabreInfo) {
                    this.model.set('totalPrice',destinationNodeData.sabreInfo.lowestFare +
                        " " + destinationNodeData.sabreInfo.currencyCode)
                }
                this.render();
            }
        });
    });