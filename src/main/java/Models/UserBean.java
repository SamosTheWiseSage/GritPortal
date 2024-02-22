package Models;

import java.io.Serializable;

public class UserBean implements Serializable {
    private USER_TYPE userType = USER_TYPE.students;
    private PRIVILAGE_TYPE userPrivilage = PRIVILAGE_TYPE.user;
    private STATE_TYPE stateType = STATE_TYPE.anonymous;
    private String myVar = "";
    public UserBean(){}

    public String getMyVar() {
        return myVar;
    }

    public void setMyVar(String myVar) {
        this.myVar = myVar;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    public PRIVILAGE_TYPE getUserPrivilage() {
        return userPrivilage;
    }

    public void setUserPrivilage(PRIVILAGE_TYPE userPrivilage) {
        this.userPrivilage = userPrivilage;
    }

    public STATE_TYPE getStateType() {
        return stateType;
    }

    public void setStateType(STATE_TYPE stateType) {
        this.stateType = stateType;
    }
}

