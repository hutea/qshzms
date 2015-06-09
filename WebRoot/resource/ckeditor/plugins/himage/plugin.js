CKEDITOR.plugins.add( 'himage', {
    icons: 'himage',
    init: function( editor ) {
        editor.addCommand( 'himage', new CKEDITOR.dialogCommand( 'himageDialog' ) );
        editor.ui.addButton( 'himage', {
            label: '插入图片',
            command: 'himage',
            toolbar: 'insert'
        });
        CKEDITOR.dialog.add( 'himageDialog', this.path + 'dialogs/himage.js' );
    }
});

