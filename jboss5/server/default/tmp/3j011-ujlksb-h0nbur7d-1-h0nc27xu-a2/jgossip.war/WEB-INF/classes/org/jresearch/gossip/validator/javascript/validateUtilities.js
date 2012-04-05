    /*$RCSfile$ $Revision$ $Date$ */

  /**
  * This is a place holder for common utilities used across the javascript validation
  *
  **/    
            function showErrors(mess,focusField){
				focusField.focus();
            	alert(mess.join('\n'));
            }