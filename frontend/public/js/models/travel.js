define([
    'underscore',
    'Backbone'
],
function TravelModel(
    _,
    Backbone
    ) {
    'use strict';
    return Backbone.Model.extend({
        maxNodes : 2,
        addChildren : function (initial) {
            for (var i =0; i < this.maxNodes; i++) {
                var dest = this.get('destinations')[i];
                var node = $.extend({},dest);
                node.parent = "TXL";
                node.name = dest.sabreInfo.destinationLocation;
                initial.children.push(node);
            }
            return initial;
        },
        toTree : function () {
            var initial = {
                "name": "TXL",
                "parent": "null",
                "children": [

                ]
            };

            return this.addChildren(initial);
        }
    });
});