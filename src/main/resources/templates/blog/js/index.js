/*;(function () {
	
	
	
	 var goToTop = function() {

		$('.js-gotop').on('click', function(event){
			
			event.preventDefault();

			
			$("body,html").animate({scrollTop: 0},200);
			
			return false;
		});

		$(window).scroll(function(){

			var $win = $(window);
			if ($win.scrollTop() > 200) {
				$('.js-top').addClass('active');
			} else {
				$('.js-top').removeClass('active');
			}

		});
	
	};

	
	
	$(function(){
		goToTop();
	}); 


}());*/

$(document).ready(function(){

	$(document).endless({

		direction:'down',
		scrollbar:'enable',
		prepend:function(){alert("there isn't the boundary at the top");}, 

		append:function(){alert("there isn't the boundary at the bottom");}, 

		n_prepend:function(){alert("you are now at the top");}, 

		n_append:function(){alert("you are now at the bottom");}

	});

});