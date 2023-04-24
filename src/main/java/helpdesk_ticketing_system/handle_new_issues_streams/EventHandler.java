package helpdesk_ticketing_system.handle_new_issues_streams;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventHandler implements RequestHandler<Map<String,Object>,Object> {

    private final MongoDb mongoDb;
    private final String dataFieldName;
    public EventHandler() {
        mongoDb = new MongoDb();
        dataFieldName = System.getenv("data_field_name");
    }

    public Object handleRequest(Map<String, Object> inputEvent, Context context) {
        context.getLogger().log("inputEvent = " + inputEvent+"\n");
        Map<?,?> fullDoc = (LinkedHashMap<?,?>)inputEvent.get(dataFieldName);

        Issue issue = new Issue();
        issue.set_id((String)fullDoc.get("_id"));
        issue.setPostedOn((Long)fullDoc.get("posted_on"));
        issue.setSubmitted_by((String)fullDoc.get("submitted_by"));
        issue.setSubject((String)fullDoc.get("subject"));
        issue.setDescription((String)fullDoc.get("description"));

        if(mongoDb.addData(issue,context))
            return HttpURLConnection.HTTP_OK;

        return HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
