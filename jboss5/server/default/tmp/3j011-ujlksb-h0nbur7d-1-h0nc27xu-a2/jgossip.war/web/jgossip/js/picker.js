/*
 * ***** BEGIN LICENSE BLOCK *****
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License
 * at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is Tigra Color Picker code.
 *
 * The Initial Developer of the Original Code is the SoftComplex Inc.
 * Portions created by the Initial Developer are Copyright (C) 2003
 * the Initial Developer. All Rights Reserved.
 *
 * Portions created by the JResearch Org. are Copyright (C) 2004
 * the JResearch Org. All Rights Reserved.
 *
 * Contributor(s):
 *              Dmitry Belov <bel@jresearch.org>
 *
 * ***** END LICENSE BLOCK ***** */
function TCPopup() {
	this.draw();
	this.contObj.style.display="block";	
}

function TCBuildCell (R, G, B, w, h) {
    var hexValue=this.dec2hex((R << 16) + (G << 8) + B);
	return '<td style="cursor:pointer;background-color:#' + hexValue + ';color:#' + hexValue + ';font-family:Tahoma; font-size: 12px;" onClick="TCP.S(\'' + hexValue + '\');return true;" onmouseover="TCP.P(\'' + hexValue + '\')" >w</td>';
}

function TCSelect(c) {
	this.field.value=c.toUpperCase();
	this.field.focus();
	this.contObj.style.display="none";
	ch_sample(this.field);
}

function TCPaint(c) {
	c = '#'+ c.toUpperCase();
	this.sampObj.innerHTML =  c +' <span style="color:#FFFFFF;background-color:transparent;font-family: Tahoma; font-size: 12px;">' + c + '</span>'
	this.sampObj.style.backgroundColor = c;
}

function TCGenerateSafe() {
	var s = '';
	for (j = 0; j < 12; j ++) {
		s += "<tr>";
		for (k = 0; k < 3; k ++){
			for (i = 0; i <= 5; i ++){
				s += this.bldCell(k * 51 + (j % 2) * 51 * 3, Math.floor(j / 2) * 51, i * 51, 10, 12);
			}
	    }
		s += "</tr>";
	}
	return s;
}

function TCDec2Hex(v) {
	v = v.toString(16);
	for(; v.length < 6; v = '0' + v);
	return v;
}


function TColorPicker(fldObj) {
	this.field=fldObj;
	this.contObj=document.getElementById("jrf_cpdiv");
	this.sampObj=document.getElementById('jrf_cp_samp');
	
	// event handlers
	this.build = TCGenerateSafe;
	this.S       = TCSelect;
	this.P       = TCPaint;
	this.popup   = TCPopup;
	this.draw    = TCDraw;
	this.dec2hex = TCDec2Hex;
	this.bldCell = TCBuildCell;
}

function TCDraw() {
    if(!this.contObj){
        var content='<div id="jrf_cpdiv" style="position:absolute;display:none;padding:2px;left:6px;top:300px;width:218px;height:234px;background-color:#FFFFFF;text-align : center; border: 3px ridge;border-color : #CCCCCC #000000 #000000 #CCCCCC;">'+
            '<table width="100%">'+
                '<tr>'+
                    '<td style="font-size:12px;font-weight : bold;">Color</td>'+
                    '<td align="right"><img  onClick="TCP.contObj.style.display=\'none\';"  style="cursor:pointer;" src="'+WEB_ROOT+'images/redcross.gif" width="17" height="15" border="0" ></td>'+
                '</tr>'+
            '</table>'+
	        '<div id="jrf_cp_samp" style="border : 1px inset InactiveBorder;text-align : center;color: #000000; font-family: Tahoma; font-size: 12px;padding:2px">sample <span style="color:#FFFFFF;background-color : transparent;font-family: Tahoma; font-size: 12px;">sample</span></div>'+
	    '</div>';
	    if(document.all){
	        document.body.insertAdjacentHTML("beforeEnd",content);
	    }else{
	        document.getElementsByTagName("body")[0].innerHTML+=content;
	    }
	    this.contObj=document.getElementById("jrf_cpdiv");
	    this.contObj.innerHTML+=('<table cellpadding=0 cellspacing=2 border=0 align=center>' + this.build() + '</table>');
        this.sampObj =  document.getElementById('jrf_cp_samp');
	}
	if (this.field.value){ 
	    this.P(this.field.value);
	}
}


var TCP=null;
function cp_popup(fname,ename){
    var fldObj=document.forms[fname].elements[ename];
    TCP = new TColorPicker(fldObj);
    TCP.popup();
}

function ch_sample(fieldObj){
	try{
		document.getElementById("jrf_cp_"+fieldObj.name).style.backgroundColor ='#'+fieldObj.value;
		self.status=self.defaultStatus;
	}catch (excObj){
		self.status=excObj.message;
	}
}