
public class Tester {
    //main method where all the testing of post and user classes will occur. 
    public static void main(String[] args) {
        User u1 = new User("CSE11Student");
        User u2 = new User("CSE12Student");
        Post p2 = new Post("i like winners", "winners are superior to losers", u2);
        Post p1 = new Post("The Inter-marriage councelling of the day", "yea so wat do you think about that bud?", u1);
        Post c1 = new Post("bro wat r u even saying", p1, u1);
        System.out.println(u1);
        System.out.println(p1);
        System.out.println(c1);
        u1.addPost(p1);
        u1.addPost(c1);
        u1.upvote(c1);
        u1.downvote(p1);
        System.out.println(u1.toString());
        System.out.println(c1.getThread());
        System.out.println(u1.getPosts());
        System.out.println(u1.getTopPost());
        System.out.println(u1.getTopComment());
        System.out.println(u1.getKarma());
        u1.upvote(p2);
    }
}
