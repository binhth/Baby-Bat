/**
 * Build Layout It is the open source software
 * Copyright (C) 2016-present

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package com.org.layout;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.service.persistence.PortletUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.org.control.BatControls;
import com.org.control.BatControlsWord;

/**
 * @author binhth
 *
 */
public class BuildIt extends MVCPortlet{

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		
		JSONParser parser = new JSONParser();
	 	Object obj;
		try {
			obj = parser.parse(new InputStreamReader(BuildIt.class.getResourceAsStream("/control.json")));
			JSONObject jsonInfo = JSONFactoryUtil.createJSONObject(obj.toString());
			renderRequest.setAttribute(
					"alpacaInfo", jsonInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.render(renderRequest, renderResponse);
	}
	
	public void buildControl(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);

			String controlType = ParamUtil.getString(actionRequest, "controlType");

			String name =
					ParamUtil.getString(actionRequest, "name");
			
			boolean required = ParamUtil.getBoolean(actionRequest, "required");
			
			String fieldClass =
					ParamUtil.getString(actionRequest, "fieldClass");
			
			String label = ParamUtil.getString(actionRequest, "label");
			
			String batOptions =
					ParamUtil.getString(actionRequest, "batOptions");
			
			String batOptionsJson = ParamUtil.getString(actionRequest, "batOptionsJson");
			
			String controlNameTemp = ParamUtil.getString(actionRequest, "controlNameTemp");
			
//			if(Validator.isNotNull(controlNameTemp)){
//				try {
//					httpRequest.getSession().removeAttribute("schema-"+controlNameTemp);
//					httpRequest.getSession().removeAttribute("options-"+controlNameTemp);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//			
//			
			JSONObject myBatOptions = JSONFactoryUtil.createJSONObject(batOptionsJson);
			String htmlControl = StringPool.BLANK;
			JSONObject objectSchema = JSONFactoryUtil.createJSONObject();
			JSONObject objectOptions = JSONFactoryUtil.createJSONObject();
			String shemaAttr = StringPool.BLANK;
			String optsAttr = StringPool.BLANK;
//			
			JSONParser parser = new JSONParser();
			org.json.simple.JSONObject SimplebatOptions = null;
			
			try {
				SimplebatOptions = (org.json.simple.JSONObject)parser.parse(batOptionsJson);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(controlType.equalsIgnoreCase("control-label")){
				htmlControl = BatControls.control_Label(label, name, fieldClass);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", false);
				objectSchema.put("properties", JSONFactoryUtil.createJSONObject());
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
				objectOptions.put("legendStyle", "");
				objectOptions.put("fields", JSONFactoryUtil.createJSONObject());
				
			}else if(controlType.equalsIgnoreCase("control-text")){
				htmlControl = BatControls.control_Text(label, name, fieldClass,required, myBatOptions);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "text");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-textarea")){
				htmlControl = BatControls.control_TextArea(label, name, fieldClass,required, myBatOptions);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "textarea");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-date")){
				htmlControl = BatControls.control_Date(label, name, fieldClass,required, myBatOptions);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "date");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-datetime")){
				htmlControl = BatControls.control_DateTime(label, name, fieldClass,required, myBatOptions);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "datetime");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-select")){
				htmlControl = BatControls.control_Select(label, name, fieldClass,required, myBatOptions, BatUtils.quoteAndN(batOptions.toString()));
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "select");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-radio")){
				htmlControl = BatControls.control_Radio(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()));
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "radio");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-checkbox")){
				htmlControl = BatControls.control_Checkbox(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()));
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
				//options
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "checkbox");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-table")){
				htmlControl = BatControls.control_Table(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()));
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
				objectSchema.put("items", myBatOptions.getJSONObject("items"));
				//options
				
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "table");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}else if(controlType.equalsIgnoreCase("control-arrays")){
				htmlControl = BatControls.control_Arrays(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()));
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
				objectSchema.put("items", myBatOptions.getJSONObject("items"));
				//options
				
				objectOptions.put("id", name);
				objectOptions.put("name", name);
				objectOptions.put("type", "array");
				objectOptions.put("label", HtmlUtil.escape(label.replaceAll("\"", "\'")));
				objectOptions.put("fieldClass", fieldClass);
			}
			shemaAttr = objectSchema.toString();
			optsAttr = objectOptions.toString();
			if(Validator.isNotNull(SimplebatOptions)){
				for (Object key : SimplebatOptions.keySet()) {
			        String keyStr = (String)key;
			        Object keyvalue = SimplebatOptions.get(keyStr);

			        if (keyvalue instanceof String){
			        	objectOptions.put(keyStr.trim(), String.valueOf(keyvalue).trim());
			        }else if(keyvalue instanceof JSONObject){
			        	objectOptions.put(keyStr.trim(), (JSONObject)keyvalue);
			        }else if(keyvalue instanceof Boolean){
			        	objectOptions.put(keyStr.trim(), (Boolean)keyvalue);
			        }else if(keyvalue instanceof Integer){
			        	objectOptions.put(keyStr.trim(), (Integer)keyvalue);
			        }
			    }
			}
			
			httpRequest.getSession().setAttribute("control-label-"+name, label);
