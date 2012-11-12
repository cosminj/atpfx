
Ext.application({
    name: 'Atpfx',
    controllers : ['Ticks'],
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            items: {
                html: 'Atpfx'
            }
        });
    }
});