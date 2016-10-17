<%@page import="com.liferay.portal.kernel.uuid.PortalUUIDUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portlet.PortletURLFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.ServletContextUtil"%>
<%
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/init.jsp"%>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<a class="big-bat" href="https://github.com/binhth"><img style="position: absolute; top: -35px; right: 0; border: 0;z-index: 9999;" src="/build-it-portlet/fork2.png" alt="Fork me on GitHub" ></a>
	<a id="imgBottom" style="bottom: 0px;" href="https://github.com/binhth"><img src="/build-it-portlet/logo.png" style="right: 0px; border: 0px none; z-index: 9999; bottom: -50px; position: fixed; " alt="Fork me on GitHub"></a>
	<nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#" style="color: #fff;line-height: 50px;">Form builder v.1.x</a>
	<img src="/build-it-portlet/logo.png" style="height: 50px ! important;" />
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active">
		<a id="edit" href="#"><i class="glyphicon glyphicon-edit "></i> Chỉnh sửa</a>
	</li>	
	<li>
		<a id="sourcepreview" href="#"><i class="glyphicon-eye-open glyphicon"></i> Xem trước</a>
	</li>
<li> <a id="wordToForm" href="javascript:void(0);" onclick='<%="javavscript:" + renderResponse.getNamespace() + "convertToBigBat(this)" %>'><i style="margin-right: 5px;" class="glyphicon-folder-open glyphicon"></i> Chuyển đổi văn bản sang Form trực tuyến</a> </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
		<a style="padding: 10px 0px 0px ! important;" href="#" >
<img src='/build-it-portlet/downloadbg.png' width='150' onmouseover="this.src='/build-it-portlet/downloadbg2.png';" onmouseout="this.src='/build-it-portlet/downloadbg.png';" />
		</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	<nav id="supportControl" class="navbar  navbar-fixed" style="background-color: rgb(250, 250, 250) ! important; z-index: 0; top: -70px; width: 106%; left: -15px; margin: 0px ! important; padding: 0px ! important; height: 78px; border-bottom: 1px solid rgb(238, 238, 238);">
      <div style="padding: 27px 0px 0px 10px;" class="container">
        <div id="navbar" class="navbar-collapse collapse">
          
          <ul class="nav navbar-nav">
<li>
          <a id="bbDownloadLinkAlpaca" href="javascript:void(0);" onclick='<%="javavscript:" + renderResponse.getNamespace() + "getAlpacaJSToDownload(this)" %>' class="btn-sm-bb with-counter">
              
            <i class="glyphicon-share glyphicon"></i> Mã tạo form
          </a>

          

    <a class="social-count-bb" id="bat-control-count-1">
      0
    </a>
  </li>
<li>
          <a id="bbDownloadLinkJasper" href="#fork-destination-box" class="btn-sm-bb with-counter">
              
            <i class="glyphicon-share glyphicon"></i> Jasper Report
          </a>

          

    <a class="social-count-bb" id="bat-control-count-2">
      0
    </a>
  </li>
<li>
          <a id="bbDownloadLinkSampleData" href="javascript:void(0);" onclick='<%="javavscript:" + renderResponse.getNamespace() + "getSampleDataToDownload(this)" %>' class="btn-sm-bb with-counter">
              
            <i class="glyphicon-share glyphicon"></i> Dữ liệu mẫu
          </a>

          

    <a class="social-count-bb" id="bat-control-count-3">
      0
    </a>
  </li>
            
          </ul>
<ul style="margin-right: 220px ! important;" class="nav navbar-nav navbar-right">
            <li>
		<a style="color: #fff;"  class="remove label label-danger" id="clear"><i class="glyphicon-trash glyphicon"></i> Làm mới </a>
	</li>
		
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	<liferay-util:include page='/html/control.jsp' servletContext="<%=application %>" />
	
	<div style="min-height: 482px; background-color: #fff; background: #fff;padding: 10px;" class="demo ui-sortable">

		<div style="display: block;" class="lyrow ui-draggable">
			<a href="#close" class="remove label label-danger"><i class="glyphicon-remove glyphicon"></i> Xóa bỏ</a>
			<span class="drag label label-default  ui-draggable-handle"><i class="glyphicon glyphicon-move"></i>Kéo</span>
			<div class="preview"><input value="12" placeholder="Enter your own" class="form-control" type="text"></div>
			<div class="view">
				<div class="row">
								<div class="col-md-12 column ui-sortable" id="wordForm"></div>
							</div>
			</div>
		</div>

	</div>

	<div class="powered-by col-xs-12 text-right">
		Author <a href="http://github.com/binhth" target="_blank">binhth</a>
	</div>
	
	<div id="download-layout-root"><div class="container-fluid" id="download-layout"></div></div>
       <aui:input type="hidden" name="myHiddenBat" />
       <portlet:actionURL name="buildSampleData" var="buildSampleDataURL" />
       <portlet:actionURL name="liveDemo" var="liveDemoURL" />
       <portlet:actionURL name="liveDemo2" var="liveDemo2URL" />
