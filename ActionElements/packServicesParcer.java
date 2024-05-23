package com.audiumcorp.support.elements.actionElements;

//These classes are used by custom configurable elements.

import java.util.List;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.session.ElementAPI;
import com.audium.server.voiceElement.ActionElementBase;
import com.audium.server.voiceElement.ElementData;
import com.audium.server.voiceElement.ElementException;
import com.audium.server.voiceElement.ElementInterface;
import com.audium.server.voiceElement.Setting;
import com.audium.server.xml.ActionElementConfig;
import com.audiumcorp.support.elements.actionElements.dto.input.InputDto;
import com.audiumcorp.support.elements.actionElements.dto.input.packServices;


/**
 * This is the skeleton of a configurable action element. This is different 
 * from a standard action in that it is pre-built and the developer 
 * configures it in Audium Builder for Studio. The methods implemented here 
 * apply primarily to define the configuration for display in the Builder. Note 
 * that there is no need to implement the methods getExitStates because action 
 * elements only have a single exit state: "done".
 */

public class packServicesParcer extends ActionElementBase implements ElementInterface 
{
    /**
     * This method is run when the action is visited. From the ActionElementData
     * object, the configuration can be obtained. 
     */
	public final void doAction(String name, ActionElementData actionData) throws ElementException, AudiumException { 
		try {
			
		ActionElementConfig config = actionData.getActionElementConfig();
		String jsonString = config.getSettingValue("jsonData", (ElementAPI)actionData);
		String softBlockServiceList = config.getSettingValue("softBlockServiceList", (ElementAPI)actionData);
		
		InputDto inputDto = Utils.parseInputDto(jsonString);
				
		String result = "false";		

		// JSON String to Java Object
					
		if (inputDto.packServices.size() > 0) {
			inputDto.packServices.forEach(item -> {
				if ((softBlockServiceList.includes(item.packServiceName) && item.status == "Действующий") || (item.packServiceCode == "SOFTBLOCK")) 
				result = "gentleBlock";
			})
		} else if (inputDto.packServices && inputDto.packServices.size() == 0) {
			result = "emptyBody";
		} else if (inputDto.errorCode) {
			result = "errorBody";
		}

		actionData.setElementData("packServiceParcer_result", result);
		
		  } catch (Exception e) {
		e.printStackTrace();
		}	 
    }

	/**
	 * This method returns the name the action element will have in the Element 
	 * Pane in the Audium Builder for Studio.
	 */
    public String getElementName()
    {
         return "packServiceParcer";
    }

	/**
	 * This method returns the name of the folder in which this action element 
	 * resides. Return null if it is to appear directly under the Elements 
	 * folder.
	 */
    public String getDisplayFolderName()
    {
        return "CTI";
    }

	/**
	 * This method returns the text of a description of the action element that 
	 * will appear as a popup when the cursor points to the element.
	 */
    public String getDescription() 
    {
        return "This element parse packServiceName";
    }

	/**
	 * This method returns an array of Setting objects representing all the 
	 * settings this action element expects. Return null if the action element 
	 * does not need any settings.
	 */
    public Setting[] getSettings() throws ElementException 
    {
    	Setting[] settingArray = new Setting[2];
		settingArray[0] = new Setting("jsonData", "jsonData", "json input data", false, false, true, 3);
		settingArray[1] = new Setting("softBlockServiceList", "softBlocklist", "softBlock list input data", false, false, true, 3);
		return settingArray;
    } 

	/**
	 * This method returns an array of ElementData objects representing the 
	 * element data that this action element creates. Return null if the action 
	 * element does not create any Element Data.
	 */
    public ElementData[] getElementData() throws ElementException 
    {
		// PUT YOUR CODE HERE.
		ElementData[] elementDataArray = new ElementData[1];
		elementDataArray[0] = new ElementData("packServiceParcer_result", "Output of the result packServiceParcer");
		return elementDataArray;
    }
 }
