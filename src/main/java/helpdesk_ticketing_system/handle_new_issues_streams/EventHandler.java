package helpdesk_ticketing_system.handle_new_issues_streams;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventHandler implements RequestHandler<Map<String,Object>,Object> {

    public EventHandler() {
    }

    public Object handleRequest(Map<String, Object> inputEvent, Context context) {
        context.getLogger().log("inputEvent = " + inputEvent+"\n");

        Map<?,?> detail = (LinkedHashMap<?, ?>) inputEvent.get("detail");
        context.getLogger().log("detail.operationType : " + detail.get("operationType") + "\n");

        Map<?,?> fullDoc = (LinkedHashMap<?,?>)detail.get("fullDocument");
        Issue issue = new Issue();
        issue.set_id((String)fullDoc.get("_id"));
        issue.setPostedOn((Long)fullDoc.get("posted_on"));
        issue.setStatus((String)fullDoc.get("status"));
        issue.setSubmitted_by((String)fullDoc.get("submitted_by"));
        issue.setSubject((String)fullDoc.get("subject"));
        issue.setDescription((String)fullDoc.get("description"));

        context.getLogger().log("issue ==> " + issue + "\n");
        return null;
    }
}