//			httpRequest.getSession().setAttribute("options-"+name, objectOptions);
//			httpRequest.getSession().setAttribute("options-"+name+"-ADV", BatUtils.quote(batOptionsADV.toString()));
//			httpRequest.getSession().setAttribute("options-optionsData-"+name, myBatOptions);
//			//countControl
//			int numberOfControl = 0;
//			Enumeration keys = httpRequest.getSession().getAttributeNames();
//			while (keys.hasMoreElements())
//			{
//			  String key = (String)keys.nextElement();
//			  if(key.startsWith("schema-")){
//				  numberOfControl++;
//			  }
//			  
//			}
//			 replacement
			
			
			jsonObject.put("id", name);
			jsonObject.put("html", htmlControl);
			jsonObject.put("html", htmlControl);
			jsonObject.put("objectSchema", objectSchema.toString());
			jsonObject.put("objectOptions", objectOptions.toString());
			jsonObject.put("objectSchemaAttr", shemaAttr.substring(1, shemaAttr.length()-1));
			jsonObject.put("objectOptionsAttr", HtmlUtil.unescape(optsAttr.substring(1, optsAttr.length()-1)));
			jsonObject.put("objectOptionsAdv", batOptions);
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	
	
	public void buildTableLayout(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			// now read your parameters, e.g. like this:
			// long someParameter = ParamUtil.getLong(request, "someParameter");

			// String keywords = ParamUtil.getString(actionRequest, "keywords");

			String columnLayout = ParamUtil.getString(actionRequest, "columnLayout");

			String[] newLine = columnLayout.split("\n");
//			JSONObject colData = JSONFactoryUtil.createJSONObject();
//			JSONObject colDataLv0 = JSONFactoryUtil.createJSONObject();
			
			List<String> fullList = new ArrayList<String>();
			
			String myFullTH = "";
			
			JSONObject myMap = JSONFactoryUtil.createJSONObject();
			
			int maxRows = 0;
			for (String string : newLine) {
				fullList.add(string);
				maxRows++;
				String[] curCol = string.split("-");
				for (String string2 : curCol) {
					
					if(Validator.isNull(myMap.getJSONObject(string2))){
						myMap.put(string2.trim(), JSONFactoryUtil.createJSONObject());
					}
					if(StringUtil.count(string2, StringPool.PERIOD) > 0 && !string2.trim().endsWith(".0")){
						if(StringUtil.count(string2.trim(), StringPool.PERIOD) > myMap.getInt(string2.trim())){
							
								String parentNode = string2.trim();
								
								if(StringUtil.count(parentNode.trim(), StringPool.PERIOD) > 0){
									
									String[] parenCol = parentNode.split("\\.");
									
									for(int i =0; i < StringUtil.count(parentNode.trim(), StringPool.PERIOD) + 1; i++ ){
										if(!parentNode.trim().equals(".0")){
										
											if(StringUtil.count( parentNode.trim(), StringPool.PERIOD) >= 3){
												myMap.getJSONObject(parentNode.trim().substring(0, parentNode.trim().indexOf(".", i+1)))
												.put("lv", StringUtil.count(string2.trim(), StringPool.PERIOD) - StringUtil.count(parentNode.trim().substring(0, parentNode.trim().indexOf(".", i+1)), StringPool.PERIOD));
											}else{
												myMap.getJSONObject(parentNode.trim().substring(0, parentNode.trim().indexOf(".", i)))
												.put("lv", StringUtil.count(string2.trim(), StringPool.PERIOD) - StringUtil.count(parentNode.trim().substring(0, parentNode.trim().indexOf(".", i)), StringPool.PERIOD));
											}
											
										}
									}
								}
								
								
						}
					}
				}
				
			}
				 System.out.println(myMap.toString());
				 
				 Map<String,Integer> myMapHack = new HashMap<String, Integer>();

				 Map<String, List<String>> myMapHackNext = new HashMap<String,  List<String>>();
				 List<String> dkmLst0 = new ArrayList<String>();
				 List<String> dkmLst1 = new ArrayList<String>();
				 List<String> dkmLst2 = new ArrayList<String>();
				 List<String> dkmLst3 = new ArrayList<String>();
				 List<String> dkmLst4 = new ArrayList<String>();
				 BatUtils.getLastJsonLVTable(myMap, myMapHack);
					
					for (Map.Entry<String, Integer> entry : myMapHack.entrySet()) {
						System.out.println(entry.getKey() + ":" + entry.getValue());
						if(StringUtil.count(entry.getKey(), StringPool.PERIOD) == 0){
							dkmLst0.add(entry.getKey());
						}
						if(StringUtil.count(entry.getKey(), StringPool.PERIOD) == 1){
							dkmLst1.add(entry.getKey());
						}
						if(StringUtil.count(entry.getKey(), StringPool.PERIOD) == 2){
							dkmLst2.add(entry.getKey());
						}
						if(StringUtil.count(entry.getKey(), StringPool.PERIOD) == 3){
							dkmLst3.add(entry.getKey());
						}
						if(StringUtil.count(entry.getKey(), StringPool.PERIOD) == 4){
							dkmLst4.add(entry.getKey());
						}
					 }
					
					myMapHackNext.put("0", dkmLst0);
					myMapHackNext.put("1", dkmLst1);
					myMapHackNext.put("2", dkmLst2);
					myMapHackNext.put("3", dkmLst3);
					myMapHackNext.put("4", dkmLst4);
			int i = 1;
			for (String string : newLine) {
				myFullTH += BatUtils.createTrTh(string, i, maxRows, fullList, myMapHack, myMapHackNext);
				i++;
				
			}
			
			System.out.println("BuildIt.buildTableLayout()"+myFullTH);
			jsonObject.put("id", PortalUUIDUtil.generate());
			jsonObject.put("html", "<div class=\"row\"><table> <tbody>" + myFullTH + "</tbody></table></div>");
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	
	public void buildControlFake(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			// now read your parameters, e.g. like this:
			// long someParameter = ParamUtil.getLong(request, "someParameter");

			// String keywords = ParamUtil.getString(actionRequest, "keywords");

			String view = ParamUtil.getString(actionRequest, "view");
//			StringBuffer sb = new StringBuffer();
//			Pattern patternName = Pattern.compile("\"dataSource\": \"(.*?)\"");
//	        Matcher matcherName = patternName.matcher(view);
//	        while(matcherName.find())
//	        {
//	        	matcherName.appendReplacement(sb, "\"dataSource\": "+matcherName.group(1));
////	        	dkm.put("dataSource", "REPLACEKEY"+matcherName.group(1)+"REPLACEKEY");
//	        }
//	        matcherName.appendTail(sb);
			jsonObject.put("view", view);
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	
	public void liveDemo(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	public void liveDemo2(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	public void removeControl(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);

			String controlNameTemp = ParamUtil.getString(actionRequest, "controlNameTemp");
			System.out.println("BuildIt.removeControl()"+controlNameTemp);
//			//countControl
//			int numberOfControl = 0;
//			Enumeration keys = httpRequest.getSession().getAttributeNames();
//			while (keys.hasMoreElements())
//			{
//			  String key = (String)keys.nextElement();
//			  if(key.startsWith("schema-")){
//				  numberOfControl++;
//			  }
//			  
//			}
//			//countControl
//			keys = httpRequest.getSession().getAttributeNames();
//			while (keys.hasMoreElements())
//			{
//			  String key = (String)keys.nextElement();
//			  if(key.equalsIgnoreCase("schema-"+controlNameTemp)){
//				  httpRequest.getSession().removeAttribute("schema-"+controlNameTemp);
//				  httpRequest.getSession().removeAttribute("options-"+controlNameTemp);
//				  numberOfControl --;
//			  }
//				
//			}
//			if(numberOfControl < 0){
//				numberOfControl = 0;
//			}
			jsonObject.put("controlNameTemp", controlNameTemp);
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	
	public void convertWordToAlpacajs(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);

			String label = ParamUtil.getString(actionRequest, "label");
			label = BatUtils.quoteAndN(label).replaceAll("\"", "\'").replaceAll("mso-highlight: red", "").replaceAll("background:#ff0000;", "background:red;").replaceAll("background: #ff0000;", "background:red;").replaceAll("background:#ff0000", "background:red;").replaceAll("background: #ff0000", "background:red;");
			
			StringBuffer sb = new StringBuffer();
			Pattern patternName = Pattern.compile("<span style=\'background:red;\'>(\\S+)</span>");
	        Matcher matcherName = patternName.matcher(label);
	        List<String> batControl = new ArrayList<String>();
	        
	        while(matcherName.find())
	        {
	        	System.out.println("BuildIt.removeControl()"+matcherName.group(1));
	        	matcherName.appendReplacement(sb, "<span style=\'display: inline-table;\' id=\'column-"+matcherName.group(1).trim().replaceAll("</span>", "")+"\' ></span>");
	        	batControl.add(matcherName.group(1).trim().replaceAll("</span>", ""));
	        }
			
	        matcherName.appendTail(sb);
	        
	        
	        
	        JSONObject alpaca = JSONFactoryUtil.createJSONObject();
	        JSONObject objectSchema = JSONFactoryUtil.createJSONObject();
	        JSONObject objectSchemaProperties = JSONFactoryUtil.createJSONObject();
			JSONObject objectOptions = JSONFactoryUtil.createJSONObject();
			JSONObject objectOptionsFields = JSONFactoryUtil.createJSONObject();
			JSONObject views = JSONFactoryUtil.createJSONObject();
			JSONObject viewsLayout = JSONFactoryUtil.createJSONObject();
			JSONObject viewsBildings = JSONFactoryUtil.createJSONObject();
			JSONObject viewsStyle = JSONFactoryUtil.createJSONObject();
			
			for (String string : batControl) {
				viewsBildings.put(string, "column-"+string);
				objectSchemaProperties.put(string, JSONFactoryUtil.createJSONObject("{\"required\": true, \"type\": \"object\"}"));
				objectOptionsFields.put(string, JSONFactoryUtil.createJSONObject("{\"id\": "+string+", \"name\": "+string+", \"type\": \"text\", \"label\": \"\", \"fieldClass\": \"form-input-100\", \"placeholder\": \""+string+"\"}"));
				viewsStyle.put("#"+string, JSONFactoryUtil.createJSONObject("{ \"width\": \"100% !important\"}"));
			}
			viewsStyle.put(".alpaca-layout-binding-holder", JSONFactoryUtil.createJSONObject("{ \"margin-bottom\": \"0px !important;\"}"));
			viewsStyle.put(".form-group", JSONFactoryUtil.createJSONObject("{ \"margin-bottom\": \"0px !important;\"}"));
			viewsStyle.put("table", JSONFactoryUtil.createJSONObject("{ \"margin-left\": \"0px !important;\"}"));
			viewsStyle.put("td", JSONFactoryUtil.createJSONObject("{ \"border\": \"1px solid #ddd !important;\"}"));
			viewsLayout.put("bindings", viewsBildings);
//			viewsLayout.put("template", HtmlUtil.unescape(sb.toString().replaceAll("\"", "\'")));
			viewsLayout.put("template", HtmlUtil.unescape("<div class='row-fluid'><div class='row-fluid'>"+sb.toString()+"</div></div>"));
			views.put("parent", "bootstrap-edit");
			views.put("fields", JSONFactoryUtil.createJSONObject());
			views.put("layout", viewsLayout);
			views.put("styles", viewsStyle);
			
			//
			objectSchema.put("type", "object");
			objectSchema.put("required", false);
			objectSchema.put("properties", objectSchemaProperties);
			
			//
			objectOptions.put("type", "object");
			objectOptions.put("legendStyle", "");
			objectOptions.put("fields", objectOptionsFields);
			
			alpaca.put("schema", objectSchema);
			alpaca.put("options", objectOptions);
			alpaca.put("view", views);
			System.out.println("BuildIt.sb()"+alpaca);
			jsonObject.put("alpaca", alpaca.toString());
			jsonObject.put("batControl", batControl.toString());
			jsonObject.put("batWordView", views.toString());
			jsonObject.put("batWordSchema", objectSchema.toString());
			jsonObject.put("batWordOption", objectOptions.toString());
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	public void buildSampleData(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);

			String name = ParamUtil.getString(actionRequest, "name");
			String batSample = ParamUtil.getString(actionRequest, "batSample");
			String batSampleLocal = ParamUtil.getString(actionRequest, "batSampleLocal");
			
			JSONObject sampleTemp = JSONFactoryUtil.createJSONObject();
			if(Validator.isNotNull(batSampleLocal)){
				sampleTemp = JSONFactoryUtil.createJSONObject(batSampleLocal);
			}
			
			sampleTemp.put(name, batSample);
			
			jsonObject.put("tempId", name.trim());
			jsonObject.put("sampleTemp", sampleTemp.toString().trim());
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	
	
	public void buildControlConvertWord(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);

			String sampleDataBat = ParamUtil.getString(actionRequest, "sampleDataBat");
			String controlType = ParamUtil.getString(actionRequest, "controlType");
			controlType = controlType.replaceAll("\"", "");
			String name =
					ParamUtil.getString(actionRequest, "name");
			name = name.replaceAll("\"", "");
			boolean required = ParamUtil.getBoolean(actionRequest, "required");
			
			String fieldClass =
					ParamUtil.getString(actionRequest, "fieldClass");
			fieldClass = fieldClass.replaceAll("\"", "");
			String label = ParamUtil.getString(actionRequest, "label");
			label = label.replaceAll("\"", "");
			String batOptions =
					ParamUtil.getString(actionRequest, "batOptions");
			
			String batOptionsJson = ParamUtil.getString(actionRequest, "batOptionsJson");
			
			String controlNameTemp = ParamUtil.getString(actionRequest, "controlNameTemp");
			
			String styleWidth =
					ParamUtil.getString(actionRequest, "styleWidth");
			
			String htmlControl = StringPool.BLANK;
			JSONObject objectSchema = JSONFactoryUtil.createJSONObject();
			JSONObject myBatOptions = JSONFactoryUtil.createJSONObject(batOptionsJson);
			
			if(controlType.equalsIgnoreCase("text")){
				htmlControl = BatControlsWord.control_Text(label, name, fieldClass,required, myBatOptions, styleWidth);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("textarea")){
				htmlControl = BatControlsWord.control_TextArea(label, name, fieldClass,required, myBatOptions, styleWidth);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("date")){
				htmlControl = BatControlsWord.control_Date(label, name, fieldClass,required, myBatOptions, styleWidth);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("datetime")){
				htmlControl = BatControlsWord.control_DateTime(label, name, fieldClass,required, myBatOptions, styleWidth);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("select")){
				htmlControl = BatControlsWord.control_Select(label, name, fieldClass,required, myBatOptions, BatUtils.quoteAndN(batOptions.toString()), styleWidth);
				//schema
				objectSchema.put("type", "object");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("radio")){
				htmlControl = BatControlsWord.control_Radio(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()), styleWidth);
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("checkbox")){
				htmlControl = BatControlsWord.control_Checkbox(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()), styleWidth);
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
			}else if(controlType.equalsIgnoreCase("table")){
				htmlControl = BatControlsWord.control_Table(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()));
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
				objectSchema.put("items", myBatOptions.getJSONObject("items"));
			}else if(controlType.equalsIgnoreCase("array")){
				htmlControl = BatControlsWord.control_Arrays(label, name, fieldClass,required, myBatOptions, BatUtils.quote(batOptions.toString()));
				//schema
				objectSchema.put("type", "array");
				objectSchema.put("required", required);
				objectSchema.put("items", myBatOptions.getJSONObject("items"));
			}

			jsonObject.put("html", htmlControl);
			jsonObject.put("objectSchema", objectSchema.toString());
			jsonObject.put("sampleDataBat", sampleDataBat);
			jsonObject.put("styleWidth", styleWidth);
			jsonObject.put("controlType", controlType);
			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	public static void writeJSON(
			ActionRequest actionRequest, ActionResponse actionResponse, Object json)
			throws IOException {

			HttpServletResponse response =
				PortalUtil.getHttpServletResponse(actionResponse);

			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, json.toString());
			response.flushBuffer();

		}
}
