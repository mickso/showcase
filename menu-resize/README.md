Menu resize demo
============

## Quick intro:

### SRC folder
I've separated the source files (sass, js, .gitignore, package.json etc) from a generated demo. 

If you want to compile this project yourself: 

1. Clone the repository to your local machine.
2. Use NPM install in root folder(menu-resize) on command line. (If you don't have NPM and NodeJS, get the installer here: https://nodejs.org/en/)
3. To run style and script pipelines:
	1. Run gulp styles on command line in root folder to "compile" sass.
	2. Run gulp scripts to "compile" javascript files.

### DEMO folder

A compiled and cleaned result.


### Notes

1. Font paths are build relative from webroot url, thus fontawesome only works when assets and index.html are placed in the webroot of a webserver.


