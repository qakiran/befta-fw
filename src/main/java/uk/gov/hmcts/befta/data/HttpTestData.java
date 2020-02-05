package uk.gov.hmcts.befta.data;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public class HttpTestData {

    private static final String KEY_INVOKING_USER = "invokingUser";

    private String _guid_;

    private String _extends_;

    private String title;

    private List<String> specs;

    private String productName;

    private String operationName;

    private String method;

    private String uri;

    private RequestData request;

    private ResponseData expectedResponse;

    private ResponseData actualResponse;

    private LinkedHashMap<String, UserData> users = new LinkedHashMap<>();

    private UserData userSet = null;

    public boolean meetsSpec(String specification) {
        return specs.contains(specification);
    }

    public boolean meetsOperationOfProduct(String operationName, String productName) {
        return operationName.equals(this.operationName) && productName.equals(this.productName);
    }

    public UserData getInvokingUser() {
        return getUsers().get(KEY_INVOKING_USER);
    }

    public void setInvokingUser(UserData invokingUser) {
        getUsers().put(KEY_INVOKING_USER, invokingUser);
    }

    public void setUser(UserData user) {
        setInvokingUser(user);
        userSet = user;
    }

    public void setUsers(LinkedHashMap<String, UserData> users) {
        if (users == null)
            throw new IllegalArgumentException("User map cannot be null.");
        this.users = users;
        if (userSet != null) {
            setInvokingUser(userSet);
        }
    }
}
