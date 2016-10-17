Liferay.provide(window, 'openDialog', function(uri, id, title) {
	var opencpsDialog = Liferay.Util.openWindow(
		{
			dialog: {
				cache: false,
				cssClass: 'bat-dialog',
				modal: true,
				width: $(window).width() * 0.8
			},
			cache: false,
			id: id,
			title: title,
			uri: uri
			
		},function(evt){
			
		}
	);
});