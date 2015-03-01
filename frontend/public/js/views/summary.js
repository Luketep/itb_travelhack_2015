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
                Backbone.Events.on('Graph.TourClicked', this.updateTourFromGraph.bind(this));
                this.model = new SummaryModel();
            },
            render: function render() {
                this.$el.empty();
                this.$el.append(Mustache.render(titleTemplate,this.model.toJSON()));
                return this;
            },
            updateTourFromGraph: function updateTourFromGraph (tourData){
                var totalPrice = this.model.get('totalPrice');

                var myTourData = {
                    name:"test",
                    price: 0
                };
                if (tourData.tours[tourData.tourId] ) {
                    var gygTour = tourData.tours[tourData.tourId];
                    myTourData.name = gygTour.title;
                    myTourData.price = gygTour.price.values.amount;
                } else {
                    return;
                }

                this.model.get('tours').push(myTourData);
                totalPrice = totalPrice + myTourData.price;
                this.model.set('totalPrice',totalPrice);
                this.model.set('totalPriceString',totalPrice + " EUR ");
                this.render();
            },
            updateFromGraph: function updateFromGraph(destinationNodeData, event) {
                this.model = new SummaryModel();
//                this.model.set('tours',new Array());
                var totalPrice = 0;
                if (destinationNodeData.sabreInfo) {
                    totalPrice = totalPrice + destinationNodeData.sabreInfo.lowestFare;
                    this.model.set('flightPrice',totalPrice + " EUR ");
                }
                this.model.set('totalPrice',totalPrice);
                this.model.set('totalPriceString',totalPrice + " EUR ");
                this.render();
            }
        });
    });