package domains;

/**
 * Created by Sylvia on 2015/1/8.
 */
public class Member {
    private String user;
    private String name;
    private int points;
    private boolean isVip;

    public Member(String user) {
        this.user = user;
    }

    public Member(String user, String name, boolean isVip) {
        this.user = user;
        this.name = name;
        this.isVip = isVip;
    }

    public Member(String user, String name, int points, boolean isVip) {
        this.user = user;
        this.name = name;
        this.points = points;
        this.isVip = isVip;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public boolean isVip() {
        return isVip;
    }

    public int getPoints() {
        return points;
    }
}
