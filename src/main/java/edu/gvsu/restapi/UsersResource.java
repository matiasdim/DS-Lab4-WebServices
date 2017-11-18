package edu.gvsu.restapi;

import com.googlecode.objectify.ObjectifyService;
import org.json.JSONArray;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.List;

public class UsersResource extends ServerResource {
    private List<User> users = null;

    @SuppressWarnings("unchecked")
    @Override
    public void doInit() {

        this.users = ObjectifyService.ofy()
                .load()
                .type(User.class) // We want only Users
                .list();

        // these are the representation types this resource can use to describe the
        // set of users with.
        getVariants().add(new Variant(MediaType.APPLICATION_JSON));
    }

    /**
     * Handle an HTTP GET. Represent the user object in the requested format.
     *
     * @param variant
     * @return
     * @throws ResourceException
     */
    @Get
    public Representation get(Variant variant) throws ResourceException {
        Representation result = null;
        if (null == this.users) {
            ErrorMessage em = new ErrorMessage();
            return representError(variant, em);
        } else {
            JSONArray usersArray = new JSONArray();
            for(Object user : this.users) {
                User u = (User)user;
                usersArray.put(u.toJSON());
            }
            result = new JsonRepresentation(usersArray);
        }
        return result;
    }

    /**
     * Handle a POST Http request. Create a new user
     *
     * @param entity
     * @throws ResourceException
     */
    @Post
    public Representation post(Representation entity, Variant variant)
            throws ResourceException
    {

        Representation rep = null;

        // We handle only a form request in this example. Other types could be
        // JSON or XML.
        try {
            if (entity.getMediaType().equals(MediaType.APPLICATION_WWW_FORM,
                    true))
            {
                // Use the incoming data in the POST request to create/store a new user resource.
                Form form = new Form(entity);
                User user = new User();
                user.setUserName(form.getFirstValue("userName"));
                user.setHost(form.getFirstValue("host"));
                user.setStatus(Boolean.valueOf(form.getFirstValue("status")));
                user.setPort(Integer.parseInt(form.getFirstValue("port")));

                // persist updated object
                ObjectifyService.ofy().save().entity(user).now();

                getResponse().setStatus(Status.SUCCESS_OK);
                rep = new JsonRepresentation(user.toJSON());
                getResponse().setEntity(rep);

            } else {
                getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            }
        } catch (Exception e) {
            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
        }
        return rep;
    }

    /**
     * Represent an error message in the requested format.
     *
     * @param variant
     * @param em
     * @return
     * @throws ResourceException
     */
    private Representation representError(Variant variant, ErrorMessage em)
            throws ResourceException {
        Representation result = null;
        if (variant.getMediaType().equals(MediaType.APPLICATION_JSON)) {
            result = new JsonRepresentation(em.toJSON());
        } else {
            result = new StringRepresentation(em.toString());
        }
        return result;
    }

    protected Representation representError(MediaType type, ErrorMessage em)
            throws ResourceException {
        Representation result = null;
        if (type.equals(MediaType.APPLICATION_JSON)) {
            result = new JsonRepresentation(em.toJSON());
        } else {
            result = new StringRepresentation(em.toString());
        }
        return result;
    }
}
