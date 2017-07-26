package com.namelessmc.plugin.NamelessBungee;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public enum Message {

    // Global
    NO_PERMISSION,
	MUST_BE_INGAME,
	PLAYER_NOT_VALID,
	MUST_REGISTER,
	
	// Incorrect usage
	INCORRECT_USAGE_MAIN,
	INCORRECT_USAGE_REGISTER,
	INCORRECT_USAGE_GETUSER,
	INCORRECT_USAGE_GETNOTIFICATIONS,
	INCORRECT_USAGE_REPORT,
	INCORRECT_USAGE_SETGROUP,
	
	// Help/Alone Help Messages
	HELP_DESCRIPTION_MAIN,
	HELP_DESCRIPTION_REGISTER,
	HELP_DESCRIPTION_GETUSER,
	HELP_DESCRIPTION_GETNOTIFICATIONS,
	HELP_DESCRIPTION_REPORT,
	HELP_DESCRIPTION_SETGROUP,
	
	// Register
	REGISTER_SUCCESS,
	REGISTER_USERNAME_EXISTS,
	@Deprecated REGISTER_UUID_EXISTS,
	@Deprecated REGISTER_EMAIL_EXISTS,

	
	// Notification
	NO_NOTIFICATIONS,
	NOTIFICATIONS_ALERTS,
	NOTIFICATIONS_MESSAGES,
	NOIFICATIONS_ERROR,
	
	// Username
	USERNAME_SYNC_SUCCESS,
	USERNAME_SYNC_ERROR,
	

	// Report 
	REPORT_SUCCESS,
	
	// SetGroup
	SETGROUP_SUCCESS,
	SETGROUP_SYNC_PLAYER_SUCESS,
	SETGROUP_SYNC_PLAYER_ERROR,
	
	// Get User
	GETUSER_USERNAME,
	GETUSER_DISPLAYNAME,
	GETUSER_UUID,
	GETUSER_GROUP_ID,
	GETUSER_REGISTERED,
	GETUSER_REPUTATION,

	GETUSER_VALIDATED,
	GETUSER_VALIDATED_YES,
	GETUSER_VALIDATED_NO,

	GETUSER_BANNED,
	GETUSER_BANNED_YES,
	GETUSER_BANNED_NO;

	public String getMessage() {
		String configString = this.toString().toLowerCase().replace("_", "-");		
		return Chat.convertColorsString(Config.MESSAGES.getConfig().getString(configString));
	}
	
	public BaseComponent[] getComponents() {
		return TextComponent.fromLegacyText(this.getMessage());
    }

}
