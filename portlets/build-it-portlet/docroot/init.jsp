<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>

<portlet:defineObjects />
<%
ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
%>


<portlet:actionURL name="removeControl" var="removeControllURL" />
<script type="text/javascript">
var A = AUI();
function removeElm() {
	$(".demo").delegate(".remove", "click", function(e) {
		e.preventDefault();
	
		var countChk = 0;

		var controlNameTemp = $(this).parent().find(".configuration .btn-mini");
		
		console.log($(this).parent().find(".configuration .btn-mini"));
		
		if($(this).attr("bat-remove") === 'control'){
			
			A.io.request(
					'<%= removeControllURL.toString() %>',
					{
						dataType : 'json',
						method : 'POST',
					    data:{    	
					    	"<portlet:namespace />controlNameTemp": controlNameTemp.attr("control-name-temp")
					    },   
					    on: {
					    	success: function(event, id, obj) {
					    		
								var instance = this;
								var res = instance.get('responseData');
								localStorage.removeItem(res.controlNameTemp)
								localStorage.removeItem("schema-"+res.controlNameTemp);
								localStorage.removeItem("options-"+res.controlNameTemp);
								localStorage.removeItem("options-adv-"+res.controlNameTemp);
								localStorage.removeItem("objectSchemaAttr-"+res.controlNameTemp);
								localStorage.removeItem("objectOptionsAttr-"+res.controlNameTemp);
								
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
							},
					    	error: function(){}
						}
					}
				);
			
		}else{
			
			$(this).parent().find(".ui-sortable").each(function( index, element ) {
				
				if($.trim($(element).html()).length > 0){
					countChk = countChk + 1;
				}
			});
		}
		if(countChk == 0){
			$(this).parent().remove();
			if (!$(".demo .lyrow").length > 0) {
				clearDemo();
			}
		}else{
			alert('remove control on layout!');
		}
		
	});
}
</script>