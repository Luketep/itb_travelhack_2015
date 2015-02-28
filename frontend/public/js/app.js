/*global require*/

require([
	'configuration'
],
function configurationLoaded() {
	'use strict';
	require([
		'applications/site',
		'bootstrap'
	],
	function appLoaded(SiteApplication) {
		new SiteApplication().render();
	});
});