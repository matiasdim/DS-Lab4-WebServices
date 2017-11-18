package edu.gvsu.restapi;

import org.json.JSONObject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

/**
 * A POJO representing the actual user resource.
 *
 * @author Jonathan Engelsma (http://themobilemontage.com)
 *
 */

@Entity
public class User {

    @Id private String userName = null;
    private String host = null;
    private boolean status = false;
    private int port = 0;

    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            The name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    /**
     * Convert this object to a JSON object for representation
     */
    public JSONObject toJSON() {
        try{
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("userName", this.userName);
            jsonobj.put("host", this.host);
            jsonobj.put("status", this.status);
            jsonobj.put("port", this.port);

            return jsonobj;
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Convert this object to a string for representation
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("userName:");
        sb.append(this.userName);
        sb.append(",host:");
        sb.append(this.host);
        sb.append(",status:");
        sb.append(this.status);
        sb.append(",port:");
        sb.append(this.port);
        return sb.toString();
    }

    /**
     * Convert this object into an HTML representation.
     * @param fragment if true, generate an html fragment, otherwise a complete document.
     * @return an HTML representation.
     */
    public String toHtml(boolean fragment)
    {
        String retval = "";
        if(fragment) {
            StringBuffer sb = new StringBuffer();
            sb.append("<b> userName: </b>");
            sb.append(userName);
            sb.append("<b> host: </b>");
            sb.append(host);
            sb.append("<b> status: </b>");
            sb.append(status);
            sb.append("<b> port: </b>");
            sb.append(port);
            sb.append(" <a href=\"/v1/users/" + this.userName + "\">View</a>");
            sb.append("<br/>");
            retval = sb.toString();
        } else {
            StringBuffer sb = new StringBuffer("<html><head><title>User Resource</title></head><body><h1>User Representation</h1>");
            sb.append("<b> userName: </b>");
            sb.append(userName);
            sb.append("<b> host: </b>");
            sb.append(host);
            sb.append("<b> status: </b>");
            sb.append(status);
            sb.append("<b> port: </b>");
            sb.append(port);
            sb.append("<br/><br/>Return to <a href=\"/v1/users\">users list<a>.</body></html>");
            retval = sb.toString();
        }
        return retval;
    }
}