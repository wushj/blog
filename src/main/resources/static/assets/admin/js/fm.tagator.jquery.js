/*
 Tagator jQuery Plugin
 version 1.0, Jan 13th, 2014
*/

(function($) {
	$.tagator = function (element, options) {
		var defaults = {
			prefix: 'tagator_',
			height: 'auto',
			useDimmer: false,
			showAllOptionsOnFocus: false,
			autocomplete: []
		};
			
		var plugin = this;
		var selected_index = -1;
		var box_element = null;
		var tags_element = null;
		var input_element = null;
		var textlength_element = null;
		var options_element = null;
		var key = {
			backspace: 8,
			enter: 13,
			escape: 27,
			left: 37,
			up: 38,
			right: 39,
			down: 40,
			comma: 188
		};
		plugin.settings = {};

		
		// INITIALIZE PLUGIN
		plugin.init = function () {
			plugin.settings = $.extend({}, defaults, options);

			//// ================== CREATE ELEMENTS ================== ////
			// dimmer
			if (plugin.settings.useDimmer) {
				if ($('#' + plugin.settings.prefix + 'dimmer').length === 0) {
					var dimmer_element = document.createElement('div');
					$(dimmer_element).attr('id', plugin.settings.prefix + 'dimmer');
					$(dimmer_element).hide();
					$(document.body).prepend(dimmer_element);
				}
			}
			// box element
			box_element = document.createElement('div');
			if (element.id !== undefined) {
				$(box_element).attr('id', plugin.settings.prefix + element.id);
			}
			$(box_element).addClass('tagator options-hidden');
			$(box_element).css({
				width: $(element).css('width'),
				padding: $(element).css('padding'),
				position: 'relative'
			});
			if (plugin.settings.height === 'element') {
				$(box_element).css({
					height: $(element).outerHeight() + 'px'
				});
			}
			$(element).after(box_element);
			$(element).hide();
			// textlength element
			textlength_element = document.createElement('span');
			$(textlength_element).addClass(plugin.settings.prefix + 'textlength');
			$(textlength_element).css({
				position: 'absolute',
				visibility: 'hidden'
			});
			$(box_element).append(textlength_element);
			// tags element
			tags_element = document.createElement('div');
			$(tags_element).addClass(plugin.settings.prefix + 'tags');
			$(box_element).append(tags_element);
			// input element
			input_element = document.createElement('input');
			$(input_element).addClass(plugin.settings.prefix + 'input');
			$(input_element).width(20);
			$(input_element).attr('autocomplete', 'false');
			$(box_element).append(input_element);
			// options element
			options_element = document.createElement('ul');
			$(options_element).addClass(plugin.settings.prefix + 'options');

			$(box_element).append(options_element);

			//// ================== BIND ELEMENTS EVENTS ================== ////
			// source element
			$(element).change(function () {
				refreshTags();
			});
			// box element
			$(box_element).bind('focus', function (e) {
				e.preventDefault();
				e.stopPropagation();
				showOptions();
				$(input_element).focus();
			});
			$(box_element).bind('mousedown', function (e) {
				e.preventDefault();
				e.stopPropagation();
				input_element.focus();
				if (input_element.setSelectionRange) {
					input_element.focus();
					input_element.setSelectionRange(input_element.value.length, input_element.value.length);
				} else if (input_element.createTextRange) {
					var range = input_element.createTextRange();
					range.collapse(true);
					range.moveEnd('character', input_element.value.length);
					range.moveStart('character', input_element.value.length);
					range.select();
				}
			});
			$(box_element).bind('mouseup', function (e) {
				e.preventDefault();
				e.stopPropagation();
			});
			$(box_element).bind('click', function (e) {
				e.preventDefault();
				e.stopPropagation();
				if (plugin.settings.showAllOptionsOnFocus) {
					//showOptions();
					searchOptions();
				}
				input_element.focus();
			});
			$(box_element).bind('dblclick', function (e) {
				e.preventDefault();
				e.stopPropagation();
				input_element.focus();
				input_element.select();
			});
			// input element
			$(input_element).bind('click', function (e) {
				e.preventDefault();
				e.stopPropagation();
			});
			$(input_element).bind('dblclick', function (e) {
				e.preventDefault();
				e.stopPropagation();
			});
			$(input_element).bind('keydown', function (e) {
				e.stopPropagation();
				var keyCode = e.keyCode || e.which;
                switch (keyCode) {
					case key.up:
						e.preventDefault();
						if (selected_index > -1) {
							selected_index = selected_index - 1;
						} else {
							selected_index = $(options_element).find('.' + plugin.settings.prefix + 'option').length - 1;
						}
						refreshActiveOption();
						scrollToActiveOption();
						break;
					case key.down:
						e.preventDefault();
						if (selected_index < $(options_element).find('.' + plugin.settings.prefix + 'option').length - 1) {
							selected_index = selected_index + 1;
						} else {
							selected_index = -1;
						}
						refreshActiveOption();
						scrollToActiveOption();
						break;
					case key.escape:
						e.preventDefault();
						break;
					case key.comma:
						e.preventDefault();
						if (selected_index === -1) {
							if ($(input_element).val() !== '') {
								addTag($(input_element).val());
							}
						}
						resizeInput();
						break;
					case key.enter:
						e.preventDefault();
						if (selected_index !== -1) {
							selectOption();
						}else{
							var text = $(input_element).val();
							if (text !== '') {
			                	blurTag(text);
			                }
						}
						resizeInput();
						break;
					case key.backspace:
						if (input_element.value === '') {
							var tags = getTagObjects();
							if(tags.length >= 1){
								var lastobj = tags[tags.length-1];
								removeTag(lastobj);
								$(element).trigger('change');
							}
							searchOptions();
						}
						resizeInput();
						break;
					default:
						resizeInput();
						break;
				}
			});
			$(input_element).bind('keyup', function (e) {
				// 动态获取后台标签缓存数据
                var searchKey = $(input_element).val();
                if(searchKey.trim() !== null && searchKey.trim() !== undefined && searchKey.trim() !== ''){
					$.ajax({
						type: 'GET',
						url: '/tag/getTagByName/'+searchKey,
						data : 'name='+searchKey,
						contentType: "application/json; charset=utf-8",
						async:false,
						success: function(data){
							plugin.autocomplete(data);
						},
						error: function(msg){
                            layer.msg(msg, {
                                time: 1000 //1s后自动关闭
                            });
						}
					});
                }
				//end

				e.preventDefault();
				e.stopPropagation();
				var keyCode = e.keyCode || e.which;
				if (keyCode === key.escape || keyCode === key.enter) {
					hideOptions();
				} else if (keyCode < 37 || keyCode > 40) {
					searchOptions();
				}
				if ($(box_element).hasClass('options-hidden') && (keyCode === key.left || keyCode === key.right || keyCode === key.up || keyCode === key.down)) {
					searchOptions();
				}
				resizeInput();
			});
			$(input_element).bind('focus', function (e) {
				e.preventDefault();
				e.stopPropagation();
				if (!$(options_element).is(':empty') || plugin.settings.showAllOptionsOnFocus) {
					searchOptions();
					showOptions();
				}
			});
			$(input_element).bind('blur', function (e) {
				e.preventDefault();
				e.stopPropagation();
				hideOptions();
                var text = $(input_element).val();
                if (text !== '') {
                	blurTag(text);
                }
			});
			refreshTags();
		};

		
        function blurTag(text) {
        	//如果标签不存在则保存tag到数据库
            if (!hasTagText(getTagObjects(),text) && !hasTagText(plugin.settings.autocomplete,text)) {
            	openLayer(text, addTag);
            //标签存在离开焦点后直接放置
            } else if (!hasTagText(getTagObjects(),text) && hasTagText(plugin.settings.autocomplete,text)) {
            	var autoCompletes = $(options_element).find('.' + plugin.settings.prefix + 'option');
            	var index = 0;
            	for (var int = 0; int < autoCompletes.length; int++) {
					if(autoCompletes[int].innerText === text){
						index = int;
						break;
					}
				}
            	var objstr = $(options_element).find('.' + plugin.settings.prefix + 'option').eq(index).data('text');
                addTag(objstr);
            }
        }

		// RESIZE INPUT
		var resizeInput = function () {
			textlength_element.innerHTML = input_element.value;
			$(input_element).css({ width: ($(textlength_element).width() + 20) + 'px' });
		};



		// SET AUTOCOMPLETE LIST
		plugin.autocomplete = function (autocomplete) {
			plugin.settings.autocomplete = autocomplete !== undefined ? autocomplete : [];
		};

		

		// REFRESH TAGS
		plugin.refresh = function () {
			refreshTags();
		};
		
		function getTagObjects() {
			if($(element).val() != '' && $(element).val() != undefined && $(element).val() != null){
				if(typeof($(element).val()) == 'string'){
					var obj = eval('(' + $(element).val() + ')');
					return obj;
				}else{
					return $(element).val();
				}
			}else{
				return [];
			}
		}
		var refreshTags = function () {
			$(tags_element).empty();
			/*var tags = $(element).val().split(' ');*/
			var tags = getTagObjects();
			$.each(tags, function (key, value) {
				if (value !== '') {
					var tag_element = document.createElement('div');
					$(tag_element).html(value.name);
					// remove button
					var button_remove_element = document.createElement('div');
					$(button_remove_element).data('text', value);
					$(button_remove_element).addClass(plugin.settings.prefix + 'tag_remove');
					$(button_remove_element).bind('mousedown', function (e) {
						e.preventDefault();
						e.stopPropagation();
					});
					$(button_remove_element).bind('mouseup', function (e) {
						e.preventDefault();
						e.stopPropagation();
						removeTag($(this).data('text'));
						$(element).trigger('change');
					});
					$(button_remove_element).html('×');
					$(tag_element).append(button_remove_element);
					// clear
					var clear_element = document.createElement('div');
					clear_element.style.clear = 'both';
					$(tag_element).append(clear_element);
	
					$(tags_element).append(tag_element);
				}
			});
			searchOptions();
		};
		
		// REMOVE TAG FROM ORIGINAL ELEMENT
		var removeTag = function (text) {
			var tagsBefore = getTagObjects();
			var tagsAfter = [];
			$.each(tagsBefore, function (key, value) {
				if (value.id != text.id) {
					tagsAfter.push(value);
				}
			});
			$(element).val(JSON.stringify(tagsAfter));
		};
		
		// CHECK IF TAG IS PRESENT
		var hasTag = function (text) {
			var tags = getTagObjects();
			var hasTag = false;
			$.each(tags, function (key, value) {
				if (value.id === text.id) {
					hasTag = true;
				}
			});
			return hasTag;
		};

		var hasTagText = function (tags,text) {
			var hasTag = false;
			$.each(tags, function (key, value) {
				if (value.name === text) {
					hasTag = true;
				}
			});
			return hasTag;
		};

		// ADD TAG TO ORIGINAL ELEMENT
		var addTag = function (text) {
			if (!hasTag(text)) {
				var elements = getTagObjects();
				if(elements != '' && elements != undefined || elements != null){
					elements.push(text);
					$(element).val(JSON.stringify(elements));
					$(element).trigger('change');
				}
				
			}
			$(input_element).val('');
			box_element.focus();
			hideOptions();
		};



		// OPTIONS SEARCH METHOD
		var searchOptions = function () {
			$(options_element).empty();
			if (input_element.value.replace(/\s/g, '') !== '' || plugin.settings.showAllOptionsOnFocus) {
				var optionsArray = [];
				$.each(plugin.settings.autocomplete, function (key, value) {
					if (!hasTag(value)) {
						optionsArray.push(value);
					}
				});
				generateOptions(optionsArray);
			}
			if ($(input_element).is(':focus')) {
				if (!$(options_element).is(':empty')) {
					showOptions();
				} else {
					hideOptions();
				}
			} else {
				hideOptions();
			}
			selected_index = -1;
		};

		// GENERATE OPTIONS
		var generateOptions = function (optionsArray) {
			var index = -1;
			$(optionsArray).each(function (key, value) {
				index++;
				var option = createOption(value, index);
				$(options_element).append(option);
			});
			refreshActiveOption();
		};

		// CREATE RESULT OPTION
		var createOption = function (text, index) {
			// holder li
			var option = document.createElement('li');
			$(option).data('index', index);
			$(option).data('text', text);
			$(option).html(text.name);
			$(option).addClass(plugin.settings.prefix + 'option');
			if (this.selected) {
				$(option).addClass('active');
			}

			// BIND EVENTS
			$(option).bind('mouseover', function (e) {
				e.stopPropagation();
				e.preventDefault();
				selected_index = index;
				refreshActiveOption();
			});
			$(option).bind('mousedown', function (e) {
				e.stopPropagation();
				e.preventDefault();
			});
			$(option).bind('click', function (e) {
				e.preventDefault();
				e.stopPropagation();
				selectOption();
			});


			return option;
		};

		// SHOW OPTIONS AND DIMMER
		var showOptions = function () {
			$(box_element).removeClass('options-hidden').addClass('options-visible');
			if (plugin.settings.useDimmer) {
				$('#' + plugin.settings.prefix + 'dimmer').show();
			}
			$(options_element).css('top', ($(box_element).outerHeight()-2) + 'px');
			if ($(box_element).hasClass('single')) {
				selected_index = $(options_element).find('.' + plugin.settings.prefix + 'option').index($(options_element).find('.' + plugin.settings.prefix + 'option.active'));
			}
			scrollToActiveOption();
		};

		// HIDE OPTIONS AND DIMMER
		var hideOptions = function () {
			$(box_element).removeClass('options-visible').addClass('options-hidden');
			if (plugin.settings.useDimmer) {
				$('#' + plugin.settings.prefix + 'dimmer').hide();
			}
		};

		// REFRESH ACTIVE IN OPTIONS METHOD
		var refreshActiveOption = function () {
			$(options_element).find('.active').removeClass('active');
			if (selected_index !== -1) {
				$(options_element).find('.' + plugin.settings.prefix + 'option').eq(selected_index).addClass('active');
			}
		};

		// SCROLL TO ACTIVE OPTION IN OPTIONS LIST
		var scrollToActiveOption = function () {
			var $active_element = $(options_element).find('.' + plugin.settings.prefix + 'option.active');
			if ($active_element.length > 0) {
				$(options_element).scrollTop($(options_element).scrollTop() + $active_element.position().top - $(options_element).height()/2 + $active_element.height()/2);
			}

		};

		// SELECT ACTIVE OPTION
		var selectOption = function () {
			addTag($(options_element).find('.' + plugin.settings.prefix + 'option').eq(selected_index).data('text'));
		};



		// REMOVE PLUGIN AND REVERT INPUT ELEMENT TO ORIGINAL STATE
		plugin.destroy = function () {
			$(box_element).remove();
			$.removeData(element, 'tagator');
			$(element).show();
			if ($('.tagator').length === 0) {
				$('#' + plugin.settings.prefix + 'dimmer').remove();
			}
		};

		// Initialize plugin
		plugin.init();
	};
	
	$.fn.tagator = function() {
		var parameters = arguments[0] !== undefined ? arguments : [{}];
		return this.each(function () {
			if (typeof(parameters[0]) === 'object') {
				if (undefined === $(this).data('tagator')) {
					var plugin = new $.tagator(this, parameters[0]);
					$(this).data('tagator', plugin);
				}
			} else if ($(this).data('tagator')[parameters[0]]) {
				$(this).data('tagator')[parameters[0]].apply(this, Array.prototype.slice.call(parameters, 1));
			} else {
				$.error('Method ' + parameters[0] + ' does not exist in $.tagator');
			}
		});
	};

}(jQuery));
