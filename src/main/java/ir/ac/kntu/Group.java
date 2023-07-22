package ir.ac.kntu;

import java.util.ArrayList;

public class Group implements Comparable<Group>{

    private String name;

    private ArrayList<Customer> members;

    private int score = 0;

    public Group(String name) {
        this.name = name;
        members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Customer> getMembers() {
        return new ArrayList<>(members);
    }

    public void addMember(Customer member) {
        members.add(member);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addScoreForMembers() {
        for(Customer member : members) {
            member.setPoint(member.getPoint() + 25);
        }
    }

    public boolean containsMember(String userName) {
        for(Customer member : members) {
            if(member.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Group group) {
        return this.score - group.getScore();
    }
}








