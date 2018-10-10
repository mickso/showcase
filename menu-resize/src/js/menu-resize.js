/* ==============================================
Navigation scroll resize
=============================================== */
var scrollNavResize = function(){

  var navbarOffsetY = $('.navbar').offset().top + $('.navbar').outerHeight();

  var breakpoint = $('header').outerHeight(true);

  if(navbarOffsetY > breakpoint){
    $('.navbar').addClass('collapsed');
  }

  if(navbarOffsetY < breakpoint){
    $('.navbar').removeClass('collapsed');
  }

};
//add logic to scroll event
$(document).scroll(scrollNavResize);
//add logic to page load
$(document).ready(scrollNavResize);
