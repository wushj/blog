;(function () {



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


}());