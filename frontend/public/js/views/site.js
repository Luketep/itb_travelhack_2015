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
    'models/travel',
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
    TravelModel,
    d3
) {
	'use strict';
	return Backbone.View.extend({
		el: 'html',
		events: {},
		initialize: function initialize() {
			Backbone.Events.on('Travel.DateReceived', this.travelDataReceived.bind(this));
			
			this.model = new SiteModel();
			
			this.$head = this.$el.find('head');
			this.$title = this.$head.find('title');
			this.$body = this.$el.find('body');
			
			this.currentView = new SearchView({
				date: this.model.get('nextWeekend'),
				source: this.model.get('source')
			});
		},
		render: function render() {
			this.$title.html(Mustache.render(titleTemplate, {}));
			this.currentView.render();
			return this;
		},
		travelDataReceived: function travelDataReceived(travelData) {
            this.travelModel = new TravelModel(travelData);
			this.currentView = new ResultView(travelData);
            this.render();
            this.renderGraph();
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

            nodeEnter.append("image")
                .attr("class", function (d) {
                    return "node " + d.id;
                })
                .attr("xlink:href", "/images/circle.png")
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

            var svg = d3.select(".graph").append("svg")
                .attr("width", width + margin.right + margin.left)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            var root = treeData;

            this.update(root,tree,svg,diagonal);
        }
	});
});