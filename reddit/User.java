/*
 * Name: Mauricio Serrano
 * Email: m2serrano.ucsd.edu
 * PID: A17020958
 * Sources used: "None" 
 * 
 * This code is 1/2 needed to make simplified reddit. it allows user to post, post comments to posts, upvote,downvote, replyto, and have a karma system. It is essentially a social media platform. 
 */


//This import here allows us to utilize arraylist within our program.
import java.util.ArrayList;

//this class is 1/2 classes needed to make our social media platform work. User is the user side based class that the users will use to interact with the website.
public class User {
    

    //instance varriables
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    private static final String TO_STRING_USER_FORMAT = "u/%s Karma: %d";

    /**
     * This method is the user creation for a new user
     * @param username name of user
     * @return no return
     */ 
    public User(String username) {

        //initialize username
        this.username = username;
        //intiialze arrays
        posts = new ArrayList<>();
        upvoted =  new ArrayList<>();
        downvoted = new ArrayList<>();
        
        karma = 0;

    }

     /**
     * This method allows user to addpost to site
     * @param post the post they will be adding
     * @return no return
     */ 
    public void addPost(Post post) {

        //if post dne, return null
        if (post == null) {

         return;
        }

        //add post to arraylsit
        posts.add(post);

        //update karma
        updateKarma();

    }

     /**
     * This method updates karma of user
     * @return no return
     */ 
    public void updateKarma() {

        //karma set to 0
        karma = 0;
        //go through posts arraylist
        for (Post p: posts) {
            //calculate the karma of all posts of user
            karma += p.getUpvoteCount() - p.getDownvoteCount(); 
        }
    }

     /**
     * This method is a getter. it gets the karma of the user
     * @return karma 
     */ 
    public int getKarma() {
        return karma;

    }

     /**
     * This method upvotes another users post
     * @param post post user will want to upvote
     * @return no return
     */ 
    public void upvote(Post post) {

        //if post is null, if post is already upvoted, or if author is username, return null
        if (post == null || upvoted.contains(post) || this.username.equals(post.getAuthor().username)) {
    
            return;
        }
    
        //if downvoted already, remove post from downvoted arraylist
        if (downvoted.contains(post)) {
            
            downvoted.remove(post);
            //update downvotecount
            post.updateDownvoteCount(false);
        }
    
        //add post to upvoted
        upvoted.add(post);
        //update upvotecount
        post.updateUpvoteCount(true);
        //update authors karma
        post.getAuthor().updateKarma();
        
    }

    /**
     * This method downvotes another users post
     * @param post post user will want to downvote
     * @return no return
     */ 
    public void downvote(Post post) {
        //if post is null, if post is already upvoted, or if author is username, return null
        if (post == null || downvoted.contains(post) || this.username.equals(post.getAuthor().username)) {
    
            return;
        }

        //if post is already upvoted, remove it from that arraylist
        if (upvoted.contains(post)) {
            
            upvoted.remove(post);
            //update uvotecount
            post.updateUpvoteCount(false);

        }

        //add post to downvoted arraylist
        downvoted.add(post);
        //update downvotecount
        post.updateDownvoteCount(true);
        //update authors karma
        post.getAuthor().updateKarma();

     


    }

    /**
     * This method returns the top original post with highest upvote - downvote
     * @return highest top post
     */ 
    public Post getTopPost() {

        //local variables
        Post currHighPost = null;
        int currHighestCum = Integer.MIN_VALUE; //-MAX_INT

        //go through the arraylists
        for (Post p: posts) {

            //if post is a original post
            if (p.getReplyTo() == null) {
                //find the post with highest cummalitive points
                int cum = p.getUpvoteCount() - p.getDownvoteCount();
                //compare highest to current highest
                if (currHighestCum < cum) {
                    //if current is higher than current highest, set to new current highest
                    currHighPost = p; 
                    currHighestCum = cum;

                }
            }
        }
        //return highestpost
        return currHighPost;
    }

    /**
     * This method returns a users highest top comment
     * @return highest comment post
     */ 
    public Post getTopComment() {

        //local variables
        Post currHighComment = null;
        int currHighestCum = Integer.MIN_VALUE; //-MAX_INT 
        //goes through users posts arraylists
        for (Post p: posts) {

            //if post is a comment
            if (p.getReplyTo() != null) {

                // cumulative points
                int cum = p.getUpvoteCount() - p.getDownvoteCount();
                //set current highest to new highest if condition is met
                if (currHighestCum < cum) {
                    currHighComment = p;
                    currHighestCum = cum;
                }
            }
        }
        //return highest post
        return currHighComment;

    }

    /**
     * This method returns a users posts.
     * @return posts
     */ 
    public ArrayList<Post> getPosts() {

        return posts;

    }

    /**
     * This method converts username and karma to string format
     * @return return of name and karma in string
     */ 
    public String toString() {
        return String.format(TO_STRING_USER_FORMAT, this.username, getKarma());
        
    }
}