<script type="text/javascript">

	$(document).ready(function(){
		$('.mTooltip').tooltip();
		$("#sourcepreview").click(function() {
			$("#sourcepreview").parent().attr('class', 'active');
			$("#edit").parent().attr('class', '');
		});
		$("#edit").click(function() {
			$("#sourcepreview").parent().attr('class', '');
			$("#edit").parent().attr('class', 'active');
		});
		$('body').addClass('edit builder');
		$('#imgBottom').hide();
		$(window).scroll(function(){
		    if ( document.body.scrollHeight - $(this).scrollTop()  <= $(this).height() ){
		        $('#imgBottom').show();
		    } else {
		        $('#imgBottom').hide();
		    }
		});
		$(".dropdown-toggle").dropdown();
		
		Liferay.provide(window, '<portlet:namespace/>configAlpacajs', function(e) {
			var A = AUI();
			var instance = A.one(e);
			var currentViewBB = $(e).parent().parent().find('.viewBB');
			console.log(currentViewBB);
			var viewBBID = Math.random().toString().replace('.','');
			$(e).attr('id', 'config-temp-'+viewBBID);
			currentViewBB.attr('id', 'view-temp-' + viewBBID);
			var portletURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, "buildit_WAR_builditportlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');
			portletURL.setParameter("mvcPath", "/html/config.jsp");
			portletURL.setParameter("controlName", instance.attr('control-name'));
			portletURL.setParameter("controlType", instance.attr('control-type'));
			portletURL.setParameter("controlNameTemp", $(e).attr('control-name-temp'));
			portletURL.setParameter("viewBBID", viewBBID);
			portletURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>'); 
			portletURL.setPortletMode('view');
			openDialog(portletURL.toString(), '<portlet:namespace />configAlpacajs','<%= UnicodeLanguageUtil.get(pageContext, "popup-config-alpacajs") %>');
		},['aui-io','liferay-portlet-url']);
		Liferay.provide(window, '<portlet:namespace/>configAlpacajsConvert', function(e) {
			var A = AUI();
			var instance = A.one(e);
			var portletURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, "buildit_WAR_builditportlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');
			portletURL.setParameter("mvcPath", "/html/config_convert.jsp");
			portletURL.setParameter("controlName", instance.attr('control-name'));
			portletURL.setParameter("controlType", instance.attr('control-type'));
			portletURL.setParameter("controlStyle", instance.attr('control-style'));
			portletURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>'); 
			portletURL.setPortletMode('view');
			openDialog(portletURL.toString(), '<portlet:namespace />configAlpacajs','<%= UnicodeLanguageUtil.get(pageContext, "popup-config-alpacajs") %>');
		},['aui-io','liferay-portlet-url']);
		
		Liferay.provide(window, '<portlet:namespace/>convertToBigBat', function(e) {
			var A = AUI();
			var instance = A.one(e);
			var portletURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, "buildit_WAR_builditportlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');
			portletURL.setParameter("mvcPath", "/html/convert_word.jsp");
			portletURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>'); 
			portletURL.setPortletMode('view');
			openDialog(portletURL.toString(), '<portlet:namespace />configAlpacajs','<%= UnicodeLanguageUtil.get(pageContext, "popup-convert-word-to-form") %>');
		},['aui-io','liferay-portlet-url']);
		
		Liferay.provide(window, '<portlet:namespace/>configSampleData', function(e) {
			var A = AUI();
			var instance = A.one(e);
			var batSample = $(e).parent().parent().parent().parent().find('.btn.btn-mini');
			
			
// 			$.each(localStorage, function(key, value){

// 				  // key magic
// 				  // value magic
// 				  console.log(key+"/"+value);

// 				});
			var localOptions = localStorage.getItem("sample-data-baby-bat");
			 
			A.io.request(
					'<%= buildSampleDataURL.toString() %>',
					{
						dataType : 'json',
						method : 'POST',
					    data:{    	
					    	"<portlet:namespace />name": batSample.attr("control-name"),
					    	"<portlet:namespace />batSample": $(e).attr("rel"),
					    	"<portlet:namespace />batSampleLocal": JSON.stringify(eval("("+localOptions+")"))
					    },   
					    on: {
					    	success: function(event, id, obj) {
					    		
					    		var instance = this;
								var res = instance.get('responseData');
							
								localStorage.setItem("sample-data-baby-bat",res.sampleTemp);
								
								var optJsonSchema = eval('(' + res.sampleTemp + ')');

								$('#bat-control-count-3').html(Object.keys(optJsonSchema).length);
								
							},
					    	error: function(){}
						}
					}
				);
		},['aui-io','liferay-portlet-url']);
		
		Liferay.on('processHTML',function(event) {
// 			console.log(event.responseData.objectSchema);
// 			console.log(event.responseData.objectOptions);
			if (supportstorage()) {
				localStorage.setItem("schema-"+event.responseData.tempId,event.responseData.objectSchema);
				localStorage.setItem("options-"+event.responseData.tempId,event.responseData.objectOptions);
				localStorage.setItem("options-adv-"+event.responseData.tempId,event.responseData.objectOptionsAdv);
				localStorage.setItem("objectSchemaAttr-"+event.responseData.tempId,event.responseData.objectSchemaAttr);
				localStorage.setItem("objectOptionsAttr-"+event.responseData.tempId,event.responseData.objectOptionsAttr);
			}
			
			
			var configTempElement = $('#config-temp-'+event.responseData.viewBBID);
			var blindElement = $('#view-temp-'+event.responseData.viewBBID);
			configTempElement.parent().append('<span style="position: absolute; right: 120px; top: 5px; color: rgb(39, 165, 82);" class="glyphicon glyphicon-saved" aria-hidden="true"></span>');
			configTempElement.attr('control-name', event.responseData.tempId);
			configTempElement.attr('control-name-temp', event.responseData.tempId);
			blindElement.parent().parent().attr('id', 'column-'+event.responseData.tempId);
			blindElement.html(event.responseData.html);
			var counterControl = 0;
			$.each(localStorage, function(key, value){

				  if(key.startsWith("schema-")){
					  counterControl = counterControl+1;
				  }

			});
			$('#bat-control-count-1').html(counterControl);
			if(counterControl > 0){
				$('#bat-control-count-2').html(1);
			}else{
				$('#bat-control-count-2').html(0);
			}
			
// 			$('#bat-control-count-3').html(event.responseData.numberOfControl);
		});
		
		Liferay.on('processHTMLWordControl',function(event) {
// 			console.log(event.responseData.objectSchema);
// 			console.log(event.responseData.objectOptions);sampleDataBat
			
			$("#column-"+event.responseData.tempId).html(event.responseData.html);
			
			var upgradeView = localStorage.getItem("word-bat-alpacajs-batWordView");
			var myObject = eval('(' + upgradeView.toString() + ')');
// 			console.log(JSON.stringify(myObject));
			myObject["styles"]["#"+event.responseData.tempId]["width"] = event.responseData.styleWidth + "px !important;";
			if(event.responseData.styleWidth > 0){
				localStorage.setItem("word-bat-alpacajs-fake-style-"+event.responseData.tempId, "#"+event.responseData.tempId+" {width: "+event.responseData.styleWidth+"px !important;}");
			}
			
			localStorage.setItem("word-bat-alpacajs-batWordView", JSON.stringify(myObject));
// 			console.log(localStorage.getItem("word-bat-alpacajs-batWordView"));
			
			$("#"+event.responseData.tempId).after('<button type="button" style="position: absolute;" class="btn btn-mini tooltiptext" href="javascript:void(0);" onclick=\''+ '<%="javavscript:" + renderResponse.getNamespace() + "configAlpacajsConvert(this)" %>' +'\'  control-style=\"'+event.responseData.styleWidth+'\" control-name=\"'+event.responseData.tempId+'\" control-type=\"control-'+event.responseData.controlType+'\"><i class="fa fa-cog " ></i><span class="tooltip">Cấu hình form alpacaJS</span></button>');
			var counterControl = 0;
			var counterSampleData = 0;
			$.each(localStorage, function(key, value){

				  if(key.startsWith("word-bat-control-schema-")){
					  counterControl = counterControl+1;
				  }
				  if(key.startsWith("word-bat-control-sampleDataBat-")){
					  counterSampleData = counterSampleData+1;
				  }
				  
			});
			$('#bat-control-count-1').html(counterControl);
			if(counterControl > 0){
				$('#bat-control-count-2').html(1);
			}else{
				$('#bat-control-count-2').html(0);
			}
			$('#bat-control-count-3').html(counterSampleData);
		});
		
		Liferay.on('processHTMLConvert',function(event) {
			
// 			var htmlmInit = $('#wordForm').html(event.responseData.alpaca);
// 			console.log(eval("("+event.responseData.alpaca+")"));
			var alpaca = event.responseData.alpaca;
			var batControl = event.responseData.batControl;
// 			$.each(eval(batControl), function( index, value ) {
				console.log(batControl);
// 			});
			$("#<portlet:namespace/>myHiddenBat").val(alpaca.replace(/\\\//g,'/'));
// 			console.log(alpaca.replace(/\\\//g,'/'));
			$("#wordForm").html("<img id=\"tempLoading\" style=\"width: 183px; left: 50%; position: relative; transform: translateX(-50%);\" src=\"/build-it-portlet/loading.gif\" />");
// 			setTimeout(function(){
// 				console.log($("#<portlet:namespace/>myHiddenBat").val());
				
				A.io.request(
						'<%= liveDemoURL.toString() %>',
						{
							dataType : 'json',
							method : 'POST',   
						    on: {
						    	success: function(event, id, obj) {
						    		
									var instance = this;
									var res = instance.get('responseData');
							
									var alpacaBatobj = eval('(' + $("#<portlet:namespace/>myHiddenBat").val() + ')');
									swal("<%= UnicodeLanguageUtil.get(pageContext, "boom-bat") %> !", "<%= UnicodeLanguageUtil.get(pageContext, "boom-bat-info") %>!", "success");
									$("#wordForm").alpaca(alpacaBatobj);
									$("#tempLoading").remove();
									setTimeout(function(){
//						 				console.log($("#<portlet:namespace/>myHiddenBat").val());
										
										A.io.request(
												'<%= liveDemo2URL.toString() %>',
												{
													dataType : 'json',
													method : 'POST',   
												    on: {
												    	success: function(event, id, obj) {
												    		
															var instance = this;
															var res = instance.get('responseData');
// 															console.log(eval(batControl));
															$.each(eval(batControl), function( index, value ) {
												 				$(value).after('<button type="button" style="position: absolute;" class="btn btn-mini tooltiptext" href="javascript:void(0);" onclick=\''+ '<%="javavscript:" + renderResponse.getNamespace() + "configAlpacajsConvert(this)" %>' +'\'  control-name=\"'+$(value).attr("id")+'\" control-type="control-text"><i class="fa fa-cog " ></i><span class="tooltip">Cấu hình form alpacaJS</span></button>');
												 				localStorage.setItem("word-bat-control-schema-"+$(value).attr("id"),"{\"required\": true, \"type\": \"object\"}");
																localStorage.setItem("word-bat-control-option-"+$(value).attr("id"),"{\"id\": \""+$(value).attr("id")+"\", \"name\": \""+$(value).attr("id")+"\", \"type\": \"text\", \"label\": \"\", \"fieldClass\": \"form-input-100\", \"placeholder\": \""+$(value).attr("id")+"\"}");
															});
															var counterControl = 0;
															$.each(localStorage, function(key, value){

																  if(key.startsWith("word-bat-control-schema-")){
																	  counterControl = counterControl+1;
																  }

															});
															$('#bat-control-count-1').html(counterControl);
															if(counterControl > 0){
																$('#bat-control-count-2').html(1);
															}else{
																$('#bat-control-count-2').html(0);
															}
															
															$("#bbDownloadLinkAlpaca").attr("bat-word",'batWord');
<%-- 															$("#bbDownloadLinkJasper").attr("onclick",'<%="javavscript:" + renderResponse.getNamespace() + "getSampleDataToDownload(this)" %>'); --%>
															$("#bbDownloadLinkSampleData").attr("bat-word",'batWord');
														},
												    	error: function(){}
													}
												}
											);
										
									}, 1000);	
								},
						    	error: function(){}
							}
						}
					);
				
// 			}, 2000);	
			
			
		});
		
		Liferay.provide(window, '<portlet:namespace/>getAlpacaJSToDownload', function(e) {
			var A = AUI();
			var instance = A.one(e);
			var viewConfig='';
			
			var counterControl = $('#bat-control-count-1').html();
			if(counterControl > 0){
				$("#download-layout").remove();
				$("#download-layout-root").append($("<div></div>").attr('id','download-layout'))
				 
				$("#download-layout").append($( ".demo.ui-sortable" ).clone());

				$( "#download-layout .view" ).each(function( index ) {
						  $( this ).find(".ui-sortable .configuration .btn-mini").each(function( index, element ) {
		
				        if($( element ).parent().parent().parent().attr('id') != null
				          && $( element ).parent().parent().parent().attr('id') != 'undefined'){
				          viewConfig += '"'+$( element ).attr('control-name')+'": ' + '"' + $( element ).parent().parent().parent().attr('id') + '", ';
				        }
						  });
		
						});
		
						$( "#download-layout .view" ).each(function( index ) {
						  $( this ).find(".ui-sortable").each(function( index, element ) {
						    $( element ).empty();
						  });
		
						});
				var viewTemplate = '<div class="container-fluid">';
						$( "#download-layout .row" ).each(function( index ) {
				        viewTemplate += '<div class="row-fluid">'+$( this ).html().replace(/\ ui-sortable/g, '')+'</div>';
						});
		
				var pos = viewConfig.lastIndexOf(',');
				viewConfig = viewConfig.substring(0,pos);
				viewTemplate += '</div>';
				viewTemplate = viewTemplate.replace(/\"/g, "'" );
		//			console.log(viewConfig);
		//			 console.log( viewTemplate);
				var batView = "\"view\": {\"parent\": \"bootstrap-edit\",\"layout\": {\"template\": \""+viewTemplate+"\",\"bindings\": {"+viewConfig+"}},\"fields\": {}}";
				localStorage.setItem("view-regular", batView);
				var portletURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, "buildit_WAR_builditportlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');
				portletURL.setParameter("mvcPath", "/html/get_alpacajs.jsp");
				portletURL.setParameter("viewConfig", viewConfig);
				portletURL.setParameter("viewTemplate", viewTemplate);
				portletURL.setParameter("batWord", instance.attr('bat-word'));
				portletURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>'); 
				portletURL.setPortletMode('view');
				openDialog(portletURL.toString(), '<portlet:namespace />configAlpacajs','<%= UnicodeLanguageUtil.get(pageContext, "popup-config-alpacajs") %>');
			}else{
				alert('<%= UnicodeLanguageUtil.get(pageContext, "chua-cau-hinh-control") %>');
			}
			
			
		},['aui-io','liferay-portlet-url']);
		
		Liferay.provide(window, '<portlet:namespace/>getSampleDataToDownload', function(e) {
			var A = AUI();
			var instance = A.one(e);
			var counterControl = $('#bat-control-count-1').html();
			if(counterControl > 0){
				var portletURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, "buildit_WAR_builditportlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');
				portletURL.setParameter("mvcPath", "/html/get_sample_data.jsp");
				portletURL.setParameter("batWord", instance.attr('bat-word'));
				portletURL.setWindowState('<%=LiferayWindowState.POP_UP.toString() %>'); 
				portletURL.setPortletMode('view');
				openDialog(portletURL.toString(), '<portlet:namespace />configAlpacajs','<%= UnicodeLanguageUtil.get(pageContext, "popup-sample-data-alpacajs") %>');
			}else{
				alert('<%= UnicodeLanguageUtil.get(pageContext, "chua-cau-hinh-control") %>');
			}
			
		},['aui-io','liferay-portlet-url']);
			});
</script>

