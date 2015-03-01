/*global define*/

define([
    'underscore',
    'Backbone',
    'jquery',
    'Mustache',
    'text!/templates/graph.html',
    'd3',
    'models/travel'
],
function GraphView(
    _,
    Backbone,
    $,
    Mustache,
    template,
    d3,
    TravelModel
    ) {
    'use strict';
    return Backbone.View.extend({
        events: {},
        travelData: undefined,
        initialize: function initialize(travelData) {
            this.travelData = travelData;
            this.travelModel = new TravelModel(travelData);
            console.log(this.travelData);
        },
        circleSize: 150,

        update : function update(source,tree,svg,diagonal) {

            var i = 0;
            // Compute the new tree layout.
            var nodes = tree.nodes(source).reverse(),
                links = tree.links(nodes);

            // Normalize for fixed-depth.
            nodes.forEach(function(d) { d.y = d.depth * 400; });

            // Declare the nodes…
            var node = svg.selectAll("g.node")
                .data(nodes, function(d) { return d.id || (d.id = ++i); });

            // Enter the nodes.
            var nodeEnter = node.enter().append("g")
                .attr("class", "node")
                .attr("transform", function(d) {
                    return "translate(" + d.x + "," + d.y + ")"; });
            nodeEnter.on("click", function(d){
                Backbone.Events.trigger('Graph.DestinationClicked', d);
            });

            nodeEnter.append("image")
                .attr("class", function (d) {
                    return "node " + d.id;
                })
                .attr("xlink:href", "/images/circle2.png")
                .attr("width", this.circleSize + "px")
                .attr("height", this.circleSize + "px");

            nodeEnter.append("text")
                .attr("y", function(d) {
                    return 150/2;
                })
                .attr("x", function(d) {
                    return 150/2;
                })
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .text(function(d) { return d.name; })
                .style("fill-opacity", 1);


            // Declare the links…
            var link = svg.selectAll("path.link")
                .data(links, function(d) { return d.target.id; });

            // Enter the links.
            link.enter().insert("path", "g")
                .attr("class", "link")
                .attr("d", diagonal);

            var filtered =nodeEnter.filter(function(el){
                return el.children ? false : true;
            });

            this.attachIcons(filtered);

        },

        attachIcons: function(singleNode) {

            singleNode.append('image')
                .attr("xlink:href", "/images/bus.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return -15;
                })
                .attr("x", function(d) {
                    return 60;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/theater.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 60;
                })
                .attr("x", function(d) {
                    return -15;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/hotel_4star.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 60;
                })
                .attr("x", function(d) {
                    return 135;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/foot.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 5;
                })
                .attr("x", function(d) {
                    return 5;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/dining.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 115;
                })
                .attr("x", function(d) {
                    return 5;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/beach.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 135;
                })
                .attr("x", function(d) {
                    return 60;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/bike.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 115;
                })
                .attr("x", function(d) {
                    return 115;
                });
            singleNode.append('image')
                .attr("xlink:href", "/images/sailing.png")
                .attr("width", "30px")
                .attr("height", "30px")
                .attr("y", function(d) {
                    return 0;
                })
                .attr("x", function(d) {
                    return 115;
                });
        },

        diagonalPath : function(d) {
            return [d.x + 150/2, d.children ? (d.y + 150) : d.y];
        },
        renderGraph: function (treeData) {
            var treeData = this.travelModel.toTree();

            // ************** Generate the tree diagram	 *****************
            var margin = {top: 40, right: 120, bottom: 20, left: 120},
                width = 960 - margin.right - margin.left,
                height = 600 - margin.top - margin.bottom;

            var tree = d3.layout.tree()
                .size([height, width]);

            var diagonal = d3.svg.diagonal()
                .projection(this.diagonalPath);

            var svg = d3.select(this.el).append("svg")
                .attr("width", width + margin.right + margin.left)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            var root = treeData;

            this.update(root,tree,svg,diagonal);
        },
        render: function render() {
            this.$el.html(Mustache.render(template,{}));
            this.renderGraph();
            return this;
        }
    });
});