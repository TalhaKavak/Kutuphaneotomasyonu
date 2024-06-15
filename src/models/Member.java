package models;

public class Member {
    private int id;
    private String name;
    private String memberType;

    public Member(int id, String name, String memberType) {
        this.id = id;
        this.name = name;
        this.memberType = memberType;
    }

    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMemberType() {
        return memberType;
    }
}
