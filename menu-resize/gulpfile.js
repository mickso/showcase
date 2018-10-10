'use strict';

var gulp = require('gulp');


//==============================================================================
//  Util
//==============================================================================
/** dependencies **/
var concat = require('gulp-concat');


//==============================================================================
//  Sourcemaps
//==============================================================================
/** dependencies **/
var sourcemaps = require('gulp-sourcemaps');

/** constants **/
var mapDest = '../map';

//==============================================================================
//  Sass
//==============================================================================
/** dependencies **/
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');

/** constants **/
var cssFiles = [
  './src/sass/app.scss'
];
var cssDest = './assets/css/';

/** task **/
gulp.task('styles', function () {
	return gulp.src(cssFiles, { base: '.' })
		.pipe(sourcemaps.init({loadMaps: true}))
		.pipe(sass().on('error', sass.logError))
    .pipe(cleanCSS({compatibility: 'ie8'}))
    .pipe(sourcemaps.write(mapDest))
		.pipe(gulp.dest(cssDest));
});

//==============================================================================
//  Scripts
//==============================================================================
/** dependencies **/
var uglify = require('gulp-uglify-es').default;
var jsValidate = require('gulp-jsvalidate');

/** constants **/
var jsFiles = [
  'node_modules/tether/dist/js/tether.min.js',
  'node_modules/jquery/dist/jquery.min.js',
  'node_modules/bootstrap/dist/js/bootstrap.min.js',
  'src/js/**/*.js',
];
var jsDest = './assets/js';

/** task **/
gulp.task('scripts', function() {
    return gulp.src(jsFiles)
        .pipe(jsValidate())
        .pipe(sourcemaps.init())
        .pipe(uglify())
        .pipe(concat('scripts.js'))
        .pipe(sourcemaps.write(mapDest))
        .pipe(gulp.dest(jsDest));
});

//==============================================================================
//  Deploy
//==============================================================================
/** dependencies **/
var ftp = require( 'vinyl-ftp' );

/** constants **/
var conn = ftp.create( {
  host:     '',
  user:     '',
  password: '',
  parallel: 10
} );

/** task **/
gulp.task( 'deploy', function () {

    var globs = [
      "./assets/**",
      "index.html"
    ];

    // using base = '.' will transfer everything to /public_html correctly
    // turn off buffering in gulp.src for best performance

    return gulp.src( globs, { base: '.', buffer: false } )
        .pipe( conn.dest('.'));
} );

//==============================================================================
//  HTML Beautify
//==============================================================================
/** dependencies **/
var htmlbeautify = require('gulp-html-beautify');

/** task **/
gulp.task('htmlbeautify', function() {
  var options = {
    "indent_size": 2,
    "preserve_newlines": false
  };
  return gulp.src('./index.html')
    .pipe(htmlbeautify(options))
    .pipe(gulp.dest('./index.html'))
});
