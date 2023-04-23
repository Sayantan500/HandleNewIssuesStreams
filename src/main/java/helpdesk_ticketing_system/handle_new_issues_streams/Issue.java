package helpdesk_ticketing_system.handle_new_issues_streams;

public class Issue {
    private String _id;
    private String submitted_by;
    private String subject;
    private String description;
    private String ticket_id;
    private String status;
    private Long postedOn;

    public Issue() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSubmitted_by() {
        return submitted_by;
    }

    public void setSubmitted_by(String submitted_by) {
        this.submitted_by = submitted_by;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Long postedOn) {
        this.postedOn = postedOn;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "_id='" + _id + '\'' +
                ", submitted_by='" + submitted_by + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", ticket_id='" + ticket_id + '\'' +
                ", status='" + status + '\'' +
                ", postedOn=" + postedOn +
                '}';
    }
}
