/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	//config.toolbarLocation = 'bottom';//可选：bottom
	config.removePlugins = 'elementspath';//
	config.allowedContent=true;
	config.pasteFromWordRemoveFontStyles = false;
	config.pasteFromWordRemoveStyles = false;
    config.toolbar = 'Mytool';//config.toolbar = 'Basic';
    config.extraPlugins = 'himage,syntaxhighlight,autogrow,imageresize'; //自定义插件，多个用逗号分隔
    config.disableObjectResizing = false;//允许调整
    config.autoGrow_maxHeight = 400;
    //config.autoGrow_bottomSpace = 80;
    config.resize_enabled = true; 
    //config.resize_maxHeight = 800; 
    config.resize_maxWidth = 700; 
    config.resize_minHeight = 100; 
    config.resize_minWidth = 100; 
    config.toolbar_Mytool = 
    [
		['Bold','Italic','Underline','Strike'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['Font','FontSize'],
		['TextColor','BGColor','himage','Maximize']
	];
    
    config.toolbar_Full =   
    [   
        ['Source','-','Save','NewPage','Preview','-','Templates'],   
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],   
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],   
        ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],   
        ['BidiLtr', 'BidiRtl'],   
        '/',   
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],   
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],   
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],   
        ['Link','Unlink','Anchor'],   
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],   
        '/',   
        ['Styles','Format','Font','FontSize'],   
        ['TextColor','BGColor'],   
        ['Maximize', 'ShowBlocks','-','About']   
    ];   
      
    config.toolbar_Basic =   
    [   
        ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']   
    ]; 
};
