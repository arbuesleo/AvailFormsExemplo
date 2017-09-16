var gulp = require('gulp');
var connect = require('gulp-connect');
var inject = require('gulp-inject');
var include = require("gulp-include");

var srcAvForms = "C:\Users\Leo Gomes\workspace - Leo Gomes\AvailFormsAngular";

gulp.task('index', function() {
    var target = gulp.src('app/index.html');
    var source = gulp.src(['app/src/**/*.js', 'app/src/**/*.css', '!app/src/AvailFormsAngular/**/*.*'], { read: false });

    return target.pipe(inject(source, { relative: true })).pipe(gulp.dest('app/'));

});

gulp.task('connect', function() {
    connect.server({
        root: 'app',
        livereload: true,
        port: 8081
    });
});

gulp.task('html', function() {
    gulp.src('./app/**/*.html')
        .pipe(connect.reload());
});

gulp.task('js', function() {
    gulp.src('./app/**/*.js')
        .pipe(connect.reload());
});

gulp.task('css', function() {
    gulp.src('./app/**/*.css')
        .pipe(connect.reload());
});

gulp.task('watch', function() {
    gulp.watch(['./app/**/*.html'], ['html']);
    gulp.watch(['./app/**/*.js'], ['js']);
    gulp.watch(['./app/**/*.css'], ['css']);
});

gulp.task('copy-AvailFormsAngular', function() {
    //Copiando Componentes
    gulp.src(['./node_modules/AvailFormsAngular/src/**/*.js',
            './node_modules/AvailFormsAngular/src/**/*.html',
            './node_modules/AvailFormsAngular/src/**/*.css'
        ])
        .pipe(gulp.dest('./app/src/AvailFormsAngular'));
    //Copiando Libs
    gulp.src(['./node_modules/AvailFormsAngular/libs/**/*'])
        .pipe(gulp.dest('./app/src/AvailFormsAngular/libs'));
    //Copiando admLTE
    gulp.src(['./node_modules/AvailFormsAngular/admLTE/**/*'])
        .pipe(gulp.dest('./app/src/AvailFormsAngular/admLTE'));
});

gulp.task('avLocal', function() {
    //Copiando Componentes
    gulp.src(['C:\Users\Leo Gomes\workspace - Leo Gomes\AvailFormsAngular/src/**/*.js',
            srcAvForms + '/src/**/*.html',
            srcAvForms + '/src/**/*.css'
        ])
        .pipe(gulp.dest('./app/src/AvailFormsAngular'));
    //Copiando Libs
    gulp.src(['C:\Users\Leo Gomes\workspace - Leo Gomes\AvailFormsAngular\libs/**/*'])
        .pipe(gulp.dest('./app/src/AvailFormsAngular/libs'));
    //Copiando admLTE
    gulp.src(['C:\Users\Leo Gomes\workspace - Leo Gomes\AvailFormsAngular/admLTE/**/*'])
        .pipe(gulp.dest('./app/src/AvailFormsAngular/admLTE'));
});

gulp.task('default', ['connect', 'watch', 'index']);


gulp.task('serveprod', function() {
    connect.server({
        root: 'app',
        port: process.env.PORT || 5000, // localhost:5000
        livereload: false
    });
});