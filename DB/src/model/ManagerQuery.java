package model;

public class ManagerQuery extends UserQuery {

    private static ManagerQuery instance;

    private ManagerQuery() {}

    public static ManagerQuery getInstance() {
        if (instance == null) {
            instance = new ManagerQuery();
        }
        return instance;
    }

}
