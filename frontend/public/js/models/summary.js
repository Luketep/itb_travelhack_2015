define([
    'underscore',
    'Backbone'
],
function SummaryModel(
    _,
    Backbone
    ) {
    'use strict';
    return Backbone.Model.extend({
        defaults : {
            tours : new Array()
        }
    });
});