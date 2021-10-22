package org.finos.springbot.workflow.response.handlers;

import org.finos.springbot.workflow.annotations.RequiresChatList;
import org.finos.springbot.workflow.conversations.AllConversations;
import org.finos.springbot.workflow.response.Response;
import org.finos.springbot.workflow.response.WorkResponse;

/**
 * When returning a {@link WorkResponse} to the user, this adds a list of chats that the
 * bot is a member of to the response data, for use in drop-downs.
 * 
 * @author rob@kite9.com
 *
 */
public class ChatListResponseHandler implements ResponseHandler {
	
	AllConversations conversations;
	
	
	public ChatListResponseHandler(AllConversations conversations) {
		super();
		this.conversations = conversations;
	}

	@Override
	public void accept(Response t) {
		if (t instanceof WorkResponse) {
			WorkResponse wr = (WorkResponse) t;
			Class<?> c = ((WorkResponse) t).getFormClass();
			
			RequiresChatList rcl = c.getAnnotation(RequiresChatList.class);
				
			if (rcl != null) {
				wr.getData().put(rcl.key(), conversations.getAllChats());
			}
		}
	}


	@Override
	public int getOrder() {
		return MEDIUM_PRIORITY;
	}

}
