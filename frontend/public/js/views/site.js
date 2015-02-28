/*global define*/

define([
	'underscore',
	'Backbone',
	'jquery',
	'Mustache',
	'models/site',
	'text!/templates/title.html',
	'views/search',
	'views/result',
    'd3'
],
function SiteView(
	_,
	Backbone,
	$,
	Mustache,
	SiteModel,
	titleTemplate,
	SearchView,
	ResultView,
    d3
) {
	'use strict';
	return Backbone.View.extend({
		el: 'html',
		events: {},
		initialize: function initialize() {
			this.model = new SiteModel();
			this.$head = this.$el.find('head');
			this.$title = this.$head.find('title');
			this.$body = this.$el.find('body');
			this.currentView = new SearchView();
			Backbone.Events.on('Travel.DateReceived', this.travelDataReceived.bind(this));
		},
		render: function render() {
			this.$title.html(Mustache.render(titleTemplate, {}));
			this.currentView.render();
			return this;
		},
		travelDataReceived: function travelDataReceived(travelData) {
			this.currentView = new ResultView(travelData);
            this.render();
            this.renderGraph();
		},

        update : function update(source,tree,svg,diagonal) {

            var i = 0;
            // Compute the new tree layout.
            var nodes = tree.nodes(source).reverse(),
                links = tree.links(nodes);

            // Normalize for fixed-depth.
            nodes.forEach(function(d) { d.y = d.depth * 100; });

            // Declare the nodes…
            var node = svg.selectAll("g.node")
                .data(nodes, function(d) { return d.id || (d.id = ++i); });

            // Enter the nodes.
            var nodeEnter = node.enter().append("g")
                .attr("class", "node")
                .attr("transform", function(d) {
                    return "translate(" + d.x + "," + d.y + ")"; });

            nodeEnter.append("circle")
                .attr("r", 10)
                .style("fill", "#fff");

            nodeEnter.append("text")
                .attr("y", function(d) {
                    return d.children || d._children ? -18 : 18; })
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

        },

        renderGraph: function () {
            var treeData = [
                {
                    "name": "Top Level",
                    "parent": "null",
                    "children": [
                        {
                            "name": "Level 2: A",
                            "parent": "Top Level",
                            "children": [
                                {
                                    "name": "Son of A",
                                    "parent": "Level 2: A"
                                },
                                {
                                    "name": "Daughter of A",
                                    "parent": "Level 2: A"
                                }
                            ]
                        },
                        {
                            "name": "Level 2: B",
                            "parent": "Top Level"
                        }
                    ]
                }
            ];

// ************** Generate the tree diagram	 *****************
            var margin = {top: 40, right: 120, bottom: 20, left: 120},
                width = 960 - margin.right - margin.left,
                height = 500 - margin.top - margin.bottom;



            var tree = d3.layout.tree()
                .size([height, width]);

            var diagonal = d3.svg.diagonal()
                .projection(function(d) { return [d.x, d.y]; });

            var svg = d3.select(".graph").append("svg")
                .attr("width", width + margin.right + margin.left)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            var root = treeData[0];

            this.update(root,tree,svg,diagonal);



        }
	});
});