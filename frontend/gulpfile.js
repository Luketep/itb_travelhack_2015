/*global require*/

var // Gulp dependencies
	gulp = require('gulp'),
    stylus = require('gulp-stylus'),
    minifycss = require('gulp-minify-css'),
    jshint = require('gulp-jshint'),
    uglify = require('gulp-uglify'),
    imagemin = require('gulp-imagemin'),
    rename = require('gulp-rename'),
    concat = require('gulp-concat'),
    del = require('del'),
    qunit = require('gulp-qunit'),
    // Pathes & Vars
    buildPath = 'build',
    deployPath = 'deploy',
    resourcesPath = 'public',
    testPath = 'test',
    deploy = false
;

gulp.task('test', function() {
	'use strict';

	var task = gulp.src([
			testPath + '/index.html'
		])
	;

	task.pipe(qunit());

	return task;
});

gulp.task('build-scripts', function() {
	'use strict';
	
	var task = gulp.src([
			resourcesPath + '/js/configuration.js',
			resourcesPath + '/js/**/*.js',
			'!' + resourcesPath + '/js/libs/**/*.js'
		])
	;

	task.pipe(jshint('.jshintrc'));
	task.pipe(jshint.reporter('default'));
	
	if (deploy) {
		task.pipe(concat('app.js'));
		task.pipe(rename({suffix: '.min'}));
		task.pipe(uglify());
	}

	task.pipe(gulp.dest(buildPath + '/js'));
	
	return task;
});

gulp.task('build-styles', function() {
	'use strict';

	var task = gulp.src([
			resourcesPath + '/css/**/*.styl',
			resourcesPath + '/css/**/*.css',
			'!' + resourcesPath + '/css/libs/'
		])
	;

    task.pipe(stylus());

	// if (deploy) {
		task.pipe(rename({suffix: '.min'}));
		task.pipe(concat('site.css'));
		task.pipe(minifycss());
	// }
	
	task.pipe(gulp.dest(buildPath + '/css'));
	
	return task;
});

gulp.task('build-images', function() {
	'use strict';

	var task = gulp.src([
			'public/images/**/*'
		])
	;

	task.pipe(imagemin({ optimizationLevel: 3, progressive: true, interlaced: true }));
	task.pipe(gulp.dest('build/images'));

	return task;
});

gulp.task('copy-script-libraries', function() {
	'use strict';

	var task = gulp.src([
			resourcesPath + '/js/libs/**/*'
		])
	;
	
	task.pipe(gulp.dest(buildPath + '/js/libs'));

	return task;
});

gulp.task('copy-style-libraries', function() {
	'use strict';

	var task = gulp.src([
			resourcesPath + '/css/libs/**/*'
		])
	;

	task.pipe(gulp.dest(buildPath + '/css/libs'));

	return task;
});

gulp.task('copy-templates', function() {
	'use strict';

	var task = gulp.src([
			resourcesPath + '/templates/**/*'
		])
	;

	task.pipe(gulp.dest(buildPath + '/templates'));

	return task;
});

gulp.task('copy-index', function() {
	'use strict';

	var task = gulp.src([
			resourcesPath + '/index.html'
		])
	;

	task.pipe(gulp.dest(buildPath));

	return task;
});

gulp.task('clean', function(cb) {
	'use strict';

	del([
		buildPath + '**/*'
	], cb);
});

gulp.task('default', ['clean'], function() {
	'use strict';

    gulp.start(
		'build-styles',
		'build-scripts',
		'build-images',
		'copy-script-libraries',
		'copy-style-libraries',
		'copy-templates',
		'copy-index'
	);
});

gulp.task('deploy', ['clean'], function() {
	'use strict';

	deploy = true;
	//TODO: Make sure deploy path is used before testing!
	gulp.start('default');

	deploy = false;
});

gulp.task('watch', function() {
	'use strict';

	gulp.start('default');
	// What about watching library folder?
	gulp.watch(resourcesPath + '/css/**/*.styl', ['build-styles']);
	gulp.watch(resourcesPath + '/images/**/*', ['build-images']);
	gulp.watch(resourcesPath + '/templates/**/*.html', ['copy-templates']);
	gulp.watch(resourcesPath + '/index.html', ['copy-index']);
	gulp.watch([
		resourcesPath + '/js/**/*.js',
		'!' + resourcesPath + '/js/libs/'
	], ['build-scripts']);
});